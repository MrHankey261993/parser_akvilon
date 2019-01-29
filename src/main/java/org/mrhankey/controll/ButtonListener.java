package org.mrhankey.controll;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import org.mrhankey.util.UtilParser;
import org.mrhankey.view.View;

public class ButtonListener {
	static View view = new View();
	static CreateFile createFile = new CreateFile();
	static Parser parser = new Parser();
	static UtilParser utilParser = new UtilParser();

	public static ActionListener listenerCreateButton() {

		ActionListener actionListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					createFile.createFile();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

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
				try {
					
					JPanel panel = View.creatProgressBar("", true);
					panel.setVisible(true);
					utilParser.linksProduct();
					JOptionPane.showMessageDialog(view, "Список обнавлён");

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};
		return actionListener;

	}

	public static ActionListener listenerNotUpdateButton() {
		ActionListener actionListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					FileInputStream fis = new FileInputStream("links.txt");
					ObjectInputStream ois = new ObjectInputStream(fis);
					utilParser.setLinksProducts((List<String>) ois.readObject());
					parser.parser(utilParser.getLinksProducts());
					JOptionPane.showMessageDialog(view, "Сылки успешно загружены");
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		};
		return actionListener;
	}

}
