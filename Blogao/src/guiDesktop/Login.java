package guiDesktop;

import br.edu.ufcg.dsc.si.blog.webservice.BlogWS;
import br.edu.ufcg.dsc.si.blog.webservice.BlogWSImpl;
import br.edu.ufcg.dsc.si.blog.webservice.HelperClient;
import classes.func.usuario.Usuario;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
public class Login extends JFrame implements KeyListener, ActionListener {
	/**
         * 
         */
	private static final long serialVersionUID = 1L;
	private static JLabel login, senha, cadastro, imagem1;
	private static JFrame frame;
	private static Box painel0, painel1, painel2, painel3, painel4, painel;
	private static JTextField campoLogin;
	private static JPasswordField campoSenha;
	private static JButton jBEntrar, jBCadastrar, jBSair;
	private static Font fonte1;
	@SuppressWarnings("unused")
	private static Login tela;
	private static Color cor1, cor2, cor5;
	private static Usuario usuario;
	private BlogWSImpl fachada = new BlogWSImpl();

	public static Usuario getUsuario() {
		return usuario;
	}

	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e) {
		//BlogWS fachada = HelperClient.getInstance("8080"); // FIXME
		if ("entrar".equals(e.getActionCommand())) {
			String loginUser = campoLogin.getText();
			String senhaUser = campoSenha.getText();

			try {
				String idSessao = fachada.logon(loginUser, senhaUser);
				fachada.saveData();
				frame.dispose();
				new FramePrincipal(idSessao);

			} catch (HeadlessException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "ERRO",
						JOptionPane.ERROR_MESSAGE);
			} catch (java.lang.Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "ERRO",
						JOptionPane.ERROR_MESSAGE);
			}

		} else if ("sair".equals(e.getActionCommand())) {
			frame.dispose();
		} else if ("cadastrar".equals(e.getActionCommand())) {
			new CriarPerfil();
			frame.dispose();
		}
	}

	@SuppressWarnings("deprecation")
	public Login() {
		try {
			fachada.loadData();
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "ERRO",
					JOptionPane.ERROR_MESSAGE);
		}
		
		frame = new JFrame("Blogão SI");

		frame.addKeyListener(this);

		frame.show();

		frame.setIconImage(frame.getToolkit()
				.getImage(("imagens\\estrela.png")));

		// CORES
		// branco
		cor1 = Color.BLACK;
		// preto do fundo
		cor2 = new Color(0, 0, 0);
		// verde da letra
		cor5 = new Color(0, 164, 164);

		frame.getContentPane().setBackground(cor2);

		// Adicionando o tema Nimbus, pra ficar mais bonitinho ;)

		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SwingUtilities.updateComponentTreeUI(frame);

		fonte1 = new Font("Calibri", 1, 16);

		login = new JLabel("Login: ");
		login.setFont(fonte1);
		login.setForeground(cor5);

		senha = new JLabel("Senha: ");
		senha.setFont(fonte1);
		senha.setForeground(cor5);

		cadastro = new JLabel("Cadastro: ");
		cadastro.setFont(fonte1);
		cadastro.setForeground(cor5);
		imagem1 = new JLabel(new Imagem("/imagens/blogão.png").carregaImagem());

		campoLogin = new JTextField(20);
		campoLogin.addKeyListener(this);
		campoLogin.show();
		campoSenha = new JPasswordField(20);
		campoSenha.addKeyListener(this);
		campoSenha.show();

		jBEntrar = new JButton("Entrar");
		jBEntrar.setFont(fonte1);
		jBEntrar.setBackground(cor5);
		jBEntrar.setForeground(cor1);
		jBEntrar.addActionListener(this);
		jBEntrar.setActionCommand("entrar");
		jBEntrar.setToolTipText("Acessar o sistema Blogão");

		jBCadastrar = new JButton("Cadastrar");
		jBCadastrar.setFont(fonte1);
		jBCadastrar.setBackground(cor5);
		jBCadastrar.setForeground(cor1);
		jBCadastrar.addActionListener(this);
		jBCadastrar.setActionCommand("cadastrar");
		jBCadastrar.setToolTipText("Cadastrar-se no sistema Blogão");

		jBSair = new JButton("Sair");
		jBSair.setFont(fonte1);
		jBSair.setBackground(cor5);
		jBSair.setForeground(cor1);
		jBSair.addActionListener(this);
		jBSair.setActionCommand("sair");
		jBSair.setToolTipText("Sair do sistema Ortodontia e Est�tica");

		painel0 = Box.createHorizontalBox();
		painel1 = Box.createHorizontalBox();
		painel2 = Box.createHorizontalBox();
		painel3 = Box.createHorizontalBox();
		painel4 = Box.createHorizontalBox();
		painel = Box.createVerticalBox();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		painel0.add(imagem1);

		painel1.add(Box.createHorizontalStrut(4));
		painel1.add(login);
		painel1.add(Box.createHorizontalStrut(27));
		painel1.add(campoLogin);

		painel2.add(Box.createHorizontalStrut(4));
		painel2.add(senha);
		painel2.add(Box.createHorizontalStrut(22));
		painel2.add(campoSenha);

		painel4.add(jBEntrar);
		painel4.add(Box.createHorizontalStrut(10));
		painel4.add(jBSair);
		painel4.add(Box.createHorizontalStrut(10));
		painel4.add(jBCadastrar);

		painel.add(painel0);
		painel.add(Box.createVerticalStrut(50));
		painel.add(painel1);
		painel.add(Box.createVerticalStrut(15));
		painel.add(painel2);
		painel.add(Box.createVerticalStrut(15));
		painel.add(painel3);
		painel.add(Box.createVerticalStrut(15));
		painel.add(painel4);

		frame.setLayout(new GridBagLayout());
		frame.add(painel);

		frame.setVisible(true);
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);

	}

	@SuppressWarnings("deprecation")
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			String loginUser = campoLogin.getText();
			String senhaUser = campoSenha.getText();
			//BlogWS fachada = HelperClient.getInstance("8080"); //FIXME
			try {

				String idSessao = fachada.logon(loginUser, senhaUser);
				fachada.saveData();
				frame.dispose();
				new FramePrincipal(idSessao);


			} catch (HeadlessException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "ERRO",
						JOptionPane.ERROR_MESSAGE);
			} catch (java.lang.Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "ERRO",
						JOptionPane.ERROR_MESSAGE);
			}

		} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			frame.dispose();
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
}