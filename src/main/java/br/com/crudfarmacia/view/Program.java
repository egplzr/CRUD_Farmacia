package br.com.crudfarmacia.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;

import javax.print.attribute.standard.JobHoldUntil;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;

import br.com.crudfarmacia.tablemodel.MedicamentoTableModel;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

import br.com.crudfarmacia.tablemodel.FarmacoTableModel;

public class Program extends JFrame {
	private JPanel fundo;

	private JMenuBar mnubar;
	private JMenu mnuMedicamento;
	private JMenuItem itemListagemMedicamento;

	private JPanel painelCadastrarMedicamento;
	private JTextField txtNomeCadastrarMedicamento;
	private JTextField txtLaboratorioCadastrarMedicamento;
	private JLabel lblCategoriaCadastrarMedicamento;
	private JComboBox<String> cmbCategoriasCadastrarMedicamento;
	private JTextField txtNomeFarmacoCadastrar;
	private JTextField txtPesoFarmacoCadastrar;
	private JScrollPane painelTabelaFarmacoCadastrar;
	private JTable tblFarmacosCadastro;
	private JButton btnSalvarFarmaco;
	private JButton btnCadastrarMedicamento;
	private JButton btnLimparDadosMedicamento;
	private JButton btnExcluirMedicameno;
	
	private JScrollPane painelListagem;
	private JTable tblListagemMedicamento;

	public Program() {
		components();
	}

	private void components() {

		try {
			UIManager.setLookAndFeel(new FlatMacLightLaf());
		} catch (Exception e) {
			// TODO: handle exception
		}

		setResizable(false);
		setSize(1000, 600);
		setLocationRelativeTo(null);

		fundo = criarPainel();
		fundo.setVisible(true);

		mnubar = new JMenuBar();
		mnubar.setBounds(0, 0, fundo.getWidth(), 30);

		mnuMedicamento = new JMenu("Medicamentos");
		itemListagemMedicamento = new JMenuItem("Listar Medicamentos");

		//Painel cadastrar medicamentos
		painelCadastrarMedicamento = criarPainel();
		painelCadastrarMedicamento.setVisible(true);
		
		txtNomeCadastrarMedicamento = criarJTextField("Medicamento", 50, 60, 390, 40);

		txtLaboratorioCadastrarMedicamento = criarJTextField("Laboratório" , 50, 140, 390, 40);
		
		lblCategoriaCadastrarMedicamento = criarJLabel("Categoria", 50, 220, 100, 40);
		cmbCategoriasCadastrarMedicamento = new JComboBox();
		cmbCategoriasCadastrarMedicamento.setBounds(130, 220, 150, 40);
		
		txtNomeFarmacoCadastrar = criarJTextField("Fármaco",50, 420, 590, 40);
		txtPesoFarmacoCadastrar = criarJTextField("Peso(mg)", 660, 420, 100, 40);
		painelTabelaFarmacoCadastrar = new JScrollPane();
		painelTabelaFarmacoCadastrar.setBounds(470, 60, 460,290);
		painelTabelaFarmacoCadastrar.setBackground(Color.black);

		tblFarmacosCadastro = new JTable();

		painelTabelaFarmacoCadastrar.getViewport().add(tblFarmacosCadastro);
		tblFarmacosCadastro.setModel(new FarmacoTableModel(new ArrayList<>()));

		btnSalvarFarmaco = criarButton("Salvar farmaco", 780, 420, 150, 40);

		btnCadastrarMedicamento = criarButton("Salvar", 50, 310, 100, 40);
		btnExcluirMedicameno = criarButton("Excluir", 170, 310, 100, 40);
		btnLimparDadosMedicamento = criarButton("Limpar", 290, 310, 100, 40);

		painelListagem = new JScrollPane();
		painelListagem.setBounds(fundo.getWidth() / 2, 30, fundo.getWidth() / 2, fundo.getHeight() - 30);
		tblListagemMedicamento = new JTable(new MedicamentoTableModel(new ArrayList<>()));
		painelListagem.setVisible(false);

		painelListagem.getViewport().add(tblListagemMedicamento);

		painelCadastrarMedicamento.add(painelListagem);
		painelCadastrarMedicamento.add(btnLimparDadosMedicamento);
		painelCadastrarMedicamento.add(btnExcluirMedicameno);
		painelCadastrarMedicamento.add(btnCadastrarMedicamento);
		painelCadastrarMedicamento.add(btnSalvarFarmaco);
		painelCadastrarMedicamento.add(painelTabelaFarmacoCadastrar);
		painelCadastrarMedicamento.add(txtPesoFarmacoCadastrar);
		painelCadastrarMedicamento.add(txtNomeFarmacoCadastrar);
		painelCadastrarMedicamento.add(cmbCategoriasCadastrarMedicamento);
		painelCadastrarMedicamento.add(lblCategoriaCadastrarMedicamento);
		painelCadastrarMedicamento.add(txtLaboratorioCadastrarMedicamento);
		painelCadastrarMedicamento.add(txtNomeCadastrarMedicamento);

		//Actions

		itemListagemMedicamento.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(painelListagem.isVisible()){
					painelListagem.setVisible(false);
				}else{
					painelListagem.setVisible(true);
				}
			}
		});

		// adicionando components
		getContentPane().add(fundo);
		fundo.add(mnubar);
		fundo.add(painelCadastrarMedicamento);

		mnubar.add(mnuMedicamento);
		mnuMedicamento.add(itemListagemMedicamento);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Program();
	}

	private JPanel criarPainel() {
		JPanel painel = new JPanel();
		painel.setSize(getWidth(), getHeight());
		painel.setLayout(null);
		painel.setVisible(false);

		return painel;
	}

	private void centralizarComponente(JComponent c) {
		int widthComponent = c.getWidth();
		int widthTela = getWidth();
		int widthCentralizado = (widthTela - widthComponent) / 2;
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
				if(tf.getText().equals(placeholder)){
					tf.setText("");
					tf.setForeground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if(tf.getText().equals("")){
					tf.setText(placeholder);
					tf.setForeground(Color.gray);
				}
			}
		});

		return tf;
	}
	
	private JLabel criarJLabel(String txt, int dEsq, int dTopo, int width, int heigth) {
		JLabel lbl = new JLabel(txt);
		lbl.setBounds(dEsq, dTopo, width, heigth);
		lbl.setOpaque(true);
		lbl.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));

		return lbl;

	}

	private JButton criarButton(String txt, int dEsq, int dTopo, int width, int heigth){
		JButton b = new JButton(txt);
		b.setBounds(dEsq, dTopo, width, heigth);
		b.setCursor(new Cursor(HAND_CURSOR));
		b.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
		return b;
	}
}
