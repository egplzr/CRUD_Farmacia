package br.com.crudfarmacia.view;

import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.formdev.flatlaf.themes.FlatMacLightLaf;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
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
	private JTextField txtIdMedicamento;
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
	private List<Farmaco> farmacos = new ArrayList<>();
	private JScrollPane painelListagem;
	private JTable tblListagemMedicamento;
	private Medicamento medicamento;
	private FarmaciaDao dao;
	private Farmaco farmaco;
	private List<Medicamento> medicamentos;

	public static void main(String[] args) {
		new Program();
	}

	public Program() {
		components();
	}

	private void components() {

		try {
			UIManager.setLookAndFeel(new FlatMacLightLaf());
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			dao = new FarmaciaDao(EMfactory.getEntityManager());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Não foi possível conectar ao banco de dados", "ERRO",
					JOptionPane.ERROR_MESSAGE);
		}

		medicamento = new Medicamento();

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

		txtIdMedicamento = criarJTextField("Id", 50, 60, 50, 40);
		txtIdMedicamento.setEditable(true);
		txtIdMedicamento.setEnabled(true);

		txtNomeCadastrarMedicamento = criarJTextField("Medicamento (pressione ENTER para pesquisar)", 120, 60, 320, 40);

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

		btnSalvarFarmaco = criarButton("Salvar Fármaco", 780, 420, 150, 40);

		btnCadastrarMedicamento = criarButton("Salvar", 50, 310, 100, 40);
		btnExcluirMedicameno = criarButton("Excluir", 170, 310, 100, 40);
		btnLimparDadosMedicamento = criarButton("Limpar", 290, 310, 100, 40);

		painelListagem = new JScrollPane();
		painelListagem.setBounds(0, 30, fundo.getWidth() / 2, fundo.getHeight() - 30);
		tblListagemMedicamento = new JTable(new MedicamentoTableModel(new ArrayList<>()));
		tblListagemMedicamento.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
		painelCadastrarMedicamento.add(txtIdMedicamento);
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
						JOptionPane.showMessageDialog(null, "Não foi possível listar os medicamentos", "ERRO",
								JOptionPane.ERROR_MESSAGE);
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
						preencherCampos();
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Usuário não encontrado", "ERRO",
								JOptionPane.ERROR_MESSAGE);
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
					preencherCampos();
				}
			}

		});

		btnSalvarFarmaco.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String nome = txtNomeFarmacoCadastrar.getText();
				String strPeso = txtPesoFarmacoCadastrar.getText().replace(",", ".");
				int linha = tblFarmacosCadastro.getSelectedRow();

				if (txtNomeCadastrarMedicamento.getText().equals("Medicamento (pressione ENTER para pesquisar)")
						&& txtLaboratorioCadastrarMedicamento.getText().equals("Laboratório")) {
					JOptionPane.showMessageDialog(null, "Selecione ou preencha os dados do medicamento primeiro!",
							"ERRO", JOptionPane.ERROR_MESSAGE);
				} else {
					try {
						double peso = Double.parseDouble(strPeso);
						strPeso = String.format("%.2fmg", peso);

						if (linha >= 0) {
							farmacos.get(linha).setNome(nome);
							farmacos.get(linha).setPeso(strPeso);
						} else {
							Farmaco farmaco = new Farmaco(nome, strPeso, medicamento);
							farmacos.add(farmaco);
						}

						tblFarmacosCadastro.setModel(new FarmacoTableModel(farmacos));

						txtNomeFarmacoCadastrar.setText("Fármaco");
						txtNomeFarmacoCadastrar.setForeground(Color.gray);
						txtPesoFarmacoCadastrar.setText("Peso(mg)");
						txtPesoFarmacoCadastrar.setForeground(Color.gray);

					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Digite informações válidas!", "ERRO",
								JOptionPane.ERROR_MESSAGE);
						ex.printStackTrace();

					}
				}

			}

		});

		tblFarmacosCadastro.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				int linha = tblFarmacosCadastro.getSelectedRow();

				if (linha >= 0) {
					Farmaco f = farmacos.get(linha);
					txtNomeFarmacoCadastrar.setText(f.getNome());
					txtPesoFarmacoCadastrar.setText(f.getPeso().replace("mg", ""));
				}

			}

		});

		tblFarmacosCadastro.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_DELETE) {
					Farmaco f = farmacos.get(tblFarmacosCadastro.getSelectedRow());
					for (int i = 0; i < farmacos.size(); i++) {
						if (farmacos.get(i).getNome().equals(f.getNome())) {
							farmacos.remove(i);
							tblFarmacosCadastro.setModel(new FarmacoTableModel(farmacos));
							txtNomeFarmacoCadastrar.setText("");
							txtPesoFarmacoCadastrar.setText("");

							txtNomeFarmacoCadastrar.setText("Fármaco");
							txtNomeFarmacoCadastrar.setForeground(Color.gray);
							txtPesoFarmacoCadastrar.setText("Peso(mg)");
							txtPesoFarmacoCadastrar.setForeground(Color.gray);
							break;
						}
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

		});

		btnCadastrarMedicamento.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String nome = txtNomeCadastrarMedicamento.getText();
				String laboratorio = txtLaboratorioCadastrarMedicamento.getText();
				Categoria categoria = (Categoria) cmbCategoriasCadastrarMedicamento.getSelectedItem();
				String id = txtIdMedicamento.getText();

				if (!nome.equals("Medicamento (pressione ENTER para pesquisar)")
						&& !laboratorio.equals("Laboratório")) {
					if (farmacos.size() == 0) {
						JOptionPane.showMessageDialog(null, "Adicione ao menos 1 farmaco para salvar", "ERRO",
								JOptionPane.ERROR_MESSAGE);
					} else {
						if (id.equals("") || id.equals("Id")) {
							try {
								medicamento.setNome(nome);
								medicamento.setCategoria(categoria);
								medicamento.setLaboratorio(laboratorio);
								medicamento.setPrincipioAtivo(farmacos);
								dao.inserir(medicamento);
								limpar();
								medicamento = new Medicamento();
								farmacos = new ArrayList<>();

							} catch (Exception e1) {
								JOptionPane.showMessageDialog(null,
										"Não foi possível cadastrar o medicamento\nERRO: " + e1.getMessage(), "ERRO",
										JOptionPane.ERROR_MESSAGE);
							}
						} else {
							try {
								medicamento = dao.buscar(Long.parseLong(id));
								medicamento.setNome(nome);
								medicamento.setCategoria(categoria);
								medicamento.setLaboratorio(laboratorio);
								medicamento.setPrincipioAtivo(farmacos);
								dao.alterar(medicamento);
								limpar();
								medicamento = new Medicamento();
								farmacos = new ArrayList<>();

							} catch (Exception e1) {
								JOptionPane.showMessageDialog(null, "Não foi possível atualizar o medicamento", "ERRO",
										JOptionPane.ERROR_MESSAGE);
							}
							try {
								tblListagemMedicamento.setModel(new MedicamentoTableModel(dao.listar()));
							} catch (Exception e1) {
								e1.printStackTrace();
							}

						}
					}

				} else {
					JOptionPane.showMessageDialog(null, "Preencha os campos vazios!", "ERRO",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		btnLimparDadosMedicamento.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				limpar();
			}

		});

		btnExcluirMedicameno.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String id = txtIdMedicamento.getText();

				if (id.equals("") || id.equals("Id")) {
					JOptionPane.showMessageDialog(null, "Selecione um medicamento ta tabela para excluir");
				} else {
					try {
						dao.excluir(Long.parseLong(id));
						limpar();
						medicamento = new Medicamento();
						farmacos = new ArrayList<>();
						tblListagemMedicamento.setModel(new MedicamentoTableModel(dao.listar()));
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Não foi possível excluir o medicamento");
					}
				}
			}

		});

		txtIdMedicamento.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						int id = Integer.parseInt(txtIdMedicamento.getText());
						medicamento = dao.buscar(id);
						preencherCampos();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Insira um valor válido!");
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
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

	private JPanel criarPainel() {
		JPanel painel = new JPanel();
		painel.setSize(getWidth(), getHeight());
		painel.setLayout(null);
		painel.setVisible(false);

		return painel;
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

	private void preencherCampos() {
		if (medicamento != null) {
			painelListagem.setVisible(false);
			txtIdMedicamento.setEnabled(false);
			txtIdMedicamento.setText(String.format("%d", medicamento.getId()));
			txtNomeCadastrarMedicamento.setText(medicamento.getNome());
			txtNomeCadastrarMedicamento.requestFocus();
			txtLaboratorioCadastrarMedicamento.setText(medicamento.getLaboratorio());
			cmbCategoriasCadastrarMedicamento.setSelectedItem(medicamento.getCategoria());
			farmacos = medicamento.getPrincipioAtivo();
			tblFarmacosCadastro.setModel(new FarmacoTableModel(farmacos));
		}

	}

	private boolean contemFarmaco(String nome) {
		for (Farmaco f : farmacos) {
			if (f.getNome().equals(nome)) {
				return true;
			}
		}

		return false;
	}

	private void limpar() {
		txtNomeCadastrarMedicamento.setText("Medicamento (pressione ENTER para pesquisar)");
		txtIdMedicamento.setText("Id");
		txtLaboratorioCadastrarMedicamento.setText("Laboratório");
		txtNomeFarmacoCadastrar.setText("Fármaco");
		txtPesoFarmacoCadastrar.setText("Peso(mg)");
		medicamento = new Medicamento();
		farmacos = new ArrayList<>();
		txtIdMedicamento.setEnabled(true);
		tblFarmacosCadastro.setModel(new FarmacoTableModel(new ArrayList<>()));

		for (Component c : painelCadastrarMedicamento.getComponents()) {
			if (c.getClass() == JTextField.class) {
				c.setForeground(Color.GRAY);
			}
		}
	}
}
