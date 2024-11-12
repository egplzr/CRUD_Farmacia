package br.com.crudfarmacia.view;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import br.com.crudfarmacia.dao.EMfactory;
import br.com.crudfarmacia.dao.FarmaciaDao;
import br.com.crudfarmacia.model.Categoria;
import br.com.crudfarmacia.model.Farmaco;
import br.com.crudfarmacia.model.Medicamento;
import br.com.crudfarmacia.tablemodel.MedicamentoTableModel;
import br.com.crudfarmacia.tablemodel.FarmacoTableModel;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

public class ProgramGroupLayout extends JFrame {
    private JPanel fundo;
    private JMenuBar mnubar;
    private JMenu mnuMedicamento;
    private JMenuItem itemListagemMedicamento;
    
    private JPanel painelCadastrarMedicamento;
    private JTextField txtIdMedicamento, txtNomeCadastrarMedicamento, txtLaboratorioCadastrarMedicamento;
    private JLabel lblCategoriaCadastrarMedicamento;
    private JComboBox<Categoria> cmbCategoriasCadastrarMedicamento;
    private JTextField txtNomeFarmacoCadastrar, txtPesoFarmacoCadastrar;
    private JScrollPane painelTabelaFarmacoCadastrar;
    private JTable tblFarmacosCadastro, tblListagemMedicamento;
    private JButton btnSalvarFarmaco, btnCadastrarMedicamento, btnLimparDadosMedicamento, btnExcluirMedicameno;
    
    private List<Farmaco> farmacos = new ArrayList<>();
    private Medicamento medicamento;
    private List<Medicamento> medicamentos;
    private FarmaciaDao dao;
    
    public ProgramGroupLayout() {
        setupUI();
        setupDatabase();
        setupEventListeners();
    }

