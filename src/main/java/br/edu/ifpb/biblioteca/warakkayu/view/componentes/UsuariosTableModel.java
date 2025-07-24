package br.edu.ifpb.biblioteca.warakkayu.view.componentes;

import br.edu.ifpb.biblioteca.warakkayu.model.Usuario;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class UsuariosTableModel extends AbstractTableModel {

    private List<Usuario> usuarios;
    // Defini colunas que fazem sentido para um usuário, você pode alterar se precisar
    private final String[] colunas = {"Matrícula", "Nome", "Email", "Telefone", "Status"};

    public UsuariosTableModel() {
        this.usuarios = new ArrayList<>();
    }

    /**
     * Define a lista de usuários a ser exibida na tabela e notifica a JTable sobre a mudança.
     * @param usuarios A lista de usuários.
     */
    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
        fireTableDataChanged(); // Notifica a tabela que os dados mudaram
    }

    @Override
    public int getRowCount() {
        return this.usuarios.size();
    }

    @Override
    public int getColumnCount() {
        return this.colunas.length;
    }

    @Override
    public String getColumnName(int column) {
        return this.colunas[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Usuario usuario = this.usuarios.get(rowIndex);
        
        switch (columnIndex) {
            case 0:
                return usuario.getMatricula();
            case 1:
                return usuario.getNome();
            case 2:
                return usuario.getEmail();
            case 3:
                return usuario.getTelefone();
            case 4:
                return usuario.getStatus();
            default:
                // Retorna null para qualquer coluna inesperada
                return null;
        }
    }
    
    /**
     * Método auxiliar para obter o objeto Usuario completo de uma linha específica da tabela.
     * Essencial para ações como "Editar" ou "Excluir".
     * @param rowIndex O índice da linha.
     * @return O objeto Usuario correspondente à linha.
     */
    public Usuario getUsuarioAt(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < this.usuarios.size()) {
            return this.usuarios.get(rowIndex);
        }
        return null;
    }
}
