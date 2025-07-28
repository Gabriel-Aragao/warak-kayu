package br.edu.ifpb.biblioteca.warakkayu;

import javax.swing.JOptionPane;

import org.mindrot.jbcrypt.BCrypt;

import br.edu.ifpb.biblioteca.warakkayu.emprestimo.dao.EmprestimoDAO;
import br.edu.ifpb.biblioteca.warakkayu.emprestimo.service.EmprestimoService;
import br.edu.ifpb.biblioteca.warakkayu.usuario.model.TipoUsuario;
import br.edu.ifpb.biblioteca.warakkayu.usuario.model.Usuario;
import br.edu.ifpb.biblioteca.warakkayu.obra.dao.ObraDAO;
import br.edu.ifpb.biblioteca.warakkayu.obra.service.ObraService;
import br.edu.ifpb.biblioteca.warakkayu.pagamento.dao.PagamentoDao;
import br.edu.ifpb.biblioteca.warakkayu.pagamento.service.PagamentoService;
import br.edu.ifpb.biblioteca.warakkayu.relatorio.service.RelatorioService;
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
            verificarECriarAdminPadrao(usuarioService);
            
            EmprestimoDAO emprestimoDAO = new EmprestimoDAO(usuarioService, obraService);
            EmprestimoService emprestimoService = new EmprestimoService(
                emprestimoDAO, obraService, usuarioService
            );
            
            AuthService authService = new AuthService(usuarioService);
            
            PagamentoDao pagamentoDao = new PagamentoDao(emprestimoService, usuarioService);
            PagamentoService pagamentoService = new PagamentoService(pagamentoDao);
            
            RelatorioService relatorioService = new RelatorioService(emprestimoDAO, pagamentoDao);

            Router navegador = new Router(
                obraService, authService, emprestimoService, 
                usuarioService, relatorioService, pagamentoService
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

    private static void verificarECriarAdminPadrao(UsuarioService usuarioService) {
        try {
            if (usuarioService.list().isEmpty()) {
                String nome = "Admin";
                String matricula = "admin";
                String telefone = "+0 (00) 00000-0000";
                String senha = BCrypt.hashpw("admin", BCrypt.gensalt());
                TipoUsuario tipo = TipoUsuario.ADMIN;
                Usuario usuario = new Usuario(matricula, nome, matricula, telefone, tipo);
                usuario.setSenha(senha);
                usuarioService.add(usuario);
                JOptionPane.showMessageDialog(
                    null, 
                    "Matrícula: admin\nSenha: admin", 
                    "Primeiro Acesso", 
                    JOptionPane.INFORMATION_MESSAGE
                );
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                null, 
                "Ocorreu um erro ao criar o usuário padrão.", 
                "Erro de Inicialização", 
                JOptionPane.ERROR_MESSAGE
            );
            System.exit(1);
        }
    }
}