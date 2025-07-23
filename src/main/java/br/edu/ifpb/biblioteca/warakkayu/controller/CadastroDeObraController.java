package br.edu.ifpb.biblioteca.warakkayu.controller;

import br.edu.ifpb.biblioteca.warakkayu.Router;
import br.edu.ifpb.biblioteca.warakkayu.exceptions.ObraNaoEncontradaException;
import br.edu.ifpb.biblioteca.warakkayu.exceptions.PersistenciaException;
import br.edu.ifpb.biblioteca.warakkayu.model.Obra;
import br.edu.ifpb.biblioteca.warakkayu.model.TipoObra;
import br.edu.ifpb.biblioteca.warakkayu.service.ObraService;
import br.edu.ifpb.biblioteca.warakkayu.view.CadastroDeObra;

public class CadastroDeObraController implements AcoesDeCadastroListener {
    
    private final CadastroDeObra view;
    private final ObraService obraService;
    private final Router router;

    public CadastroDeObraController(CadastroDeObra view, ObraService obraService, Router router) {
        this.view = view;
        this.obraService = obraService;
        this.router = router;
        this.view.setListener(this);
    }

    @Override
    public void aoClicarSalvar() {
        Obra obra = this.view.getObra();
        long codigo = Long.parseLong(this.view.getCodigo().getTextField().getText());
        String titulo = this.view.getTitulo().getTextField().getText();
        String autor = this.view.getAutor().getTextField().getText();
        int anoPublicacao = Integer.parseInt(
            this.view.getAnoPublicacao()
            .getTextField()
            .getText()
        );
        double valorDaMulta = Double.parseDouble(
            this.view.getValorDaMulta()
            .getTextField()
            .getText()
        );
        TipoObra tipoObra = (TipoObra) view.getTipo().getSelectedItem();
        try{
            obraService.save(obra, codigo, titulo, autor, anoPublicacao, valorDaMulta, tipoObra);
            this.view.exibirMensagem("Obra salva com sucesso!");
        } catch(PersistenciaException  e){
            this.view.exibirErro("Erro ao salvar a Obra! \nOperação Cancelada.");
        } catch(ObraNaoEncontradaException e) {
            this.view.exibirErro("Obra Não encontrada! \nOperação Cancelada.");
        }
        this.router.toGerenciamentoObras(null);
    }
}
