/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MenuBlog.java
 *
 * Created on 28/11/2010, 14:55:21
 */

package guiDesktop;

import br.edu.ufcg.dsc.si.blog.webservice.BlogWSImpl;
import classes.Blog;
import classes.Post;
import facades.FacadeBlog;
import facades.FacadePost;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

/**
 *
 * @author Tiago
 */
public class MenuBlog extends javax.swing.JFrame implements KeyListener{
    private String idBlog, idSessao;
    private BlogWSImpl fachada = new BlogWSImpl();


    @SuppressWarnings("deprecation")
    @Override
    public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    //botaoCadastrarActionPerformed(null);

            } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                   // botaoCancelarActionPerformed(null);
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

    /** Creates new form FormularioCliente */
    @SuppressWarnings({"deprecation", "LeakingThisInConstructor"})

    public MenuBlog(String IdBlog, String idSessao) {
        this.idBlog = IdBlog;
        this.idSessao = idSessao;

        String blogTitulo = "";
        String blogDescricao = "";
        String postTitulo = "";
        String postCorpo = "";


        try {
            fachada.loadData();
            String blogID = idBlog;//FIXME TEM QUE VER ESSE INDEX
            blogTitulo = fachada.getBlogInformation(blogID, "titulo");
            blogDescricao = fachada.getBlogInformation(blogID, "descricao");
            //TODO FAZER A PARTE DO POST
            /*Integer postID = fachada.getPost(String.valueOf(blogID), 0);
            postTitulo = fachada.getPostInformation(String.valueOf(postID), "titulo");
            postCorpo = fachada.getPostInformation(String.valueOf(postID), "corpo");*/

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),
                    "Problemas ao criar um perfil",
                    JOptionPane.ERROR_MESSAGE);
        }
        initComponents();

        jLabelTitulo.setText(blogTitulo);
        jLabelDescricao.setText(blogDescricao);


        this.addKeyListener(this);
        this.show();




        setVisible(true);
        setExtendedState(MAXIMIZED_BOTH);

        reiniciaCampos();

        try {
           buscaPosts();
           buscaSubBlogs();
        } catch (Exception ex) {
           JOptionPane.showMessageDialog(null, ex.getMessage(),
                "Busca Sem Sucesso",
                JOptionPane.INFORMATION_MESSAGE);
        }

    }

    private void buscaSubBlogs() throws Exception {
        final FacadeBlog fachada = FacadeBlog.getInstance();
    	if(idSessao != null) {
            jListSubBlogs.setModel(new javax.swing.AbstractListModel() {
            List<Blog> clientes = fachada.getListOfSubBlogs(idBlog);
            public int getSize() {return clientes.size();}
            public String getElementAt(int i){return clientes.get(i).getId();}
            });
            jScrollPane2.setViewportView(jListSubBlogs);
        } else
            throw new Exception("ID da sessão inválida");
    }

    private void buscaPosts() throws Exception {
        final FacadePost fachada = FacadePost.getInstance();
        final FacadeBlog fachadaBlog = FacadeBlog.getInstance();
    	if(idSessao != null) {
            final Blog blog = fachadaBlog.getBlogByIdBlog(idBlog);
            jListPosts.setModel(new javax.swing.AbstractListModel() {
            List<Post> clientes = fachada.getListaPostsPorBlog(blog);
            public int getSize() {return clientes.size();}
            public String getElementAt(int i){return clientes.get(i).getId();}
            });
            jScrollPane1.setViewportView(jListPosts);
        } else
            throw new Exception("ID da sessão inválida");
    }

    private void reiniciaCampos() {
         jListPosts.setModel(new javax.swing.AbstractListModel() {
            Post[] posts = {};
            public int getSize() {return posts.length;}
            public Post getElementAt(int i){return posts[i];}
        });

         jListSubBlogs.setModel(new javax.swing.AbstractListModel() {
            Blog[] subBlogs = {};
            public int getSize() {return subBlogs.length;}
            public Blog getElementAt(int i){return subBlogs[i];}
        });
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelTitulo = new javax.swing.JLabel();
        jLabelDescricao = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListSubBlogs = new javax.swing.JList();
        botaoCriarSubBlog = new javax.swing.JButton();
        botaoCancelar = new javax.swing.JButton();
        jLabelPost = new javax.swing.JLabel();
        jLabelTituloPost1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListPosts = new javax.swing.JList();
        botaoCriarPost = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabelTitulo.setFont(new java.awt.Font("Tahoma", 0, 36));
        jLabelTitulo.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        jLabelDescricao.setFont(new java.awt.Font("Tahoma", 0, 14));

        jLabel1.setText("Posts");

        jLabel3.setText("SubBlogs");

        jScrollPane1.setViewportView(jListSubBlogs);

        botaoCriarSubBlog.setText("Criar SubBlog");
        botaoCriarSubBlog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCriarSubBlogActionPerformed(evt);
            }
        });

        botaoCancelar.setText("Voltar");
        botaoCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCancelarActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(jListPosts);

        botaoCriarPost.setText("Criar Post");
        botaoCriarPost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCriarPostActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelDescricao, javax.swing.GroupLayout.DEFAULT_SIZE, 847, Short.MAX_VALUE)
                        .addGap(10, 10, 10))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 847, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelTituloPost1, javax.swing.GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE)
                            .addComponent(jLabelPost, javax.swing.GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(botaoCancelar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botaoCriarSubBlog)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botaoCriarPost)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap())
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel3)
                                        .addGap(88, 88, 88)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel1)
                                    .addGap(96, 96, 96)))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jLabelDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelTituloPost1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabelPost, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoCancelar)
                    .addComponent(botaoCriarSubBlog)
                    .addComponent(botaoCriarPost))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoCriarSubBlogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCriarSubBlogActionPerformed
        this.dispose();
        try {
            fachada.saveData();
        } catch (Exception ex) {
            Logger.getLogger(MenuBlog.class.getName()).log(Level.SEVERE, null, ex);
        }
        new CriaSubBlog(idSessao, idBlog);
        
}//GEN-LAST:event_botaoCriarSubBlogActionPerformed

    private void botaoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelarActionPerformed
        this.dispose();
        new FramePrincipal(idSessao);
}//GEN-LAST:event_botaoCancelarActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try {
            fachada.logoff(idSessao);
            fachada.saveData();
        } catch (Exception ex) {
           JOptionPane.showMessageDialog(null, "Logoff sem sucesso!",
                "Logoff sem sucesso!",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_formWindowClosing

    private void botaoCriarPostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCriarPostActionPerformed
        this.dispose();
        new CriarPost(idSessao, idBlog);
    }//GEN-LAST:event_botaoCriarPostActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoCancelar;
    private javax.swing.JButton botaoCriarPost;
    private javax.swing.JButton botaoCriarSubBlog;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelDescricao;
    private javax.swing.JLabel jLabelPost;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JLabel jLabelTituloPost1;
    private javax.swing.JList jListPosts;
    private javax.swing.JList jListSubBlogs;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables

}