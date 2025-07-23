package br.edu.ifpb.biblioteca.warakkayu;

import javax.swing.JOptionPane;

import br.edu.ifpb.biblioteca.warakkayu.dao.ObraDAO;
import br.edu.ifpb.biblioteca.warakkayu.exceptions.PersistenciaException;
import br.edu.ifpb.biblioteca.warakkayu.service.AuthService;
import br.edu.ifpb.biblioteca.warakkayu.service.ObraService;


public class App 
{
    public static void main( String[] args )
    {           
        try {
            ObraDAO obraDAO = new ObraDAO();
            ObraService obraService = new ObraService(obraDAO);
            AuthService authService = new AuthService();
            Router navegador = new Router(obraService, authService);
            navegador.toGerenciamentoObras(null);
        } catch (PersistenciaException e) {
            JOptionPane.showMessageDialog(
                null, 
                "Erro ao carregar os dados.\n"+ e.getMessage(), 
                "Erro!!", 
                JOptionPane.ERROR_MESSAGE
            );
        }        
    }
}