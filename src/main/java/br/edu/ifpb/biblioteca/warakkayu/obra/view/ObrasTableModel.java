package br.edu.ifpb.biblioteca.warakkayu.obra.view; // Coloque no mesmo pacote da sua tela

import br.edu.ifpb.biblioteca.warakkayu.obra.model.Artigo;
import br.edu.ifpb.biblioteca.warakkayu.obra.model.Livro;
import br.edu.ifpb.biblioteca.warakkayu.obra.model.Obra;
import br.edu.ifpb.biblioteca.warakkayu.obra.model.Revista;

import javax.swing.table.AbstractTableModel;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ObrasTableModel extends AbstractTableModel {

    private List<Obra> obras;
    private final String[] colunas = {"Código", "Título", "Autor", "Status", "Tipo", "Multa (R$)"};

    public ObrasTableModel() {
        this.obras = new ArrayList<>();
    }

    public void setObras(List<Obra> obras) {
        this.obras = obras;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return this.obras.size();
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
        Obra obra = this.obras.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return obra.getCodigo();
            case 1:
                return obra.getTitulo();
            case 2:
                return obra.getAutor();
            case 3:
                return obra.getStatus();
            case 4:
                // Lógica para identificar o tipo
                if (obra instanceof Livro) return "Livro";
                if (obra instanceof Artigo) return "Artigo";
                if (obra instanceof Revista) return "Revista";
                return "N/A";
            case 5:
                NumberFormat formatadorMoeda = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
                return formatadorMoeda.format(obra.getValorDaMulta());
            default:
                return null;
        }
    }
    
    public Obra getObraAt(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < this.obras.size()) {
            return this.obras.get(rowIndex);
        }
        return null;
    }
}