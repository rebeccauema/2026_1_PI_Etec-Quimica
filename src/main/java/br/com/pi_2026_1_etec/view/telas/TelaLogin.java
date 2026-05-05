package br.com.pi_2026_1_etec.view.telas;

public class TelaLogin extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaLogin.class.getName());

    public TelaLogin() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        jPanelFundoAzul = new javax.swing.JPanel();
        jPanelFundoVermelho = new javax.swing.JPanel();
        jPanelFundoLogin = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLayeredPane1.setPreferredSize(new java.awt.Dimension(775, 460));
        jLayeredPane1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelFundoAzul.setBackground(new java.awt.Color(7, 92, 110));
        jPanelFundoAzul.setPreferredSize(new java.awt.Dimension(387, 335));

        javax.swing.GroupLayout jPanelFundoAzulLayout = new javax.swing.GroupLayout(jPanelFundoAzul);
        jPanelFundoAzul.setLayout(jPanelFundoAzulLayout);
        jPanelFundoAzulLayout.setHorizontalGroup(
            jPanelFundoAzulLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 390, Short.MAX_VALUE)
        );
        jPanelFundoAzulLayout.setVerticalGroup(
            jPanelFundoAzulLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
        );

        jLayeredPane1.add(jPanelFundoAzul, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 390, 350));

        jPanelFundoVermelho.setBackground(new java.awt.Color(146, 25, 19));
        jPanelFundoVermelho.setPreferredSize(new java.awt.Dimension(775, 460));

        javax.swing.GroupLayout jPanelFundoVermelhoLayout = new javax.swing.GroupLayout(jPanelFundoVermelho);
        jPanelFundoVermelho.setLayout(jPanelFundoVermelhoLayout);
        jPanelFundoVermelhoLayout.setHorizontalGroup(
            jPanelFundoVermelhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 775, Short.MAX_VALUE)
        );
        jPanelFundoVermelhoLayout.setVerticalGroup(
            jPanelFundoVermelhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 460, Short.MAX_VALUE)
        );

        jLayeredPane1.add(jPanelFundoVermelho, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 460));

        jPanelFundoLogin.setBackground(new java.awt.Color(255, 255, 255));
        jPanelFundoLogin.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanelFundoLoginLayout = new javax.swing.GroupLayout(jPanelFundoLogin);
        jPanelFundoLogin.setLayout(jPanelFundoLoginLayout);
        jPanelFundoLoginLayout.setHorizontalGroup(
            jPanelFundoLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 340, Short.MAX_VALUE)
        );
        jPanelFundoLoginLayout.setVerticalGroup(
            jPanelFundoLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 320, Short.MAX_VALUE)
        );

        jLayeredPane1.setLayer(jPanelFundoLogin, javax.swing.JLayeredPane.MODAL_LAYER);
        jLayeredPane1.add(jPanelFundoLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(217, 70, 340, 320));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new TelaLogin().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanelFundoAzul;
    private javax.swing.JPanel jPanelFundoLogin;
    private javax.swing.JPanel jPanelFundoVermelho;
    // End of variables declaration//GEN-END:variables
}
