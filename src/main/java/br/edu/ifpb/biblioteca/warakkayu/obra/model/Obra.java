package br.edu.ifpb.biblioteca.warakkayu.obra.model;

import java.time.LocalDate;
import java.util.UUID;

public abstract class Obra implements Emprestavel{
    private UUID id;
    private long codigo;
    private String titulo;
    private String autor;
    private int anoPublicacao;
    private StatusObra status;
    private double valorDaMulta;
    private TipoObra tipoObra;

    public Obra(TipoObra tipoObra, long codigo, String titulo, String autor, 
            int anoPublicacao, double valorDaMulta) {
        this.tipoObra = tipoObra;
        this.id = UUID.randomUUID();
        this.setCodigo(codigo);
        this.setTitulo(titulo);
        this.setAutor(autor);
        this.setAnoPublicacao(anoPublicacao);
        this.setStatus(StatusObra.DISPONIVEL);
        this.setValorDaMulta(valorDaMulta);
    }

    public Obra(TipoObra tipoObra, UUID id, long codigo, String titulo, 
            String autor, int anoPublicacao, StatusObra statusObra, double valorDaMulta) {
        this.tipoObra = tipoObra;
        this.id = id;
        this.setCodigo(codigo);
        this.setTitulo(titulo);
        this.setAutor(autor);
        this.setAnoPublicacao(anoPublicacao);
        this.setStatus(statusObra);
        this.setValorDaMulta(valorDaMulta);
    }

    public abstract int getTempoEmprestimo();
    @Override
    public void emprestar() {
        this.setStatus(StatusObra.EMPRESTADO);
    }
    public void devolver() {
        this.setStatus(StatusObra.DISPONIVEL);
    }
    public boolean isDisponivel() {
        return this.getStatus() == StatusObra.DISPONIVEL;
    }
    public  LocalDate calcularDataDevolucao() {
        return LocalDate.now().plusDays(this.getTempoEmprestimo());
    }

    public UUID getId(){
        return this.id;
    }

    public TipoObra geTipoObra() {
        return this.tipoObra;
    }

    public long getCodigo() {
        return this.codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return this.autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnoPublicacao() {
        return this.anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public StatusObra getStatus() {
        return this.status;
    }

    public void setStatus(StatusObra status) {
        this.status = status;
    }

    public Double getValorDaMulta(){
        return this.valorDaMulta;
    }

    public void setValorDaMulta(double valorDaMulta){
        this.valorDaMulta = valorDaMulta;
    }
    public double calcularMulta(int diasDeAtraso) {
        return getValorDaMulta() * diasDeAtraso;
    }
    
}
