package br.com.crudfarmacia.tablemodel;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.crudfarmacia.model.Usuario;

public class UsuarioTableModel extends AbstractTableModel{
    private List<Usuario> usuarios;
    private String[] cabecalho = {"Cpf", "Senha"};

    public UsuarioTableModel(List<Usuario> usuarios){
        this.usuarios = usuarios;
    }

    @Override
    public int getRowCount() {
        return usuarios.size();
    }

    @Override
    public int getColumnCount() {
        return cabecalho.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Usuario usuario = usuarios.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return usuario.getCpf();
            case 1:
                return usuario.getSenha();
            default:
                return null;
        }
    }
    
    @Override
    public String getColumnName(int column) {  
        return cabecalho[column];
    }
}
