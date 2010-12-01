/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * EditarPerfil.java
 *
 * Created on 30/11/2010, 19:43:09
 */

package guiDesktop;

import br.edu.ufcg.dsc.si.blog.webservice.BlogWSImpl;
import enuns.Sexo;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

/**
 *
 * @author usuário
 */
public class EditarPerfil extends javax.swing.JFrame {
    private String sessao;

    /** Creates new form EditarPerfil */
    public EditarPerfil(String sessao) {
        this.sessao = sessao;
        initComponents();
        JScrollPane scroll = new JScrollPane();
        scroll.setViewportView(jPanel1);
        setContentPane(scroll);
        setVisible(true);
        jPanel1.setVisible(true);
        setExtendedState(MAXIMIZED_BOTH);
        carregaCombosEnums();
        inicializaDados();        
    }

    private void inicializaDados() {
        BlogWSImpl ws = new BlogWSImpl();
        try {
            fieldLogin.setText(ws.getProfileInformationBySessionId(sessao, "login"));
            fieldNome.setText(ws.getProfileInformationBySessionId(sessao, "nome_exibicao"));
            fieldEmail.setText(ws.getProfileInformationBySessionId(sessao, "email"));
            fieldRua.setText(ws.getProfileInformationBySessionId(sessao, "endereco"));
            fieldInteresse.setText(ws.getProfileInformationBySessionId(sessao, "interesses"));
            fieldQmSouEu.setText(ws.getProfileInformationBySessionId(sessao, "quem_sou_eu"));
            fieldFilmes.setText(ws.getProfileInformationBySessionId(sessao, "filmes"));
            fieldMusicas.setText(ws.getProfileInformationBySessionId(sessao, "musicas"));
            fieldLivros.setText(ws.getProfileInformationBySessionId(sessao, "livros"));
            String ano = ws.getProfileInformationBySessionId(sessao, "dataNasc").split("/")[0];
            String dia = ws.getProfileInformationBySessionId(sessao, "dataNasc").split("/")[1];
            String mes = ws.getProfileInformationBySessionId(sessao, "dataNasc").split("/")[2];
            if(ano != null)
                comboAno.setSelectedItem(ano);
            else
                comboAno.setSelectedIndex(-1);

            if(dia != null)
                comboDia.setSelectedItem(dia);
            else
                comboDia.setSelectedIndex(-1);

            if(mes != null)
                comboMes.setSelectedItem(mes);
            else
                comboMes.setSelectedIndex(-1);

            String sexo = ws.getProfileInformationBySessionId(sessao, "sexo");
            if(sexo != null)
                comboSexo.setSelectedItem(sexo);
            else
                comboSexo.setSelectedIndex(-1);

        }  catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Problemas ao editar um perfil",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void carregaCombosEnums() {
        comboSexo.setModel(new javax.swing.
                DefaultComboBoxModel(Sexo.values()));
         comboAno.setModel(new javax.swing.
                DefaultComboBoxModel(arrayAnos()));
    }
    
    private String[] arrayAnos() {
        String[] retorno = new String[111];
        for(int i = 1910; i - 1910 < retorno.length; i++)
            retorno[i - 1910] = String.valueOf(i);
        return retorno;
    }

