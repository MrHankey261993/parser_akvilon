package org.mrhankey.controll;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.apache.log4j.Logger;
import org.mrhankey.util.UtilParser;
import org.mrhankey.view.View;

public class ButtonListener {
	static View view = new View();
	static CreateFile createFile = new CreateFile();
	static Parser parser = new Parser();
	static UtilParser utilParser = new UtilParser();
	private static Logger log = Logger.getLogger(ButtonListener.class);
	public static ActionListener listenerCreateButton() {

		ActionListener actionListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				createFile.createFile();

			}
		};
		return actionListener;

	}

	public static ActionListener listenerUpdateButton() {
		ActionListener actionListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JDialog dialog = view.createDialog("", true);
				dialog.setVisible(true);

			}
		};
		return actionListener;

	}

	public static ActionListener listenerUpdateLinksButton() {
		ActionListener actionListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel panel = View.creatProgressBar("", true);
				panel.setVisible(true);
				parser.parser(utilParser.linksProduct());
				JOptionPane.showMessageDialog(view, "Список обнавлён");
			}
		};
		return actionListener;

	}

	public static ActionListener listenerNotUpdateButton() {
		ActionListener actionListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try (FileInputStream fis = new FileInputStream("links.txt");
						ObjectInputStream ois = new ObjectInputStream(fis)) {				
					utilParser.setLinksProducts((List<String>) ois.readObject());
					JOptionPane.showMessageDialog(view, "Сылки успешно загружены");
					parser.parser(utilParser.getLinksProducts());				
				} catch (FileNotFoundException e1) {
					log.error(e1.getMessage());
				} catch (IOException e1) {
					log.error(e1.getMessage());
				} catch (ClassNotFoundException e1) {
					log.error(e1.getMessage());
				}

			}
		};
		return actionListener;
	}

}
