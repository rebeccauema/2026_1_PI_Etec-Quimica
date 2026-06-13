package br.com.pi_2026_1_etec.view.telas;

import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.util.List;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class TelaGerenciamentoDePergunta extends JFrame {

    private static final java.util.logging.Logger logger =
            java.util.logging.Logger.getLogger(TelaGerenciamentoDePergunta.class.getName());

    private final Color corPrincipal = new Color(146, 25, 19);

    private JButton jButtonAdicionarPergunta;
    private JButton jButtonEditar;
    private JButton jButtonFiltrar;
    private JButton jButtonVoltar;

    private JComboBox<String> jComboBoxDificuldade;
    private JComboBox<String> jComboBoxTema;

    private JPanel jPanel2;
    private JPanel jPanelPesquisar;

    private JScrollPane jScrollPane2;
    private JSpinner jSpinner1;
    private JTable jTablePerguntas;
    private JTextField jTextFieldPesquisarPergunta;

    public TelaGerenciamentoDePergunta() {
        initComponents();

        carregarPerguntas();
        carregarFiltros();

        ocultarColunaId();

        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    private void carregarFiltros() {
        jComboBoxTema.removeAllItems();
        jComboBoxTema.addItem("Todos");
        jComboBoxTema.addItem("Sistema");
        jComboBoxTema.addItem("Material");

        jComboBoxDificuldade.removeAllItems();
        jComboBoxDificuldade.addItem("Todas");
        jComboBoxDificuldade.addItem("Fácil");
        jComboBoxDificuldade.addItem("Médio");
        jComboBoxDificuldade.addItem("Difícil");
    }

    private void carregarPerguntas() {
        DefaultTableModel model = (DefaultTableModel) jTablePerguntas.getModel();
        model.setRowCount(0);

        String sql = "SELECT p.id_pergunta, p.texto AS enunciado, nv.nome AS dificuldade, '' AS criado_em " +
                "FROM pergunta p " +
                "LEFT JOIN nivel nv ON p.id_nivel = nv.id_nivel " +
                "ORDER BY p.id_pergunta DESC";

        try (java.sql.Connection conn = br.com.pi_2026_1_etec.config.ConexaoBD.obterConexao();
             java.sql.PreparedStatement ps = conn.prepareStatement(sql);
             java.sql.ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getString("enunciado"),
                        rs.getString("dificuldade"),
                        rs.getString("criado_em"),
                        rs.getInt("id_pergunta")
                });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Erro ao carregar perguntas: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void aplicarFiltro(String texto, String tema, String dificuldade) {
        DefaultTableModel model = (DefaultTableModel) jTablePerguntas.getModel();
        model.setRowCount(0);

        StringBuilder sql = new StringBuilder(
                "SELECT p.id_pergunta, p.texto AS enunciado, nv.nome AS dificuldade, '' AS criado_em " +
                        "FROM pergunta p " +
                        "LEFT JOIN nivel nv ON p.id_nivel = nv.id_nivel " +
                        "WHERE 1=1 "
        );

        List<Object> params = new java.util.ArrayList<>();

        if (texto != null && !texto.isBlank() && !texto.equals("Pesquisar pergunta...")) {
            sql.append(" AND p.texto LIKE ?");
            params.add("%" + texto + "%");
        }

        if (dificuldade != null && !dificuldade.equals("Todas") && !dificuldade.equals("Dificuldade")) {
            sql.append(" AND nv.nome = ?");
            params.add(dificuldade);
        }

        sql.append(" ORDER BY p.id_pergunta DESC");

        try (java.sql.Connection conn = br.com.pi_2026_1_etec.config.ConexaoBD.obterConexao();
             java.sql.PreparedStatement ps = conn.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            try (java.sql.ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    model.addRow(new Object[]{
                            rs.getString("enunciado"),
                            rs.getString("dificuldade"),
                            rs.getString("criado_em"),
                            rs.getInt("id_pergunta")
                    });
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Erro ao filtrar: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initComponents() {
        jSpinner1 = new JSpinner();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Gerenciamento de Perguntas");
        setMinimumSize(new Dimension(1000, 600));

        jPanel2 = new JPanel(new BorderLayout(20, 20));
        jPanel2.setBackground(Color.WHITE);
        jPanel2.setBorder(new EmptyBorder(20, 25, 25, 25));

        JPanel painelTopo = criarPainelTopo();
        JPanel painelTabela = criarPainelTabela();
        JPanel painelRodape = criarPainelRodape();

        jPanel2.add(painelTopo, BorderLayout.NORTH);
        jPanel2.add(painelTabela, BorderLayout.CENTER);
        jPanel2.add(painelRodape, BorderLayout.SOUTH);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(jPanel2, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }

    private JPanel criarPainelTopo() {
        JPanel painelTopo = new JPanel(new BorderLayout(20, 10));
        painelTopo.setOpaque(false);

        jButtonVoltar = new JButton("Voltar");
        jButtonVoltar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jButtonVoltar.addActionListener(this::jButtonVoltarActionPerformed);

        JPanel painelVoltar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        painelVoltar.setOpaque(false);
        painelVoltar.add(jButtonVoltar);

        jPanelPesquisar = criarPainelPesquisar();

        jButtonAdicionarPergunta = new JButton("Adicionar nova pergunta");
        jButtonAdicionarPergunta.setBackground(corPrincipal);
        jButtonAdicionarPergunta.setForeground(Color.WHITE);
        jButtonAdicionarPergunta.setFont(new Font("Segoe UI", Font.BOLD, 12));
        jButtonAdicionarPergunta.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jButtonAdicionarPergunta.setPreferredSize(new Dimension(190, 34));
        jButtonAdicionarPergunta.addActionListener(this::jButtonAdicionarPerguntaActionPerformed);

        JPanel painelAdicionar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        painelAdicionar.setOpaque(false);
        painelAdicionar.add(jButtonAdicionarPergunta);

        JPanel painelLinhaAcoes = new JPanel(new BorderLayout());
        painelLinhaAcoes.setOpaque(false);
        painelLinhaAcoes.add(jPanelPesquisar, BorderLayout.WEST);
        painelLinhaAcoes.add(painelAdicionar, BorderLayout.EAST);

        painelTopo.add(painelVoltar, BorderLayout.NORTH);
        painelTopo.add(painelLinhaAcoes, BorderLayout.CENTER);

        return painelTopo;
    }

    private JPanel criarPainelPesquisar() {
        JPanel painel = new JPanel(new BorderLayout(10, 8));
        painel.setBackground(corPrincipal);
        painel.setBorder(new EmptyBorder(10, 10, 10, 10));
        painel.setPreferredSize(new Dimension(480, 95));

        jTextFieldPesquisarPergunta = new JTextField("Pesquisar pergunta...");
        jTextFieldPesquisarPergunta.setPreferredSize(new Dimension(430, 32));
        jTextFieldPesquisarPergunta.addActionListener(this::jTextFieldPesquisarPerguntaActionPerformed);

        jComboBoxTema = new JComboBox<>();
        jComboBoxTema.addActionListener(this::jComboBoxTemaActionPerformed);

        jComboBoxDificuldade = new JComboBox<>();
        jComboBoxDificuldade.addActionListener(this::jComboBoxDificuldadeActionPerformed);

        jButtonFiltrar = new JButton("Filtrar");
        jButtonFiltrar.setBackground(corPrincipal);
        jButtonFiltrar.setForeground(Color.WHITE);
        jButtonFiltrar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        jButtonFiltrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jButtonFiltrar.addActionListener(this::jButtonFiltrarActionPerformed);

        JPanel painelFiltros = new JPanel(new GridLayout(1, 3, 8, 0));
        painelFiltros.setOpaque(false);
        painelFiltros.add(jComboBoxTema);
        painelFiltros.add(jComboBoxDificuldade);
        painelFiltros.add(jButtonFiltrar);

        painel.add(jTextFieldPesquisarPergunta, BorderLayout.NORTH);
        painel.add(painelFiltros, BorderLayout.CENTER);

        return painel;
    }

    private JPanel criarPainelTabela() {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setOpaque(false);

        jTablePerguntas = new JTable();
        jTablePerguntas.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{
                        "Pergunta", "Dificuldade", "Criado em", "ID"
                }
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });

        jTablePerguntas.setRowHeight(28);
        jTablePerguntas.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        jTablePerguntas.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        jTablePerguntas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jScrollPane2 = new JScrollPane(jTablePerguntas);
        jScrollPane2.setBorder(BorderFactory.createLineBorder(new Color(204, 204, 204)));

        painel.add(jScrollPane2, BorderLayout.CENTER);

        return painel;
    }

    private JPanel criarPainelRodape() {
        JPanel painelRodape = new JPanel(new BorderLayout());
        painelRodape.setOpaque(false);

        jButtonEditar = new JButton("Editar");
        jButtonEditar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jButtonEditar.setPreferredSize(new Dimension(90, 30));
        jButtonEditar.addActionListener(this::jButtonEditarActionPerformed);

        JPanel painelBotaoEditar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        painelBotaoEditar.setOpaque(false);
        painelBotaoEditar.add(jButtonEditar);

        painelRodape.add(painelBotaoEditar, BorderLayout.EAST);

        return painelRodape;
    }

    private void ocultarColunaId() {
        if (jTablePerguntas.getColumnModel().getColumnCount() > 3) {
            jTablePerguntas.getColumnModel().getColumn(3).setMinWidth(0);
            jTablePerguntas.getColumnModel().getColumn(3).setMaxWidth(0);
            jTablePerguntas.getColumnModel().getColumn(3).setPreferredWidth(0);
        }
    }

    private void jTextFieldPesquisarPerguntaActionPerformed(java.awt.event.ActionEvent evt) {
        // Mantido para preservar função existente.
    }

    private void jButtonFiltrarActionPerformed(java.awt.event.ActionEvent evt) {
        String texto = jTextFieldPesquisarPergunta.getText();
        String tema = jComboBoxTema.getSelectedItem().toString();
        String dificuldade = jComboBoxDificuldade.getSelectedItem().toString();
        aplicarFiltro(texto, tema, dificuldade);
    }

    private void jButtonVoltarActionPerformed(java.awt.event.ActionEvent evt) {
        new TelaMenuProfessores().setVisible(true);
        this.dispose();
    }

    private void jButtonAdicionarPerguntaActionPerformed(java.awt.event.ActionEvent evt) {
        new TelaAlterarPergunta(0).setVisible(true);
        this.dispose();
    }

    private void jComboBoxTemaActionPerformed(java.awt.event.ActionEvent evt) {
        // Mantido para preservar função existente.
    }

    private void jComboBoxDificuldadeActionPerformed(java.awt.event.ActionEvent evt) {
        // Mantido para preservar função existente.
    }

    private void jButtonEditarActionPerformed(java.awt.event.ActionEvent evt) {
        int linha = jTablePerguntas.getSelectedRow();

        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma pergunta.");
            return;
        }

        int idPergunta = (int) jTablePerguntas.getValueAt(linha, 3);

        TelaAlterarPergunta tela = new TelaAlterarPergunta(idPergunta);
        tela.setVisible(true);
        this.dispose();
    }

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new TelaGerenciamentoDePergunta().setVisible(true));
    }
}