    private void reiniciaCampos() {
        fieldCidade.setText(null);
        fieldEmail.setText(null);
        fieldNome.setText(null);
        fieldRua.setText(null);
        fieldFilmes.setText(null);
        fieldInteresse.setText(null);
        fieldLivros.setText(null);
        fieldMusicas.setText(null);
        fieldQmSouEu.setText(null);
        fieldLogin.setText(null);
        fieldSenha.setText(null);
        fieldConfirmaSenha.setText(null);
        comboAno.setSelectedIndex(-1);
        comboDia.setSelectedIndex(-1);
        comboMes.setSelectedIndex(-1);
        comboSexo.setSelectedIndex(-1);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        comboDia = new javax.swing.JComboBox();
        comboMes = new javax.swing.JComboBox();
        comboAno = new javax.swing.JComboBox();
        fieldEmail = new javax.swing.JTextField();
        fieldRua = new javax.swing.JTextField();
        fieldCidade = new javax.swing.JTextField();
        comboSexo = new javax.swing.JComboBox();
        botaoCadastrar = new javax.swing.JButton();
        botaoCancelar = new javax.swing.JButton();
        fieldNome = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        fieldLogin = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        fieldConfirmaSenha = new javax.swing.JPasswordField();
        fieldSenha = new javax.swing.JPasswordField();
        jScrollPane1 = new javax.swing.JScrollPane();
        fieldInteresse = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        fieldQmSouEu = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        fieldFilmes = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        fieldMusicas = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        fieldLivros = new javax.swing.JTextArea();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setPreferredSize(new java.awt.Dimension(800, 507));

        jLabel1.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel1.setText("Cadastro do Perfil");

        jLabel2.setText("Nome");

        jLabel5.setText("Data de Nascimento");

        jLabel8.setText("E-mail");

        jLabel10.setText("Endereco");

        jLabel14.setText("Cidade");

        jLabel17.setText("Sexo");

        comboDia.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        comboMes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        botaoCadastrar.setText("Atualizar");
        botaoCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCadastrarActionPerformed(evt);
            }
        });

        botaoCancelar.setText("Fechar");
        botaoCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCancelarActionPerformed(evt);
            }
        });

        jLabel18.setText("Interesses");

        jLabel19.setText("Quem sou eu");

        jLabel20.setText("Filmes");

        jLabel21.setText("Livros");

        jLabel22.setText("Músicas");

        jLabel3.setText("Login");

        jLabel9.setText("Confirmação da Senha");

        jLabel15.setText("Senha");

        fieldInteresse.setColumns(20);
        fieldInteresse.setRows(5);
        jScrollPane1.setViewportView(fieldInteresse);

        fieldQmSouEu.setColumns(20);
        fieldQmSouEu.setRows(5);
        jScrollPane2.setViewportView(fieldQmSouEu);

        fieldFilmes.setColumns(20);
        fieldFilmes.setRows(5);
        jScrollPane3.setViewportView(fieldFilmes);

        fieldMusicas.setColumns(20);
        fieldMusicas.setRows(5);
        jScrollPane4.setViewportView(fieldMusicas);

        fieldLivros.setColumns(20);
        fieldLivros.setRows(5);
        jScrollPane5.setViewportView(fieldLivros);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel15)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(fieldSenha, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                                .addComponent(fieldConfirmaSenha, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE))
                            .addComponent(fieldLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(805, 805, 805))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel17)
                            .addComponent(jLabel5)
                            .addComponent(jLabel2)
                            .addComponent(jLabel18)
                            .addComponent(jLabel14)
                            .addComponent(jLabel8)
                            .addComponent(jLabel19)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addGap(8, 8, 8))
                            .addComponent(jLabel21)
                            .addComponent(jLabel22))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(fieldNome)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(comboDia, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboMes, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboAno, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(fieldCidade)
                            .addComponent(fieldEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                            .addComponent(jScrollPane4)
                            .addComponent(jScrollPane3)
                            .addComponent(jScrollPane2)
                            .addComponent(jScrollPane1)
                            .addComponent(fieldRua)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(comboSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(146, 146, 146)
                                .addComponent(botaoCancelar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botaoCadastrar))
                            .addComponent(jScrollPane5))
                        .addContainerGap(120, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel1)
                .addContainerGap(531, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(fieldLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(fieldSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldConfirmaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(fieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(comboAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(fieldRua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(fieldCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(fieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(botaoCadastrar)
                    .addComponent(botaoCancelar))
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel19))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel22))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel21))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 657, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCadastrarActionPerformed
        try {
            //TODO RELACIONAMETO COM ESSE USUARIO u.
            //BlogWS fachada = HelperClient.getInstance("8080"); //FIXME não sei se eh essa porta
            BlogWSImpl ws = new BlogWSImpl();
            if(fieldConfirmaSenha.getText().equals(fieldSenha.getText())){
                if(!ws.getProfileInformationBySessionId(sessao, "login").equals(fieldLogin.getText()))
                    ws.changeProfileInformation(sessao, "login", fieldLogin.getText());
                if(!fieldSenha.getText().trim().isEmpty())
                    ws.changeProfileInformation(sessao, "senha", fieldSenha.getText());
                ws.changeProfileInformation(sessao, "nome_exibicao", fieldNome.getText());
                ws.changeProfileInformation(sessao, "dataNasc", String.valueOf(comboDia.getSelectedItem())
                        + "/" + String.valueOf(comboMes.getSelectedItem()) + "/" +
                        String.valueOf(comboAno.getSelectedItem()));
                ws.changeProfileInformation(sessao, "endereco", fieldRua.getText());
                ws.changeProfileInformation(sessao, "sexo",
                        String.valueOf(comboSexo.getSelectedItem()));
                ws.changeProfileInformation(sessao, "interesses", fieldInteresse.getText());
                ws.changeProfileInformation(sessao, "quem_sou_eu", fieldQmSouEu.getText());
                ws.changeProfileInformation(sessao, "filmes", fieldFilmes.getText());
                ws.changeProfileInformation(sessao, "musicas", fieldMusicas.getText());
                ws.changeProfileInformation(sessao, "livros", fieldLivros.getText());

                ws.saveData();

                JOptionPane.showMessageDialog(null, "Perfil atualizado com sucesso!",
                        "Perfil",
                        JOptionPane.CLOSED_OPTION);
            }else{
                throw new Exception("Senha e confirmação da senha devem ser iguais!");
            }
            reiniciaCampos();
            this.dispose();
            new FramePrincipal(sessao);

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),
                    "Problemas ao criar um perfil",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),
                    "Problemas ao criar um perfil",
                    JOptionPane.ERROR_MESSAGE);
        }
}//GEN-LAST:event_botaoCadastrarActionPerformed

    private void botaoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelarActionPerformed
        reiniciaCampos();
        this.dispose();
        new FramePrincipal(sessao);
}//GEN-LAST:event_botaoCancelarActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        reiniciaCampos();
        dispose();
        new FramePrincipal(sessao);
    }//GEN-LAST:event_formWindowClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoCadastrar;
    private javax.swing.JButton botaoCancelar;
    private javax.swing.JComboBox comboAno;
    private javax.swing.JComboBox comboDia;
    private javax.swing.JComboBox comboMes;
    private javax.swing.JComboBox comboSexo;
    private javax.swing.JTextField fieldCidade;
    private javax.swing.JPasswordField fieldConfirmaSenha;
    private javax.swing.JTextField fieldEmail;
    private javax.swing.JTextArea fieldFilmes;
    private javax.swing.JTextArea fieldInteresse;
    private javax.swing.JTextArea fieldLivros;
    private javax.swing.JTextField fieldLogin;
    private javax.swing.JTextArea fieldMusicas;
    private javax.swing.JTextField fieldNome;
    private javax.swing.JTextArea fieldQmSouEu;
    private javax.swing.JTextField fieldRua;
    private javax.swing.JPasswordField fieldSenha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    // End of variables declaration//GEN-END:variables

}
