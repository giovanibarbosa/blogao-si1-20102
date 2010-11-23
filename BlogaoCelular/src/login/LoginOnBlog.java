package login;

import javax.microedition.midlet.MIDlet;
import javax.microedition.lcdui.*;
import menu.MenuAfterLogin;

/**
 * Classe que inicializa a tela do login
 * @author Tiago Leite
 * 
 */
public class LoginOnBlog extends MIDlet implements CommandListener {
	private Display display;
	private TextField userName;
	private TextField password;
	private Form form;
	private Command cancelar;
	private Command login;
	private static final String TRY_AGAIN = "Por favor, tente novamente!";
	private static final String LOGIN_FAIL = "Login Incorreto";
	

	public LoginOnBlog() {
		userName = new TextField("Login:", "", 10, TextField.ANY);
		password = new TextField("Senha:", "", 10, TextField.PASSWORD);
		form = new Form("Entrar no Blogao");
		cancelar = new Command("Cancelar", Command.CANCEL, 2);
		login = new Command("Login", Command.OK, 2);
	}

	public void startApp() {
		display = Display.getDisplay(this);
		form.append(userName);
		form.append(password);
		form.addCommand(cancelar);
		form.addCommand(login);
		form.setCommandListener(this);
		display.setCurrent(form);
	}

	public void pauseApp() {
	}

	public void destroyApp(boolean unconditional) {
		notifyDestroyed();
	}

	// FIXME Ajeitar a busca de usuarios...
	public void validateUser(String name, String password) {
		if (name.equals("tiago") && password.equals("1234")) {
			display.vibrate(500);
			
			menu(); // FIXME Redirecionar para o MenuAfterLogin
		} else {
			tryAgain();
		}
	}

	//FIXME Ajeitar isso aqui
	public void menu() {
		MenuAfterLogin menuAfterLogin = new MenuAfterLogin();
		display = menuAfterLogin.getDisplay();
		menuAfterLogin.startApp();
		display.setCurrent(menuAfterLogin.getList());
//		List services = new List("Choose one", Choice.EXCLUSIVE);
//		services.append("Check Mail", null);
//		services.append("Compose", null);
//		services.append("Addresses", null);
//		services.append("Options", null);
//		services.append("Sign Out", null);
//		display.setCurrent(services);
	}

	/**
	 * Metodo que mostra novamente a tela do login.
	 */
	public void tryAgain() {
		Alert error = new Alert(LOGIN_FAIL, TRY_AGAIN, null, AlertType.ERROR);
		error.setTimeout(Alert.FOREVER);
		userName.setString("");
		password.setString("");
		display.setCurrent(error, form);
	}

	//FIXME Ajeitar este metodo
	public void commandAction(Command c, Displayable d) {
		String label = c.getLabel();
		if (label.equals("Cancelar")) {
			destroyApp(true);
		} else if (label.equals("Login")) {
			validateUser(userName.getString(), password.getString());
			
//			MenuAfterLogin menuAfterLogin = new MenuAfterLogin();			
//			menuAfterLogin.startApp();
//			menuAfterLogin.commandAction(c, d);
		}
	}
}