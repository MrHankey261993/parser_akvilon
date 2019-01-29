package org.mrhankey.controll;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

import javax.swing.JDialog;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.mrhankey.model.ListSpares;
import org.mrhankey.model.Spares;

public class CreateFile {
	

	public void createFile() throws IOException {
		List<Spares> listSpares = null;
		File file = new File("text.txt");
		if (file.length() != 0) {
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			
			try {
				listSpares = (List<Spares>) ois.readObject();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			Workbook workbook = new HSSFWorkbook();
			Sheet sheet = workbook.createSheet("Test");
			CellStyle style = createStyleForTitle(workbook);

			Cell cell;
			Row row;

			row = sheet.createRow(0);

			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("Articul");
			cell.setCellStyle(style);
			sheet.setColumnWidth(0, 3000);

			cell = row.createCell(1, CellType.STRING);
			cell.setCellValue("Name");
			cell.setCellStyle(style);
			sheet.setColumnWidth(1, 3000);

			cell = row.createCell(2, CellType.STRING);
			cell.setCellValue("Price");
			cell.setCellStyle(style);
			sheet.setColumnWidth(2, 3000);

			cell = row.createCell(3, CellType.STRING);
			cell.setCellValue("weight");
			cell.setCellStyle(style);
			sheet.setColumnWidth(3, 3000);

			cell = row.createCell(4, CellType.STRING);
			cell.setCellValue("Prodaction");
			cell.setCellStyle(style);
			sheet.setColumnWidth(4, 3000);

			cell = row.createCell(5, CellType.STRING);
			cell.setCellValue("applicability");
			cell.setCellStyle(style);
			sheet.setColumnWidth(5, 3000);

			cell = row.createCell(6, CellType.STRING);
			cell.setCellValue("Subgroup");
			cell.setCellStyle(style);
			sheet.setColumnWidth(6, 3000);

			for (int i = 1, y = 0; i < listSpares.size(); i++, y++) {
				row = sheet.createRow(i);
				cell = row.createCell(0, CellType.STRING);
				cell.setCellValue(listSpares.get(y).getArticul());
			}

			File fileExcel = new File("test.xls");
			// file.getParentFile().mkdirs();

			FileOutputStream outputStream = null;
			try {
				outputStream = new FileOutputStream(file);
			} catch (FileNotFoundException e) {
				// add log
				e.printStackTrace();
			}
			try {
				workbook.write(outputStream);
			} catch (IOException e) {
				// add log
				e.printStackTrace();
			}

			System.out.println(file.getAbsolutePath());
			fis.close();
		} else {
			JDialog dialog = new JDialog();
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);

		}

	}

	private static CellStyle createStyleForTitle(Workbook workbook) {
		HSSFFont font = (HSSFFont) workbook.createFont();
		font.setBold(true);
		CellStyle style = workbook.createCellStyle();
		style.setFont(font);
		style.setBorderBottom(BorderStyle.MEDIUM);
		style.setBorderTop(BorderStyle.MEDIUM);
		style.setBorderLeft(BorderStyle.MEDIUM);
		style.setBorderRight(BorderStyle.MEDIUM);

		return (HSSFCellStyle) style;
	}

	public static void main(String[] args) {
		try {
			new CreateFile().createFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		;
	}
}
