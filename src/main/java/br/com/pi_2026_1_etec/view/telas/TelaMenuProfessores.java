package br.com.pi_2026_1_etec.view.telas;

import br.com.pi_2026_1_etec.model.Sessao;
import br.com.pi_2026_1_etec.dao.AlunoDAO;
import br.com.pi_2026_1_etec.dao.PerguntaDAO;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class TelaMenuProfessores extends JFrame {

    private static final java.util.logging.Logger logger =
            java.util.logging.Logger.getLogger(TelaMenuProfessores.class.getName());

    private AlunoDAO alunoDAO = new AlunoDAO();
    private PerguntaDAO perguntaDAO = new PerguntaDAO();

    private final Color corAlunos = new Color(7, 92, 110);
    private final Color corPerguntas = new Color(146, 25, 19);
    private final Color corFundoAlunos = new Color(7, 92, 110, 15);
    private final Color corFundoPerguntas = new Color(146, 25, 19, 15);

    private JButton jButtonGerenciamentoDePerguntas;
    private JButton jButtonGerenciarAlunos;
    private JButton jButtonLogout;

    private JLabel jLabel1;
    private JLabel jLabel10;
    private JLabel jLabel4;
    private JLabel jLabel6;
    private JLabel jLabel8;
    private JLabel jLabelDescricao2;
    private JLabel jLabelDescricao3;
    private JLabel jLabelDescricao3Parte2;
    private JLabel jLabelSubtitulo;
    private JLabel jLabelSubtitulo2;
    private JLabel jLabelSubtitulo3;
    private JLabel jLabelTitulo;
    private JLabel jLabelTitulo2;
    private JLabel jLabelTitulo3;

    private JPanel jPanel1;
    private JPanel jPanel6;
    private JPanel jPanelTitulo2;
    private JPanel jPanelTitulo3;
    private JPanel jPanelTitulo3_2;

    public TelaMenuProfessores() {
        initComponents();

        atualizarAlunosAtivos();
        atualizarPerguntasRegistradas();

        if (!Sessao.estaLogado()) {
            JOptionPane.showMessageDialog(this, "Faça login para acessar.");
            new TelaLogin().setVisible(true);
            this.dispose();
            return;
        }

        jPanelTitulo3.setBackground(corFundoAlunos);
        jPanelTitulo3.setOpaque(true);

        jPanelTitulo3_2.setBackground(corFundoPerguntas);
        jPanelTitulo3_2.setOpaque(true);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    private void atualizarAlunosAtivos() {
        int total = alunoDAO.contarAlunosAtivos();
        jLabelSubtitulo2.setText("Alunos ativos: " + total);
    }

    private void atualizarPerguntasRegistradas() {
        int total = perguntaDAO.contarPerguntas();
        jLabel8.setText("Perguntas registradas: " + total);
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Menu Professores");
        setMinimumSize(new Dimension(1000, 600));

        jPanel1 = new JPanel(new BorderLayout(30, 25));
        jPanel1.setBackground(Color.WHITE);
        jPanel1.setBorder(new EmptyBorder(25, 35, 35, 35));

        JPanel painelTopo = criarPainelTopo();
        JPanel painelCentro = criarPainelCentro();

        jPanel1.add(painelTopo, BorderLayout.NORTH);
        jPanel1.add(painelCentro, BorderLayout.CENTER);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(jPanel1, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }

    private JPanel criarPainelTopo() {
        JPanel painelTopo = new JPanel(new BorderLayout());
        painelTopo.setOpaque(false);

        JPanel painelTextos = new JPanel();
        painelTextos.setOpaque(false);
        painelTextos.setLayout(new BoxLayout(painelTextos, BoxLayout.Y_AXIS));

        jLabelTitulo = new JLabel("Olá, Professor(a)!");
        jLabelTitulo.setFont(new Font("Segoe UI", Font.BOLD, 26));

        jLabelSubtitulo = new JLabel("Bem-vindo ao painel de O Grande Desafio da Química.");
        jLabelSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 15));

        painelTextos.add(jLabelTitulo);
        painelTextos.add(Box.createVerticalStrut(6));
        painelTextos.add(jLabelSubtitulo);

        jButtonLogout = new JButton("Logout");
        jButtonLogout.setBackground(new Color(195, 16, 12));
        jButtonLogout.setForeground(Color.WHITE);
        jButtonLogout.setFont(new Font("Segoe UI", Font.BOLD, 12));
        jButtonLogout.addActionListener(this::jButtonLogoutActionPerformed);

        JPanel painelLogout = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        painelLogout.setOpaque(false);
        painelLogout.add(jButtonLogout);

        painelTopo.add(painelTextos, BorderLayout.WEST);
        painelTopo.add(painelLogout, BorderLayout.EAST);

        return painelTopo;
    }

    private JPanel criarPainelCentro() {
        JPanel painelCentro = new JPanel();
        painelCentro.setOpaque(false);
        painelCentro.setLayout(new BoxLayout(painelCentro, BoxLayout.Y_AXIS));

        jLabelTitulo2 = new JLabel("Resumo rápido");
        jLabelTitulo2.setFont(new Font("Segoe UI", Font.BOLD, 18));
        jLabelTitulo2.setAlignmentX(LEFT_ALIGNMENT);

        JPanel painelResumo = new JPanel(new GridLayout(1, 2, 20, 0));
        painelResumo.setOpaque(false);
        painelResumo.setAlignmentX(LEFT_ALIGNMENT);
        painelResumo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 130));
        painelResumo.setPreferredSize(new Dimension(900, 130));

        jPanelTitulo2 = criarCardResumoAlunos();
        jPanel6 = criarCardResumoPerguntas();

        painelResumo.add(jPanelTitulo2);
        painelResumo.add(jPanel6);

        jLabelTitulo3 = new JLabel("O que você deseja gerenciar?");
        jLabelTitulo3.setFont(new Font("Segoe UI", Font.BOLD, 18));
        jLabelTitulo3.setAlignmentX(LEFT_ALIGNMENT);

        JPanel painelGerenciamento = new JPanel(new GridLayout(1, 2, 20, 0));
        painelGerenciamento.setOpaque(false);
        painelGerenciamento.setAlignmentX(LEFT_ALIGNMENT);
        painelGerenciamento.setMaximumSize(new Dimension(Integer.MAX_VALUE, 190));
        painelGerenciamento.setPreferredSize(new Dimension(900, 190));

        jPanelTitulo3 = criarCardGerenciarAlunos();
        jPanelTitulo3_2 = criarCardGerenciarPerguntas();

        painelGerenciamento.add(jPanelTitulo3);
        painelGerenciamento.add(jPanelTitulo3_2);

        painelCentro.add(jLabelTitulo2);
        painelCentro.add(Box.createVerticalStrut(14));
        painelCentro.add(painelResumo);
        painelCentro.add(Box.createVerticalStrut(32));
        painelCentro.add(jLabelTitulo3);
        painelCentro.add(Box.createVerticalStrut(14));
        painelCentro.add(painelGerenciamento);
        painelCentro.add(Box.createVerticalGlue());

        return painelCentro;
    }

    private JPanel criarCardResumoAlunos() {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBackground(Color.WHITE);
        painel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(204, 204, 204)),
                new EmptyBorder(18, 20, 18, 20)
        ));

        jLabelSubtitulo2 = new JLabel("Alunos ativos");
        jLabelSubtitulo2.setFont(new Font("Segoe UI", Font.BOLD, 16));

        jLabelDescricao2 = new JLabel("Alunos que jogaram nos últimos 7 dias");
        jLabelDescricao2.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        painel.add(jLabelSubtitulo2, BorderLayout.NORTH);
        painel.add(jLabelDescricao2, BorderLayout.SOUTH);

        return painel;
    }

    private JPanel criarCardResumoPerguntas() {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBackground(Color.WHITE);
        painel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(204, 204, 204)),
                new EmptyBorder(18, 20, 18, 20)
        ));

        jLabel8 = new JLabel("Perguntas ativas");
        jLabel8.setFont(new Font("Segoe UI", Font.BOLD, 16));

        jLabel10 = new JLabel("Perguntas disponíveis no sistema");
        jLabel10.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        painel.add(jLabel8, BorderLayout.NORTH);
        painel.add(jLabel10, BorderLayout.SOUTH);

        return painel;
    }

    private JPanel criarCardGerenciarAlunos() {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBackground(corFundoAlunos);
        painel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(corAlunos),
                new EmptyBorder(20, 22, 20, 22)
        ));

        JPanel textos = new JPanel();
        textos.setOpaque(false);
        textos.setLayout(new BoxLayout(textos, BoxLayout.Y_AXIS));

        jLabelSubtitulo3 = new JLabel("Alunos");
        jLabelSubtitulo3.setFont(new Font("Segoe UI", Font.BOLD, 18));
        jLabelSubtitulo3.setForeground(corAlunos);

        jLabelDescricao3 = new JLabel("Gerencie seus alunos, visualize desempenhos e acompanhe o");
        jLabelDescricao3.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        jLabelDescricao3Parte2 = new JLabel("progresso no jogo.");
        jLabelDescricao3Parte2.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        textos.add(jLabelSubtitulo3);
        textos.add(Box.createVerticalStrut(8));
        textos.add(jLabelDescricao3);
        textos.add(Box.createVerticalStrut(4));
        textos.add(jLabelDescricao3Parte2);

        jButtonGerenciarAlunos = new JButton("Gerenciar alunos");
        jButtonGerenciarAlunos.setBackground(corAlunos);
        jButtonGerenciarAlunos.setForeground(Color.WHITE);
        jButtonGerenciarAlunos.setFont(new Font("Segoe UI", Font.BOLD, 12));
        jButtonGerenciarAlunos.addActionListener(this::jButtonGerenciarAlunosActionPerformed);

        JPanel painelBotao = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        painelBotao.setOpaque(false);
        painelBotao.add(jButtonGerenciarAlunos);

        painel.add(textos, BorderLayout.CENTER);
        painel.add(painelBotao, BorderLayout.SOUTH);

        return painel;
    }

    private JPanel criarCardGerenciarPerguntas() {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBackground(corFundoPerguntas);
        painel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(corPerguntas),
                new EmptyBorder(20, 22, 20, 22)
        ));

        JPanel textos = new JPanel();
        textos.setOpaque(false);
        textos.setLayout(new BoxLayout(textos, BoxLayout.Y_AXIS));

        jLabel1 = new JLabel("Perguntas");
        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 18));
        jLabel1.setForeground(corPerguntas);

        jLabel4 = new JLabel("Crie, edite e gerencie perguntas do jogo.");
        jLabel4.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        jLabel6 = new JLabel("Organize por temas e níveis de dificuldade.");
        jLabel6.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        textos.add(jLabel1);
        textos.add(Box.createVerticalStrut(8));
        textos.add(jLabel4);
        textos.add(Box.createVerticalStrut(4));
        textos.add(jLabel6);

        jButtonGerenciamentoDePerguntas = new JButton("Gerenciar perguntas");
        jButtonGerenciamentoDePerguntas.setBackground(corPerguntas);
        jButtonGerenciamentoDePerguntas.setForeground(Color.WHITE);
        jButtonGerenciamentoDePerguntas.setFont(new Font("Segoe UI", Font.BOLD, 12));
        jButtonGerenciamentoDePerguntas.addActionListener(this::jButtonGerenciamentoDePerguntasActionPerformed);

        JPanel painelBotao = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        painelBotao.setOpaque(false);
        painelBotao.add(jButtonGerenciamentoDePerguntas);

        painel.add(textos, BorderLayout.CENTER);
        painel.add(painelBotao, BorderLayout.SOUTH);

        return painel;
    }

    private void jButtonGerenciarAlunosActionPerformed(java.awt.event.ActionEvent evt) {
        new TelaGerenciarAlunos().setVisible(true);
        this.dispose();
    }

    private void jButtonGerenciamentoDePerguntasActionPerformed(java.awt.event.ActionEvent evt) {
        new TelaGerenciamentoDePergunta().setVisible(true);
        this.dispose();
    }

    private void jButtonLogoutActionPerformed(java.awt.event.ActionEvent evt) {
        Sessao.logout();
        new TelaLogin().setVisible(true);
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

        java.awt.EventQueue.invokeLater(() -> new TelaMenuProfessores().setVisible(true));
    }
}