    private void setupUI() {
        try {
            UIManager.setLookAndFeel(new FlatMacLightLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        fundo = new JPanel(new BorderLayout());
        fundo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(fundo);

        mnubar = new JMenuBar();
        mnuMedicamento = new JMenu("Medicamentos");
        itemListagemMedicamento = new JMenuItem("Listar Medicamentos");

        mnuMedicamento.add(itemListagemMedicamento);
        mnubar.add(mnuMedicamento);
        setJMenuBar(mnubar);

        painelCadastrarMedicamento = new JPanel();
        painelCadastrarMedicamento.setLayout(new GridBagLayout());

        txtIdMedicamento = createTextField("Id", false);
        txtNomeCadastrarMedicamento = createTextField("Medicamento (pressione ENTER para pesquisar)", true);
        txtLaboratorioCadastrarMedicamento = createTextField("Laboratório", true);

        lblCategoriaCadastrarMedicamento = new JLabel("Categoria:");
        cmbCategoriasCadastrarMedicamento = new JComboBox<>(Categoria.values());

        txtNomeFarmacoCadastrar = createTextField("Fármaco", true);
        txtPesoFarmacoCadastrar = createTextField("Peso(mg)", true);

        tblFarmacosCadastro = new JTable(new FarmacoTableModel(new ArrayList<>()));
        painelTabelaFarmacoCadastrar = new JScrollPane(tblFarmacosCadastro);

        btnSalvarFarmaco = createButton("Salvar Fármaco");
        btnCadastrarMedicamento = createButton("Salvar Medicamento");
        btnLimparDadosMedicamento = createButton("Limpar Dados");
        btnExcluirMedicameno = createButton("Excluir Medicamento");

        tblListagemMedicamento = new JTable(new MedicamentoTableModel(new ArrayList<>()));
        
        // Layout customizado usando GridBagLayout para componentes redimensionáveis
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        addComponent(painelCadastrarMedicamento, txtIdMedicamento, gbc, 0, 0, 1);
        addComponent(painelCadastrarMedicamento, txtNomeCadastrarMedicamento, gbc, 1, 0, 2);
        addComponent(painelCadastrarMedicamento, txtLaboratorioCadastrarMedicamento, gbc, 0, 1, 3);
        addComponent(painelCadastrarMedicamento, lblCategoriaCadastrarMedicamento, gbc, 0, 2, 1);
        addComponent(painelCadastrarMedicamento, cmbCategoriasCadastrarMedicamento, gbc, 1, 2, 2);
        addComponent(painelCadastrarMedicamento, txtNomeFarmacoCadastrar, gbc, 0, 3, 2);
        addComponent(painelCadastrarMedicamento, txtPesoFarmacoCadastrar, gbc, 2, 3, 1);
        addComponent(painelCadastrarMedicamento, painelTabelaFarmacoCadastrar, gbc, 0, 4, 3);

        addComponent(painelCadastrarMedicamento, btnSalvarFarmaco, gbc, 0, 5, 1);
        addComponent(painelCadastrarMedicamento, btnCadastrarMedicamento, gbc, 1, 5, 1);
        addComponent(painelCadastrarMedicamento, btnExcluirMedicameno, gbc, 2, 5, 1);
        addComponent(painelCadastrarMedicamento, btnLimparDadosMedicamento, gbc, 1, 6, 1);

        fundo.add(painelCadastrarMedicamento, BorderLayout.CENTER);
        fundo.add(new JScrollPane(tblListagemMedicamento), BorderLayout.EAST);
    }

    private JTextField createTextField(String placeholder, boolean enabled) {
        JTextField field = new JTextField(placeholder);
        field.setEnabled(enabled);
        field.setForeground(Color.GRAY);
        field.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setText(placeholder);
                    field.setForeground(Color.GRAY);
                }
            }
        });
        return field;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return button;
    }

    private void addComponent(JPanel panel, Component comp, GridBagConstraints gbc, int x, int y, int width) {
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        panel.add(comp, gbc);
    }

    private void setupDatabase() {
        try {
            dao = new FarmaciaDao(EMfactory.getEntityManager());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não foi possível conectar ao banco de dados", "ERRO", JOptionPane.ERROR_MESSAGE);
        }
        medicamento = new Medicamento();
    }

    private void setupEventListeners() {
        itemListagemMedicamento.addActionListener(e -> toggleListagemMedicamento());
        btnSalvarFarmaco.addActionListener(e -> salvarFarmaco());
        btnCadastrarMedicamento.addActionListener(e -> cadastrarMedicamento());
        btnExcluirMedicameno.addActionListener(e -> excluirMedicamento());
        btnLimparDadosMedicamento.addActionListener(e -> limparCampos());

        tblFarmacosCadastro.getSelectionModel().addListSelectionListener(e -> carregarFarmacoSelecionado());
        tblFarmacosCadastro.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DELETE) {
                    excluirFarmacoSelecionado();
                }
            }
        });

        txtNomeCadastrarMedicamento.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    buscarMedicamentoPorNome();
                }
            }
        });
    }

    // Funções de manipulação de dados
    private void salvarFarmaco() {
        // Adicionar lógica para salvar farmaco
    }

    private void cadastrarMedicamento() {
        // Adicionar lógica para cadastrar medicamento
    }

    private void excluirMedicamento() {
        // Adicionar lógica para excluir medicamento
    }

    private void limparCampos() {
        // Adicionar lógica para limpar campos
    }

    private void carregarFarmacoSelecionado() {
        // Adicionar lógica para carregar farmaco selecionado
    }

    private void excluirFarmacoSelecionado() {
        // Adicionar lógica para excluir farmaco selecionado
    }

    private void buscarMedicamentoPorNome() {
        // Adicionar lógica para buscar medicamento por nome
    }

    private void toggleListagemMedicamento() {
        if (tblListagemMedicamento.isVisible()) {
            tblListagemMedicamento.setVisible(false);
        } else {
            tblListagemMedicamento.setVisible(true);
            try {
                medicamentos = dao.listar();
                tblListagemMedicamento.setModel(new MedicamentoTableModel(medicamentos));
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, "Não foi possível listar os medicamentos", "ERRO", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        new ProgramGroupLayout();
    }
}