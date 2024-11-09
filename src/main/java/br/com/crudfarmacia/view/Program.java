package br.com.crudfarmacia.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import com.formdev.flatlaf.themes.FlatMacLightLaf;

public class Program extends JFrame {
	private JPanel fundo;
	private JMenuBar mnubar;
	private JMenu mnuMedicamento;
	private JMenuItem itemCadastrarMedicamento;
	private JMenuItem itemListarMedicamento;
	private JMenuItem itemExcluirMedicamento;
	private JMenuItem itemAlterarNMedicamento;

	private JPanel painelCadastrarMedicamento;
	private JLabel lblNomeCadastrarMedicamento;
	private JTextField txtNomeCadastrarMedicamento;
	private JLabel lblLaboratorioCadastrarMedicamento;
	private JTextField txtLaboratorioCadastrarMedicamento;
	private JLabel lblCategoriaCadastrarMedicamento;
	private JComboBox<String> cmbCategoriasCadastrarMedicamento;
	//TODO Verificar qual tipo sera utilizado para representar os farmacos
	private JComboBox<String> cmbFarmacosCadastrarMedicamento;
	
	private JPanel painelListarMedicamento;
	private JPanel painelExcluirMedicamento;
	private JPanel painelAlterarMedicamento;

	private JMenu mnuFarmaco;
	private JMenuItem itemCadastrarFarmaco;
	private JMenuItem itemListarFarmaco;
	private JMenuItem itemExcluirFarmaco;
	private JMenuItem itemAlterarFarmaco;

	private JPanel painelCadastrarFarmaco;
	private JPanel painelListarFarmaco;
	private JPanel painelExcluirFarmaco;
	private JPanel painelAlterarFarmaco;

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

		//															AREA MEDICAMENTOS
		mnuMedicamento = new JMenu("Medicamentos");

		itemCadastrarMedicamento = new JMenuItem("Cadastrar");
		itemAlterarNMedicamento = new JMenuItem("Alterar");
		itemExcluirMedicamento = new JMenuItem("Excluir");
		itemListarMedicamento = new JMenuItem("Listar");
		
		//Painel cadastrar medicamentos
		painelCadastrarMedicamento = criarPainel();
		painelCadastrarMedicamento.setVisible(true);
		
		lblNomeCadastrarMedicamento = criarJLabel("Nome do medicamento", 50, 50, 200, 40);
		txtNomeCadastrarMedicamento = criarJTextField(50, 100, 880, 40);
		
		lblLaboratorioCadastrarMedicamento = criarJLabel("Nome do laboratório", 50 , 160, 200, 40);
		txtLaboratorioCadastrarMedicamento = criarJTextField(50, 210, 880, 40);
		
		lblCategoriaCadastrarMedicamento = criarJLabel("Categoria", 50, 270, 100, 40);
		cmbCategoriasCadastrarMedicamento = new JComboBox();
		cmbCategoriasCadastrarMedicamento.setBounds(130, 270, 200, 40);
		//TODO finalizar o front do painelCadastroMedicamento
		
		painelCadastrarMedicamento.add(cmbCategoriasCadastrarMedicamento);
		painelCadastrarMedicamento.add(lblCategoriaCadastrarMedicamento);
		painelCadastrarMedicamento.add(txtLaboratorioCadastrarMedicamento);
		painelCadastrarMedicamento.add(lblLaboratorioCadastrarMedicamento);
		painelCadastrarMedicamento.add(txtNomeCadastrarMedicamento);
		painelCadastrarMedicamento.add(lblNomeCadastrarMedicamento);
		
		painelListarMedicamento = criarPainel();
		painelExcluirMedicamento = criarPainel();
		painelAlterarMedicamento = criarPainel();

		// area farmacos
		mnuFarmaco = new JMenu("Farmacos");

		itemAlterarFarmaco = new JMenuItem("Alterar");
		itemCadastrarFarmaco = new JMenuItem("Cadastrar");
		itemExcluirFarmaco = new JMenuItem("Excluir");
		itemListarFarmaco = new JMenuItem("Listar");

		// action listenners

		itemCadastrarMedicamento.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setarFoco(painelCadastrarMedicamento);

			}
		});

		itemExcluirMedicamento.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setarFoco(painelExcluirMedicamento);

			}
		});

		itemAlterarNMedicamento.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setarFoco(painelAlterarMedicamento);

			}
		});

		itemListarMedicamento.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setarFoco(painelListarMedicamento);

			}
		});

		// adicionando components
		getContentPane().add(fundo);
		fundo.add(mnubar);
		fundo.add(painelCadastrarMedicamento);
		fundo.add(painelListarMedicamento);
		fundo.add(painelAlterarMedicamento);
		fundo.add(painelExcluirMedicamento);
		fundo.add(itemAlterarFarmaco);
		fundo.add(itemCadastrarFarmaco);
		fundo.add(itemExcluirFarmaco);
		fundo.add(itemListarFarmaco);

		mnubar.add(mnuMedicamento);
		mnubar.add(mnuFarmaco);

		mnuMedicamento.add(itemCadastrarMedicamento);
		mnuMedicamento.add(itemExcluirMedicamento);
		mnuMedicamento.add(itemListarMedicamento);
		mnuMedicamento.add(itemAlterarNMedicamento);

		mnuFarmaco.add(itemCadastrarFarmaco);
		mnuFarmaco.add(itemExcluirFarmaco);
		mnuFarmaco.add(itemListarFarmaco);
		mnuFarmaco.add(itemAlterarFarmaco);

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

	private void setarFoco(JComponent c) {
		for (Component c1 : fundo.getComponents()) {
			if (!c1.equals(c) && c1.getClass() == JPanel.class) {
				c1.setVisible(false);
			}
		}

		c.setVisible(true);
	}
	
	private JTextField criarJTextField(int dEsq, int dTopo, int width, int heigth) {
		JTextField txt = new JTextField();
		txt.setBounds(dEsq, dTopo, width, heigth);
		txt.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
		return txt;
	}
	
	private JLabel criarJLabel(String txt, int dEsq, int dTopo, int width, int heigth) {
		JLabel lbl = new JLabel(txt);
		lbl.setBounds(dEsq, dTopo, width, heigth);
		lbl.setOpaque(true);
		lbl.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
		return lbl;

	}

}
