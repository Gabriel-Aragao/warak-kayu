package br.edu.ifpb.biblioteca.warakkayu;

import javax.swing.SwingUtilities;

import br.edu.ifpb.biblioteca.warakkayu.view.CadastroDeObra;

public class App 
{
    public static void main( String[] args )
    {   
        SwingUtilities.invokeLater(() -> {
            CadastroDeObra cadastroDeObra = new CadastroDeObra();
            cadastroDeObra.setVisible(true);
        });
    }
}
