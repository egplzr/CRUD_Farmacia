package br.com.crudfarmacia.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.print.attribute.standard.JobOriginatingUserName;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

import br.com.crudfarmacia.dao.EMfactory;
import br.com.crudfarmacia.dao.UsuarioDao;
import br.com.crudfarmacia.model.Medicamento;
import br.com.crudfarmacia.model.Usuario;
import br.com.crudfarmacia.tablemodel.FarmacoTableModel;

public class Login extends JFrame{
    private JLabel tituloLogin;
    private JTextField txtCpf;
    private JTextField txtSenha;
    private JButton btnLogin;
    private JLabel direcionarCadastro;
    private Usuario usuario;
	private UsuarioDao dao = new UsuarioDao(EMfactory.getEntityManager());

    public Login(){
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
        tituloLogin = criarJLabel("Login", 0, 30, 100, 40);
        centralizarComponente(tituloLogin);
        tituloLogin.setFont(new Font("Helvetica Neue", Font.BOLD, 30));

        txtCpf = criarJTextField("Cpf", 45, 110, 300, 40);

        txtSenha = criarJTextField("Senha",45 , 180, 300, 40);

        btnLogin = criarButton("Entrar", 95, 270, 200, 40);

        direcionarCadastro = criarJLabel("Não possui login? Faça cadastro.", 0, 400, 190, 40);
        centralizarComponente(direcionarCadastro);
        direcionarCadastro.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
		direcionarCadastro.setCursor(new Cursor(HAND_CURSOR));

		//actions
		btnLogin.addActionListener(e -> {
			try {
				fazerLogin();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});

		direcionarCadastro.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				new Cadastro();
				dispose();
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				direcionarCadastro.setForeground(Color.blue);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				direcionarCadastro.setForeground(Color.black);
			}
			
		});

        //adicionar componentes
        add(tituloLogin);
        add(txtCpf);
        add(txtSenha);
        add(btnLogin);
        add(direcionarCadastro);

        setVisible(true);
    }

	private void fazerLogin() throws Exception{
		String cpf = txtCpf.getText();
		String senha = txtSenha.getText();

		if(cpf.equals("Cpf") || senha.equals("")){
			JOptionPane.showMessageDialog(null, "Preencha os campos para continuar!");
		}else{
			if(cpf.equals("admin") && senha.equals("123")){
				new PaginaAdministrador();
				dispose();
			}else{
				if(true){ //TODO completar com a lógica de validar cpf
					usuario = dao.buscar(cpf);
					
					if(usuario != null){
						if(usuario.getCpf().equals(cpf) && usuario.getSenha().equals(senha)){
							new Program();
							dispose();
						}else{
							JOptionPane.showMessageDialog(null, "Senha incorreta!", "ERRO", JOptionPane.ERROR_MESSAGE);
						}
					}else{
						JOptionPane.showMessageDialog(null, "Usuário não existe!", "ERRO", JOptionPane.ERROR_MESSAGE);
					}
		
				}else{
					JOptionPane.showMessageDialog(null, "Esse cpf não é válido!", "ERRO", JOptionPane.ERROR_MESSAGE);
				}
			}
			}
	}

    private void centralizarComponente(Component c){
        int widht = c.getWidth();
        int centro = ((getWidth() - widht) / 2);
        c.setLocation(centro, c.getY());
    }

    private void limpar() {
		txtCpf.setText("Rg");
		txtSenha.setText("Senha");
		usuario = new Usuario();

		for(Component c : getContentPane().getComponents()){
			if(c.getClass() == JTextField.class){
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

    public static void main(String[] args) {
        new Login();
    }
}
