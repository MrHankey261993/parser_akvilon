package org.mrhankey.view;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.mrhankey.controll.ButtonListener;
import org.mrhankey.controll.Parser;
import org.mrhankey.util.UtilParser;

public class View extends JFrame {

	private JButton updateList;
	private JButton addInBD;
	private JButton createFile;
	private JButton updateLinks;
	private JButton notUpdateLinks;
	Parser parser = new Parser();
	UtilParser utilParser = new UtilParser();




	public void init() {

		JPanel panel = new JPanel();
		updateList = new JButton("Обновить список");
		updateList.setBounds(20, 20, 190, 30);
		updateList.addActionListener(ButtonListener.listenerUpdateButton());
		
		addInBD = new JButton("Добавить в базу данных");
		addInBD.setBounds(20, 70, 190, 30);
		createFile = new JButton("Создать файл");
		createFile.setBounds(20, 120, 190, 30);
		createFile.addActionListener(ButtonListener.listenerCreateButton());
/*		updateList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialog dialog = createDialog("Модальное", true);
				dialog.setVisible(true);
			}
		});*/
		add(panel);
		panel.add(updateList);
		panel.add(addInBD);
		panel.add(createFile);
		panel.setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(200, 200);
		setVisible(true);
		setLocationRelativeTo(null);
		setLayout(null);
	}

	public JDialog createDialog(String title, boolean modal) {
		JDialog dialog = new JDialog(this, title, modal);
		updateLinks = new JButton("Обновить сылки");
		updateLinks.setMargin(new Insets(0, 0, 0, 0));
		updateLinks.setBounds(0, 100, 125, 50);
		updateLinks.addActionListener(ButtonListener.listenerUpdateLinksButton());
		notUpdateLinks = new JButton("Не обновлять сылки");
		notUpdateLinks.setMargin(new Insets(0, 0, 0, 0));
		notUpdateLinks.setBounds(125, 100, 125, 50);
		notUpdateLinks.addActionListener(ButtonListener.listenerNotUpdateButton());
		dialog.add(updateLinks);
		dialog.add(notUpdateLinks);
		dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setSize(250, 200);
		dialog.setLocationRelativeTo(null);
		dialog.setLayout(null);
		return dialog;
	}
	public static JPanel creatProgressBar(String title, boolean modal) {
		JPanel panel = new JPanel();
		panel.setSize(200, 200);
		panel.setLayout(null);;
		
		return panel;
		
	}

	public JButton getUpdateLinks() {
		return updateLinks;
	}

	public void setUpdateLinks(JButton updateLinks) {
		this.updateLinks = updateLinks;
	}

	public JButton getNotUpdateLinks() {
		return notUpdateLinks;
	}

	public void setNotUpdateLinks(JButton notUpdateLinks) {
		this.notUpdateLinks = notUpdateLinks;
	}

	public JButton getUpdateList() {
		return updateList;
	}

	public void setUpdateList(JButton updateList) {
		this.updateList = updateList;
	}

	public JButton getAddInBD() {
		return addInBD;
	}

	public void setAddInBD(JButton addInBD) {
		this.addInBD = addInBD;
	}

	public JButton getCreateFile() {
		return createFile;
	}

	public void setCreateFile(JButton createFile) {
		this.createFile = createFile;
	}

}
