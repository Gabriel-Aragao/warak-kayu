package br.edu.ifpb.biblioteca.warakkayu;

import javax.swing.JOptionPane;

import br.edu.ifpb.biblioteca.warakkayu.emprestimo.dao.EmprestimoDAO;
import br.edu.ifpb.biblioteca.warakkayu.emprestimo.service.EmprestimoService;
import br.edu.ifpb.biblioteca.warakkayu.obra.dao.ObraDAO;
import br.edu.ifpb.biblioteca.warakkayu.obra.service.ObraService;
import br.edu.ifpb.biblioteca.warakkayu.shared.exceptions.PersistenciaException;
import br.edu.ifpb.biblioteca.warakkayu.shared.service.AuthService;
import br.edu.ifpb.biblioteca.warakkayu.usuario.dao.UsuarioDAO;
import br.edu.ifpb.biblioteca.warakkayu.usuario.service.UsuarioService;

public class App 
{
    public static void main( String[] args )
    {         
        try {
            ObraDAO obraDAO = new ObraDAO();
            ObraService obraService = new ObraService(obraDAO);
            
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            UsuarioService usuarioService = new UsuarioService(usuarioDAO);
            
            EmprestimoDAO emprestimoDAO = new EmprestimoDAO(usuarioService, obraService);
            EmprestimoService emprestimoService = new EmprestimoService(
                emprestimoDAO, obraService, usuarioService
            );

            AuthService authService = new AuthService(usuarioService);
            
            Router navegador = new Router(
                obraService, authService, emprestimoService, usuarioService 
            );
            navegador.toTelaLogin();
            
        } catch (PersistenciaException e) {
            e.printStackTrace(); 
            
            JOptionPane.showMessageDialog(
                null, 
                "Erro ao carregar os dados.\n"+ e.getMessage(), 
                "Erro!!", 
                JOptionPane.ERROR_MESSAGE
            );
        }       
    }
}