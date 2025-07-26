package br.edu.ifpb.biblioteca.warakkayu.usuario.view;

import javax.swing.table.AbstractTableModel;

import br.edu.ifpb.biblioteca.warakkayu.usuario.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuariosTableModel extends AbstractTableModel {

    private List<Usuario> usuarios;
    // Defini colunas que fazem sentido para um usuário, você pode alterar se precisar
    private final String[] colunas = {"Matrícula", "Nome", "Email", "Telefone"};

    public UsuariosTableModel() {
        this.usuarios = new ArrayList<>();
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
        fireTableDataChanged();
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
            default:
                return null;
        }
    }
    
    public Usuario getUsuarioAt(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < this.usuarios.size()) {
            return this.usuarios.get(rowIndex);
        }
        return null;
    }
}
