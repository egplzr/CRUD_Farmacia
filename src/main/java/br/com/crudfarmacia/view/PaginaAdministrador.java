package br.com.crudfarmacia.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.List;
import java.awt.ScrollPane;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Component;

import com.formdev.flatlaf.themes.FlatMacLightLaf;

import br.com.crudfarmacia.dao.EMfactory;
import br.com.crudfarmacia.dao.UsuarioDao;
import br.com.crudfarmacia.model.Usuario;
import br.com.crudfarmacia.tablemodel.UsuarioTableModel;

public class PaginaAdministrador extends JFrame {
    private JTextField txtCpfUsuario;
    private JTextField txtSenhaUsuario;
    private JButton btnSalvar;
    private JButton btnExcluir;
    private JButton btnLimpar;
    private JScrollPane scrollPane;
    private JTable tabelaUsuario;
    private Usuario usuario;
    private java.util.List<Usuario> usuarios;
    private UsuarioDao dao = new UsuarioDao(EMfactory.getEntityManager());

    public PaginaAdministrador() {
        component();
    }

    public void component() {

        try {
            UIManager.setLookAndFeel(new FlatMacLightLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        setSize(400, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(Color.white);

        txtCpfUsuario = criarJTextField("Cpf", 40, 20, 300, 40);

        txtSenhaUsuario = criarJTextField("Senha", 40, 80, 300, 40);

        btnSalvar = criarButton("Salvar", 50, 140, 90, 40);

        btnExcluir = criarButton("Excluir", 145, 140, 90, 40);

        btnLimpar = criarButton("Limpar", 240, 140, 90, 40);

        try {
            usuarios = dao.listar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não foi possível listar os Usuários");
        }

        tabelaUsuario = new JTable(new UsuarioTableModel(usuarios));
        scrollPane = new JScrollPane();
        scrollPane.setBounds(40, 200, 300, 350);
        scrollPane.getViewport().add(tabelaUsuario);

        // actions
        tabelaUsuario.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                int linha = tabelaUsuario.getSelectedRow();

                if (linha >= 0) {
                    usuario = usuarios.get(linha);
                    txtCpfUsuario.setText(usuario.getCpf());
                    txtCpfUsuario.setEnabled(false);
                    txtSenhaUsuario.setText(usuario.getSenha());
                }
            }

        });

        btnSalvar.addActionListener(e -> {
            try {
                salvarUsuario();
            } catch (Exception e1) {
               JOptionPane.showMessageDialog(null, "Não foi possível salvar o usuário!", "ERRO", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnExcluir.addActionListener(e -> {
            try {
                excluirUsuario();
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, "Não foi possível excluir o usuário!", "ERRO", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnLimpar.addActionListener(e -> limpar());

        // adicionar componente
        add(btnExcluir);
        add(scrollPane);
        add(txtCpfUsuario);
        add(btnSalvar);
        add(btnLimpar);
        add(txtSenhaUsuario);
        setVisible(true);
    }

    private void excluirUsuario() throws Exception{
        String cpf = txtCpfUsuario.getText();

        dao.excluir(cpf);
        limpar();

        usuarios = dao.listar();
        tabelaUsuario.setModel(new UsuarioTableModel(usuarios));
    }

    private void salvarUsuario() throws Exception {
        String cpf = txtCpfUsuario.getText();
        String senha = txtSenhaUsuario.getText();
        Usuario u = dao.buscar(cpf);

        if (u == null) {
            dao.inserir(new Usuario(cpf, senha));
            JOptionPane.showMessageDialog(null, "Usuário cadastrado");
        } else {
            u.setCpf(cpf);
            u.setSenha(senha);
            dao.alterar(u);
        }

        usuarios = dao.listar();
        tabelaUsuario.setModel(new UsuarioTableModel(usuarios));
        limpar();
    }

    private void limpar() {
        txtCpfUsuario.setText("Rg");
        txtCpfUsuario.setEnabled(true);
        txtSenhaUsuario.setText("Senha");
        usuario = new Usuario();

        for (Component c : getContentPane().getComponents()) {
            if (c.getClass() == JTextField.class) {
                c.setForeground(Color.gray);
            }
        }
    }

    private JTextField criarJTextField(String placeholder, int dEsq, int dTopo, int width, int heigth) {
        JTextField tf = new JTextField();
        tf.setBounds(dEsq, dTopo, width, heigth);
        tf.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
        tf.setText(placeholder);
        tf.setForeground(Color.gray);

        tf.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (tf.getText().equals(placeholder)) {
                    tf.setText("");
                    tf.setForeground(Color.black);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (tf.getText().equals("")) {
                    tf.setText(placeholder);
                    tf.setForeground(Color.gray);
                }
            }
        });

        tf.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                tf.setForeground(Color.black);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {

            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });

        return tf;
    }

    private JLabel criarJLabel(String txt, int dEsq, int dTopo, int width, int heigth) {
        JLabel lbl = new JLabel(txt);
        lbl.setBounds(dEsq, dTopo, width, heigth);
        lbl.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));

        return lbl;

    }

    private JButton criarButton(String txt, int dEsq, int dTopo, int width, int heigth) {
        JButton b = new JButton(txt);
        b.setBounds(dEsq, dTopo, width, heigth);
        b.setCursor(new Cursor(HAND_CURSOR));
        b.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
        return b;
    }
}
