package com.capgemini.piloto.data.export;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.capgemini.piloto.model.Sucursal;

public class ExportSucursales {
	
	private Logger log = LoggerFactory.getLogger(getClass());

	private String fileName;
	private Workbook workbook;
	private Sheet sheet;

	public ExportSucursales(String fileName) {
		this.fileName = "C:\\Windows\\Temp\\"+fileName+".xlsx";
		this.workbook = new XSSFWorkbook();
		sheet = workbook.createSheet();
	}

	public boolean export(List<Sucursal> sucursales) {
		boolean exportado = false;
		createHeader();
		for (int i = 1; i <= sucursales.size(); i++) {
			Row row = sheet.createRow(i);
			row.createCell(0).setCellValue(sucursales.get(i - 1).getId());
			row.createCell(1).setCellValue(sucursales.get(i - 1).getNombre());
			row.createCell(2).setCellValue(sucursales.get(i - 1).getDireccion());
		}

		try (FileOutputStream outputStream = new FileOutputStream(fileName)) {
			log.info("Saving the file");
			workbook.write(outputStream);
			
			log.info("Opening the file");
			Runtime.getRuntime().exec(new String[] {"cmd.exe", "/C", fileName});
			exportado = true;
			
		} catch (FileNotFoundException e) {
			log.warn("FileNotFoundException", e);
		} catch (IOException e) {
			log.warn("IOException", e);	
		}
		return exportado;

	}

	private void createHeader() {
		Row row = sheet.createRow(0);
		row.createCell(0).setCellValue("Id");
		row.createCell(1).setCellValue("Nombre");
		row.createCell(2).setCellValue("Direccion");
	}

}
