package br.com.crudfarmacia.tablemodel;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.crudfarmacia.model.Medicamento;


public class MedicamentoTableModel extends AbstractTableModel {
    private List<Medicamento> medicamentos;
    private String[] cabecalho = {"Nome", "Laboratório", "Categoria"};

    public MedicamentoTableModel(List<Medicamento> medicamentos){
        this.medicamentos = medicamentos;
    }

    @Override
    public int getRowCount() {
        return medicamentos.size();
    }

    @Override
    public int getColumnCount() {
        return cabecalho.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Medicamento m = medicamentos.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return m.getNome();
            case 1:
                return m.getLaboratorio();
            case 2:
                return m.getCategoria();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return cabecalho[column];
    }
    
}
