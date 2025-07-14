package br.edu.ifpb.biblioteca.warakkayu;

import javax.swing.SwingUtilities;

import br.edu.ifpb.biblioteca.warakkayu.view.admin.GerenciamentoDeObra;


public class App 
{
    public static void main( String[] args )
    {   
        SwingUtilities.invokeLater(() -> {
            GerenciamentoDeObra gerenciamentoDeObra = new GerenciamentoDeObra();
        });
        
    }
}