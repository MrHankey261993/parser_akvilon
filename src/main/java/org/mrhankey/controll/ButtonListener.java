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

	View view = new View();
	CreateFile createFile = new CreateFile();
	Parser parser = new Parser();
	UtilParser utilParser = new UtilParser();

	public ButtonListener() {
		listenerCreateButton();
		listenerUpdateButton();
		/*
		 * listenerUpdateLinksButton(); listenerNotUpdateButton();
		 */
	}

	public void listenerCreateButton() {
		view.getCreateFile().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					createFile.createFile();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

	}

	public void listenerUpdateButton() {
		view.getUpdateList().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JDialog dialog = view.createDialog("", true);
				dialog.setVisible(true);
				

			}
		});
		
	}

	public void listenerUpdateLinksButton() {

		view.getUpdateLinks().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					utilParser.linksProduct();
					JOptionPane.showMessageDialog(view, "Список обнавлён");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

	}

	public void listenerNotUpdateButton() {
		view.getNotUpdateLinks().addActionListener(new ActionListener() {

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
		});
	}

}
