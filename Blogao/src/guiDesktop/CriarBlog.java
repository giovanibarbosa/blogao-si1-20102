/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CriarBlog.java
 *
 * Created on 27/11/2010, 20:30:24
 */

package guiDesktop;

import br.edu.ufcg.dsc.si.blog.webservice.BlogWS;
import br.edu.ufcg.dsc.si.blog.webservice.BlogWSImpl;
import br.edu.ufcg.dsc.si.blog.webservice.HelperClient;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Tiago
 */
public class CriarBlog extends javax.swing.JFrame implements KeyListener {
    private String idSessao;

    @SuppressWarnings("deprecation")
    @Override
    public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    botaoCadastrarActionPerformed(null);

            } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    botaoCancelarActionPerformed(null);
            }
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
            // TODO Auto-generated method stub

    }

    @Override
    public void keyTyped(KeyEvent arg0) {
            // TODO Auto-generated method stub

    }
    public CriarBlog(String idSessao) {

        initComponents();

        this.idSessao = idSessao;
        this.addKeyListener(this);
        this.show();


        fieldTitulo.addKeyListener(this);
        fieldTitulo.show();

        fieldDescricao.addKeyListener(this);
        fieldDescricao.show();

        setVisible(true);
        setExtendedState(MAXIMIZED_BOTH);

        reiniciaCampos();


    }

    private void reiniciaCampos() {
        fieldDescricao.setText(null);
        fieldTitulo.setText(null);

        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        fieldTitulo = new javax.swing.JTextField();
        botaoCadastrar = new javax.swing.JButton();
        botaoLimpar = new javax.swing.JButton();
        botaoCancelar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        fieldDescricao = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Título do Blog");

        jLabel3.setText("Descrição");

        jLabel2.setFont(new java.awt.Font("Calibri", 0, 18));
        jLabel2.setText("Criar Blog");

        fieldTitulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldTituloActionPerformed(evt);
            }
        });

        botaoCadastrar.setText("Criar");
        botaoCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCadastrarActionPerformed(evt);
            }
        });

        botaoLimpar.setText("Limpar");
        botaoLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoLimparActionPerformed(evt);
            }
        });

        botaoCancelar.setText("Voltar");
        botaoCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCancelarActionPerformed(evt);
            }
        });

        fieldDescricao.setColumns(20);
        fieldDescricao.setRows(5);
        jScrollPane1.setViewportView(fieldDescricao);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(27, 27, 27))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(fieldTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(228, 228, 228))
                            .addComponent(jScrollPane1)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(423, Short.MAX_VALUE)
                        .addComponent(botaoCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoLimpar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoCadastrar)))
                .addGap(130, 130, 130))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(73, 73, 73)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(fieldTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(botaoLimpar)
                            .addComponent(botaoCancelar)
                            .addComponent(botaoCadastrar)))
                    .addComponent(jLabel3))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fieldTituloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldTituloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldTituloActionPerformed

    private void botaoCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCadastrarActionPerformed
        //BlogWS fachada = HelperClient.getInstance("8080");
        BlogWSImpl fachada = new BlogWSImpl();
        try {
            fachada.createBlog(idSessao, fieldTitulo.getText(), fieldDescricao.getText());
            fachada.saveData();
            JOptionPane.showMessageDialog(null, "Blog criado com sucesso!",
            "Blog",
            JOptionPane.CLOSED_OPTION);

            reiniciaCampos();
            this.dispose();
            new FramePrincipal(idSessao);
            
        } catch (Exception ex) {
           JOptionPane.showMessageDialog(null, ex.getMessage(),
            "Problemas ao criar um perfil",
            JOptionPane.ERROR_MESSAGE);
        }

}//GEN-LAST:event_botaoCadastrarActionPerformed

    private void botaoLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLimparActionPerformed
        reiniciaCampos();
}//GEN-LAST:event_botaoLimparActionPerformed

    private void botaoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelarActionPerformed
        this.dispose();
        reiniciaCampos();
        new FramePrincipal(idSessao);
}//GEN-LAST:event_botaoCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoCadastrar;
    private javax.swing.JButton botaoCancelar;
    private javax.swing.JButton botaoLimpar;
    private javax.swing.JTextArea fieldDescricao;
    private javax.swing.JTextField fieldTitulo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

}
