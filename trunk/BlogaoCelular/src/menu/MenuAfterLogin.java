package menu;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.List;
import javax.microedition.midlet.MIDlet;


/**
 * Classe que inicializa o menu apos o login efetuado com sucesso.
 * @author Tiago Leite
 *
 */
public class MenuAfterLogin extends MIDlet implements CommandListener {

	private Display display;
	private Command exit;
	private Command submit;
	private List list;
	private static final String SELECT_AN_ITEM = "Selecione um item";

	public MenuAfterLogin() {
		display = Display.getDisplay(this);
		list = new List(SELECT_AN_ITEM, List.EXCLUSIVE);
		list.append("Blog", null);
		list.append("Visualizar Perfil", null);
		list.append("Opções", null);
		exit = new Command("Sair", Command.EXIT, 1);
		submit = new Command("Ok", Command.SCREEN, 2);
		list.addCommand(exit);
		list.addCommand(submit);
		list.setCommandListener(this);
	}

	public void startApp() {
		display.setCurrent(list);
	}

	public void pauseApp() {
	}

	public void destroyApp(boolean unconditional) {
		notifyDestroyed();
	}

	public void commandAction(Command command, Displayable Displayable) {
		if (command == submit) {
			Alert alert = new Alert("Escolha", list.getString(list
					.getSelectedIndex()), null, null);
			alert.setTimeout(Alert.FOREVER);
			alert.setType(AlertType.INFO);
			display.setCurrent(alert);
			list.removeCommand(submit);
		} else if (command == exit) {
			destroyApp(false);
			notifyDestroyed();
		}
	}
	
	public Display getDisplay() {
		return display;
	}
	
	public List getList() {
		return list;
	}
}
