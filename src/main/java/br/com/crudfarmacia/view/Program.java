package br.com.crudfarmacia.view;

import java.awt.event.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;

import javax.print.attribute.standard.JobHoldUntil;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import com.formdev.flatlaf.themes.FlatMacLightLaf;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.util.Arrays;
import java.util.List;

import br.com.crudfarmacia.dao.EMfactory;
import br.com.crudfarmacia.dao.FarmaciaDao;
import br.com.crudfarmacia.model.Categoria;
import br.com.crudfarmacia.model.Farmaco;
import br.com.crudfarmacia.model.Medicamento;
import br.com.crudfarmacia.tablemodel.MedicamentoTableModel;

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
	private JComboBox<Categoria> cmbCategoriasCadastrarMedicamento;
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

	private Medicamento medicamento;
	private static FarmaciaDao dao = new FarmaciaDao(EMfactory.getEntityManager());
	private List<Farmaco> farmacosMedicamento;
	private List<Medicamento> medicamentos;

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

		// Painel cadastrar medicamentos
		painelCadastrarMedicamento = criarPainel();
		painelCadastrarMedicamento.setVisible(true);

		txtNomeCadastrarMedicamento = criarJTextField("Medicamento (pressione ENTER para pesquisar)", 50, 60, 390, 40);

		txtLaboratorioCadastrarMedicamento = criarJTextField("Laboratório", 50, 140, 390, 40);

		lblCategoriaCadastrarMedicamento = criarJLabel("Categoria", 50, 220, 100, 40);
		cmbCategoriasCadastrarMedicamento = new JComboBox();
		cmbCategoriasCadastrarMedicamento.setBounds(130, 220, 150, 40);

		Categoria[] categorias = { Categoria.COMPRIMIDO, Categoria.CAPSULA, Categoria.POMADA, Categoria.XAROPE };

		cmbCategoriasCadastrarMedicamento.setModel(new DefaultComboBoxModel<>(categorias));
		cmbCategoriasCadastrarMedicamento.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));

		txtNomeFarmacoCadastrar = criarJTextField("Fármaco", 50, 420, 590, 40);
		txtPesoFarmacoCadastrar = criarJTextField("Peso(mg)", 660, 420, 100, 40);
		painelTabelaFarmacoCadastrar = new JScrollPane();
		painelTabelaFarmacoCadastrar.setBounds(470, 60, 460, 290);
		painelTabelaFarmacoCadastrar.setBackground(Color.black);

		tblFarmacosCadastro = new JTable();
		tblFarmacosCadastro.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

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

		// Actions

		itemListagemMedicamento.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (painelListagem.isVisible()) {
					painelListagem.setVisible(false);
				} else {
					painelListagem.setVisible(true);

					try {
						medicamentos = dao.listar();
						tblListagemMedicamento.setModel(new MedicamentoTableModel(medicamentos));
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "Não foi possível listar os medicamentos");
					}
				}
			}
		});

		txtNomeCadastrarMedicamento.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					String nomeMedicamento = txtNomeCadastrarMedicamento.getText();

					try {
						medicamento = dao.buscar(nomeMedicamento);
						preencherCampos(medicamento);
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Usuário não encontrado");
						ex.printStackTrace();
					}
				} else {

				}

			}
		});

		tblListagemMedicamento.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				int linha = tblListagemMedicamento.getSelectedRow();

				if (linha >= 0) {
					medicamento = medicamentos.get(linha);
					preencherCampos(medicamento);
				}
			}

		});

		btnSalvarFarmaco.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String nome = txtNomeFarmacoCadastrar.getText();
				String strPeso = txtPesoFarmacoCadastrar.getText().replace(".", ",");

				try {
					double peso = Double.parseDouble(txtPesoFarmacoCadastrar.getText());
					strPeso = String.format("%.2fmg", peso);
					farmacosMedicamento.add(new Farmaco(nome, strPeso, medicamento));
					tblFarmacosCadastro.setModel(new FarmacoTableModel(farmacosMedicamento));
					txtPesoFarmacoCadastrar.setText("");
					txtNomeFarmacoCadastrar.setText("");
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Informe informações válidas!", "ERRO",
							JOptionPane.ERROR_MESSAGE);

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
		lbl.setOpaque(true);
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

	private void preencherCampos(Medicamento m) {
		if (medicamento != null) {
			painelListagem.setVisible(false);
			txtNomeCadastrarMedicamento.setText(m.getNome());
			txtLaboratorioCadastrarMedicamento.setText(m.getLaboratorio());
			cmbCategoriasCadastrarMedicamento.setSelectedItem(m.getCategoria());
			farmacosMedicamento = m.getPrincipioAtivo();
			tblFarmacosCadastro.setModel(new FarmacoTableModel(farmacosMedicamento));
		}

	}
}
