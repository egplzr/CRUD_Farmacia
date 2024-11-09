package br.com.crudfarmacia.tablemodel;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import br.com.crudfarmacia.model.Farmaco;

public class FarmacoTableModel extends AbstractTableModel{
    private List<Farmaco> farmacos;
    private static String[] cabecalho = {"Nome", "Peso"};

    public FarmacoTableModel(List<Farmaco> farmacos){
        this.farmacos = farmacos;
    }

    @Override
    public int getRowCount() {
        return farmacos.size();
    }

    @Override
    public int getColumnCount() {
        return cabecalho.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Farmaco f = farmacos.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return f.getNome();
            case 1:
                return f.getPeso();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return cabecalho[column];
    }
    
}
