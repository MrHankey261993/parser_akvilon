package org.mrhankey.app;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

import org.mrhankey.controll.ButtonListener;
import org.mrhankey.model.Spares;
import org.mrhankey.view.View;

public class Main {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		/*FileInputStream fis = new FileInputStream("text.txt");

		ObjectInputStream ois = new ObjectInputStream(fis);
		List <Spares> list= (List<Spares>) ois.readObject();
		System.out.println(list.size());*/
		
		ButtonListener b = new ButtonListener();
	}

}
