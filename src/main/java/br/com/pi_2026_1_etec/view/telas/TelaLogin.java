package br.com.pi_2026_1_etec.view.telas;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TelaLogin extends JFrame {

    private JTextField campEmail;
    private JPasswordField campSenha;
    private JCheckBox checkSenha;
    private JButton btnEntrar;
    private JLabel lblErro;

    public TelaLogin() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1024, 768);
        setLocationRelativeTo(null);
        setTitle("The Amazing Chemistry Game - Login");

        JPanel painel = new JPanel(null) {
            private final Image bg = new ImageIcon(
                    getClass().getResource("/Imagens/imagemTelaLogin.png")
            ).getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
            }
        };
        setContentPane(painel);

        int cardX = 330, cardY = 130;

        campEmail = new JTextField();
        campEmail.setOpaque(false);
        campEmail.setBackground(new Color(0, 0, 0, 0)); // transparente
        campEmail.setBorder(null);
        campEmail.setFont(new Font("SansSerif", Font.PLAIN, 15));
        campEmail.setBounds(cardX - 40, cardY + 80, 400, 45);
        painel.add(campEmail);

        campSenha = new JPasswordField();
        campSenha.setFont(new Font("SansSerif", Font.PLAIN, 15));
        campSenha.setOpaque(false);
        campSenha.setBackground(new Color(0, 0, 0, 0));
        campSenha.setBorder(null);
        campSenha.setBounds(cardX - 40, cardY + 180, 400, 45);
        painel.add(campSenha);

        checkSenha = new JCheckBox("");
        checkSenha.setFont(new Font("SansSerif", Font.PLAIN, 13));
        checkSenha.setPreferredSize(new Dimension(60, 60));
        checkSenha.setOpaque(false);
        checkSenha.setBorder(null);
        checkSenha.setForeground(new Color(0, 0, 0));
        checkSenha.setBounds(cardX - 40, cardY + 230, 200, 40);
        checkSenha.setIcon(null);
        checkSenha.setSelectedIcon(null);
        checkSenha.addActionListener(e
                -> campSenha.setEchoChar(checkSenha.isSelected() ? (char) 0 : '\u2022')
        );
        painel.add(checkSenha);

        lblErro = new JLabel("", SwingConstants.CENTER);
        lblErro.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblErro.setForeground(new Color(200, 30, 28));
        lblErro.setBounds(cardX + 30, cardY + 258, 320, 20);
        painel.add(lblErro);

        btnEntrar = new JButton("");
        btnEntrar.setFont(new Font("SansSerif", Font.BOLD, 18));
        btnEntrar.setOpaque(false);

        btnEntrar.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 0));
        btnEntrar.setFocusPainted(false);
        btnEntrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnEntrar.setBounds(cardX - 40, cardY + 292, 430, 50);
        btnEntrar.addActionListener(e -> fazerLogin());
        btnEntrar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnEntrar.setContentAreaFilled(false);
                btnEntrar.setBorderPainted(false);
                btnEntrar.setOpaque(false);
                btnEntrar.setBackground(new Color(0, 0, 0));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnEntrar.setBackground(new Color(165, 25, 22));
            }
        });
        painel.add(btnEntrar);

        JLabel lblCadastro = new JLabel(
                "<html><center>Ainda n\u00e3o possui login? <u>Fa\u00e7a o cadastro aqui.</u></center></html>",
                SwingConstants.CENTER
        );
        lblCadastro.setFont(new Font("SansSerif", Font.PLAIN, 13));
        lblCadastro.setForeground(new Color(50, 55, 65));
        lblCadastro.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblCadastro.setBounds(cardX + 30, cardY + 342, 320, 28);
        lblCadastro.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "Tela de cadastro em breve!");
            }
        });
        painel.add(lblCadastro);
    }

    private void fazerLogin() {
        String email = campEmail.getText().trim();
        String senha = new String(campSenha.getPassword()).trim();

        if (email.isEmpty() || senha.isEmpty()) {
            lblErro.setText("Preencha email e senha.");
            return;
        }

        lblErro.setText("");
        JOptionPane.showMessageDialog(this, "Login OK!\nEmail: " + email);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaLogin().setVisible(true));
    }
}
