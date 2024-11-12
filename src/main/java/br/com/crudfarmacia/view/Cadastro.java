package br.com.crudfarmacia.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.w3c.dom.events.MouseEvent;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

import br.com.crudfarmacia.dao.EMfactory;
import br.com.crudfarmacia.dao.UsuarioDao;
import br.com.crudfarmacia.model.Medicamento;
import br.com.crudfarmacia.model.Usuario;
import br.com.crudfarmacia.tablemodel.FarmacoTableModel;
import br.com.util.Validador;

public class Cadastro extends JFrame {
    private JLabel tituloCadastro;
    private JTextField txtCpf;
    private JTextField txtSenha;
    private JButton btnCadastrar;
    private JLabel direcionarLogin;
    private Usuario usuario;
    private UsuarioDao dao = new UsuarioDao(EMfactory.getEntityManager());

    public Cadastro() {
        component();
    }

    public void component(){

        try{
            UIManager.setLookAndFeel(new FlatMacLightLaf());
        }catch(Exception e){
            e.printStackTrace();
        }   

        setResizable(false);
        setSize(400, 500);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        //criando components
        tituloCadastro = criarJLabel("Cadastro", 0, 30, 150, 40);
        centralizarComponente(tituloCadastro);
        tituloCadastro.setFont(new Font("Helvetica Neue", Font.BOLD, 30));

        txtCpf = criarJTextField("Cpf", 45, 110, 300, 40);

        txtSenha = criarJTextField("Senha",45 , 180, 300, 40);

        btnCadastrar = criarButton("Cadastrar", 95, 270, 200, 40);

        direcionarLogin = criarJLabel("Já possui cadastro? Faça Login.", 0, 400, 190, 40);
        centralizarComponente(direcionarLogin);
        direcionarLogin.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));

        //actions
        direcionarLogin.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                new Login();
                dispose();

            }

            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'mousePressed'");
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                direcionarLogin.setForeground(Color.black);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                direcionarLogin.setForeground(Color.blue);
            }
			
		});

        btnCadastrar.addActionListener(e -> {
            try {
                fazerCadastro();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        //adicionar componentes
        add(tituloCadastro);
        add(txtCpf);
        add(txtSenha);
        add(btnCadastrar);
        add(direcionarLogin);

        setVisible(true);
    }

    private void fazerCadastro() throws Exception {
        String cpf = txtCpf.getText();
        String senha = txtSenha.getText();

        if (cpf.equals("Cpf") || senha.equals("")) {
            JOptionPane.showMessageDialog(null, "Preencha os campos para continuar!");
        } else {
            if (Validador.validarCpf(cpf)) { // TODO completar com a lógica de validar cpf
                usuario = dao.buscar(cpf);

                if (usuario != null) {
                    JOptionPane.showMessageDialog(null, "Usuário já cadastrado!", "ERRO", JOptionPane.ERROR_MESSAGE);
                } else {
                    usuario = new Usuario(cpf, senha);
                    dao.inserir(usuario);
                    limpar();
                    JOptionPane.showMessageDialog(null, "Usuário cadastrado!", "", JOptionPane.PLAIN_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(null, "Esse cpf não é válido!", "ERRO", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void centralizarComponente(Component c) {
        int widht = c.getWidth();
        int centro = ((getWidth() - widht) / 2);
        c.setLocation(centro, c.getY());
    }

    private void limpar() {
        txtCpf.setText("Rg");
        txtSenha.setText("Senha");
        usuario = new Usuario();

        for (Component c : getContentPane().getComponents()) {
            if (c.getClass() == JTextField.class) {
                c.setForeground(Color.GRAY);
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
