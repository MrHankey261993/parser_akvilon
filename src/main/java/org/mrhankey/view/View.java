package org.mrhankey.view;

import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.mrhankey.controll.ButtonListener;

public class View extends JFrame {

	private JButton updateList;
	private JButton addInBD;
	private JButton createFile;
	private JButton updateLinks;
	private JButton notUpdateLinks;




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
		add(panel);
		panel.add(updateList);
		panel.add(addInBD);
		panel.add(createFile);
		panel.setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(200, 170);
		setVisible(true);
		setLocationRelativeTo(null);
		setLayout(null);
	}

	public JDialog createDialog(String title, boolean modal) {
		JDialog dialog = new JDialog(this, title, modal);
		JLabel label = new JLabel("Хотите обновить сылки?");
		label.setBounds(50, 30, 150, 30);
		updateLinks = new JButton("Да");
		updateLinks.setMargin(new Insets(0, 0, 0, 0));
		updateLinks.setBounds(0, 70, 125, 50);
		updateLinks.addActionListener(ButtonListener.listenerUpdateLinksButton());
		notUpdateLinks = new JButton("Нет");
		notUpdateLinks.setMargin(new Insets(0, 0, 0, 0));
		notUpdateLinks.setBounds(125, 70, 125, 50);
		notUpdateLinks.addActionListener(ButtonListener.listenerNotUpdateButton());
		dialog.add(label);
		dialog.add(updateLinks);
		dialog.add(notUpdateLinks);
		dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setSize(250, 160);
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
