package br.edu.ifpb.biblioteca.warakkayu.relatorio.service;

import br.edu.ifpb.biblioteca.warakkayu.emprestimo.dao.EmprestimoDAO;
import br.edu.ifpb.biblioteca.warakkayu.emprestimo.model.Emprestimo;
import br.edu.ifpb.biblioteca.warakkayu.emprestimo.model.StatusEmprestimo;
import br.edu.ifpb.biblioteca.warakkayu.obra.model.Obra;
import br.edu.ifpb.biblioteca.warakkayu.pagamento.dao.PagamentoDao;
import br.edu.ifpb.biblioteca.warakkayu.pagamento.model.Pagamento;
import br.edu.ifpb.biblioteca.warakkayu.usuario.model.Usuario;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RelatorioService {

    private static final String DEST_FOLDER = "relatorios";
    private final EmprestimoDAO emprestimoDAO;
    private final PagamentoDao pagamentoDao;


    public RelatorioService(
            EmprestimoDAO emprestimoDAO, PagamentoDao pagamentoDao
        ) 
    {
        this.emprestimoDAO = emprestimoDAO;
        this.pagamentoDao = pagamentoDao;
    }

    public void gerarRelatorioEmprestimosDoMes() throws IOException {
        YearMonth mesAtual = YearMonth.now();
        List<Emprestimo> emprestimos = emprestimoDAO.list();

        List<Emprestimo> emprestimosDoMes = emprestimos.stream()
                .filter(e -> YearMonth.from(e.getDataEmprestimo()).equals(mesAtual))
                .collect(Collectors.toList());

        String nomeArquivo = "emprestimos_do_mes_" + mesAtual.toString() + ".pdf";
        Document documento = criarDocumentoPdf(nomeArquivo);

        adicionarCabecalho(
            documento, "Relatório de Empréstimos Realizados no Mês", 
            "Mês/Ano de Referência: " + mesAtual.format(DateTimeFormatter.ofPattern("MM/yyyy"))
        );

        Table tabela = new Table(UnitValue.createPercentArray(new float[]{1, 1, 3, 1, 3}));
        tabela.setWidth(UnitValue.createPercentValue(100));
        
        tabela.addHeaderCell(new Cell().add(new Paragraph("Data")));
        tabela.addHeaderCell(new Cell().add(new Paragraph("Matrícula")));
        tabela.addHeaderCell(new Cell().add(new Paragraph("Usuário")));
        tabela.addHeaderCell(new Cell().add(new Paragraph("Código")));
        tabela.addHeaderCell(new Cell().add(new Paragraph("Obra")));

        for (Emprestimo e : emprestimosDoMes) {
            tabela.addCell(e.getDataEmprestimo().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            tabela.addCell(e.getUsuario().getMatricula());
            tabela.addCell(e.getUsuario().getNome());
            tabela.addCell(String.valueOf(e.getObra().getCodigo()));
            tabela.addCell(e.getObra().getTitulo());
        }

        documento.add(tabela);
        documento.close();
    }

    public void gerarRelatorioObrasMaisEmprestadas() throws IOException {
        List<Emprestimo> emprestimos = emprestimoDAO.list();

        Map<Obra, Long> contagemObras = emprestimos.stream()
                .collect(Collectors.groupingBy(Emprestimo::getObra, Collectors.counting()));
        
        List<Map.Entry<Obra, Long>> obrasOrdenadas = contagemObras.entrySet().stream()
                .sorted(Map.Entry.<Obra, Long>comparingByValue().reversed())
                .collect(Collectors.toList());
        
        String nomeArquivo = "obras_mais_emprestadas.pdf";
        Document documento = criarDocumentoPdf(nomeArquivo);

        adicionarCabecalho(documento, "Relatório de Obras Mais Emprestadas", null);

        Table tabela = new Table(UnitValue.createPercentArray(new float[]{1, 1, 3, 1}));
        tabela.setWidth(UnitValue.createPercentValue(100));
        
        tabela.addHeaderCell(new Cell().add(new Paragraph("#")));
        tabela.addHeaderCell(new Cell().add(new Paragraph("Código")));
        tabela.addHeaderCell(new Cell().add(new Paragraph("Título")));
        tabela.addHeaderCell(new Cell().add(new Paragraph("Empréstimos")));
        
        int posicao = 1;
        for (Map.Entry<Obra, Long> obra : obrasOrdenadas) {
            tabela.addCell(String.valueOf(posicao++));
            tabela.addCell(String.valueOf(obra.getKey().getCodigo()));
            tabela.addCell(obra.getKey().getTitulo());
            tabela.addCell(String.valueOf(obra.getValue()));
        }

        documento.add(tabela);
        documento.close();
    }
    
    public void gerarRelatorioUsuariosComMaisAtrasos() throws IOException {
        List<Emprestimo> emprestimos = emprestimoDAO.list();

        Map<Usuario, Long> contagemAtrasos = emprestimos.stream()
            .filter(e -> e.getStatusEmprestimo() == StatusEmprestimo.CONCLUIDO_COM_ATRASO || 
                         e.getStatusEmprestimo() == StatusEmprestimo.CONCLUIDO_COM_ATRASO)
            .collect(Collectors.groupingBy(Emprestimo::getUsuario, Collectors.counting()));
            
        List<Map.Entry<Usuario, Long>> usuariosOrdenados = contagemAtrasos.entrySet().stream()
            .sorted(Map.Entry.<Usuario, Long>comparingByValue().reversed())
            .collect(Collectors.toList());

        String nomeArquivo = "usuarios_com_mais_atrasos.pdf";
        Document documento = criarDocumentoPdf(nomeArquivo);

        adicionarCabecalho(
            documento, "Relatório de Usuários com Mais Atrasos", null
        );

        Table tabela = new Table(UnitValue.createPercentArray(new float[]{1, 4, 2}));
        tabela.setWidth(UnitValue.createPercentValue(100));

        tabela.addHeaderCell(new Cell().add(new Paragraph("#")));
        tabela.addHeaderCell(new Cell().add(new Paragraph("Nome do Usuário")));
        tabela.addHeaderCell(new Cell().add(new Paragraph("Nº de Atrasos")));

        int posicao = 1;
        for (Map.Entry<Usuario, Long> entry : usuariosOrdenados) {
            tabela.addCell(String.valueOf(posicao++));
            tabela.addCell(entry.getKey().getNome());
            tabela.addCell(String.valueOf(entry.getValue()));
        }
        
        documento.add(tabela);
        documento.close();
    }

    public void gerarRelatorioPagamentosDoMes() throws IOException {
        YearMonth mesAtual = YearMonth.now();
        List<Pagamento> pagamentos = pagamentoDao.list();

        List<Pagamento> pagamentosDoMes = pagamentos.stream()
                .filter(pagamento -> YearMonth.from(pagamento.getData()).equals(mesAtual))
                .collect(Collectors.toList());

        String nomeArquivo = "pagamentos_do_mes_" + mesAtual.toString() + ".pdf";
        Document documento = criarDocumentoPdf(nomeArquivo);

        adicionarCabecalho(
            documento, "Relatório de Pagamentos Realizados no Mês", 
            "Mês/Ano de Referência: " + mesAtual.format(DateTimeFormatter.ofPattern("MM/yyyy"))
        );

        Table tabela = new Table(UnitValue.createPercentArray(new float[]{1, 2, 3, 3, 3}));
        tabela.setWidth(UnitValue.createPercentValue(100));
        
        tabela.addHeaderCell(new Cell().add(new Paragraph("Data")));
        tabela.addHeaderCell(new Cell().add(new Paragraph("Valor")));
        tabela.addHeaderCell(new Cell().add(new Paragraph("Pagador")));
        tabela.addHeaderCell(new Cell().add(new Paragraph("Recebedor")));
        tabela.addHeaderCell(new Cell().add(new Paragraph("Obra")));

        for (Pagamento pagamento : pagamentosDoMes) {
            tabela.addCell(pagamento.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            tabela.addCell("R$ "+String.format("%.2f", pagamento.getValor()));
            tabela.addCell(pagamento.getPagante().getNome());
            tabela.addCell(pagamento.getRecebedor().getNome());
            tabela.addCell(pagamento.getEmprestimo().getObra().getTitulo());
        }

        documento.add(tabela);
        documento.close();
    }

    private Document criarDocumentoPdf(String nomeArquivo) throws IOException {
        Path pasta = Paths.get(DEST_FOLDER);
        if (Files.notExists(pasta)) {
            Files.createDirectories(pasta);
        }
        PdfWriter writer = new PdfWriter(pasta.resolve(nomeArquivo).toString());
        PdfDocument pdf = new PdfDocument(writer);
        return new Document(pdf);
    }

    private void adicionarCabecalho(Document documento, String titulo, String subtitulo) {
        documento.add(new Paragraph(titulo)
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(18));
        
        if (subtitulo != null && !subtitulo.isEmpty()) {
             documento.add(new Paragraph(subtitulo)
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(12));
        }

        documento.add(new Paragraph("Gerado em: " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(10));

        documento.add(new Paragraph("\n"));
    }
}