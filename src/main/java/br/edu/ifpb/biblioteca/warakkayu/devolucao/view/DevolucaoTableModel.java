package br.edu.ifpb.biblioteca.warakkayu.devolucao.view;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.table.AbstractTableModel;
import br.edu.ifpb.biblioteca.warakkayu.emprestimo.model.Emprestimo;

public class DevolucaoTableModel extends AbstractTableModel {
    
    private List<Emprestimo> emprestimos;
    private final String[] colunas = {"Usuário", "Obra", "Multa (R$)"};

    public DevolucaoTableModel() {
        this.emprestimos = new ArrayList<>();
    }

    public void setEmprestimos(List<Emprestimo> emprestimos) {
        this.emprestimos = emprestimos;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return this.emprestimos.size();
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
        Emprestimo emprestimo = this.emprestimos.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return emprestimo.getUsuario().getNome();
            case 1:
                return emprestimo.getObra().getTitulo();
            case 2:
                NumberFormat formatadorMoeda = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
                return formatadorMoeda.format(emprestimo.calcularMulta());
            default:
                return null;
        }
    }
    
    public Emprestimo getEmprestimoAt(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < this.emprestimos.size()) {
            return this.emprestimos.get(rowIndex);
        }
        return null;
    }
}