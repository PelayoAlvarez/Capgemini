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

import com.capgemini.piloto.model.Empleado;

public class ExportEmpleados {
	
	private Logger log = LoggerFactory.getLogger(getClass());

	private String fileName;
	private Workbook workbook;
	private Sheet sheet;

	public ExportEmpleados(String fileName) {
		this.fileName = "C:\\Windows\\Temp\\"+fileName+".xlsx";
		this.workbook = new XSSFWorkbook();
		sheet = workbook.createSheet();
	}

	public boolean export(List<Empleado> empleados) {
		boolean exportado = false;
		createHeader();
		for (int i = 1; i <= empleados.size(); i++) {
			Row row = sheet.createRow(i);
			row.createCell(0).setCellValue(empleados.get(i - 1).getDni());
			row.createCell(1).setCellValue(empleados.get(i - 1).getNombre());
			row.createCell(2).setCellValue(empleados.get(i - 1).getApellidos());
			row.createCell(3).setCellValue(empleados.get(i - 1).getDireccion());
			row.createCell(4).setCellValue(empleados.get(i - 1).getFijo());
			row.createCell(5).setCellValue(empleados.get(i - 1).getMovil());
			row.createCell(6).setCellValue(empleados.get(i - 1).getEmail());
		}

		try (FileOutputStream outputStream = new FileOutputStream(fileName)) {
			log.info("Saving the file");
			workbook.write(outputStream);
			
			log.info("Opening the file");
			Runtime.getRuntime().exec(new String[] {"cmd.exe", "/C", fileName});
			exportado = true;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return exportado;

	}

	private void createHeader() {
		Row row = sheet.createRow(0);
		row.createCell(0).setCellValue("NIF/NIE");
		row.createCell(1).setCellValue("Nombre");
		row.createCell(2).setCellValue("Apellidos");
		row.createCell(3).setCellValue("Dirección");
		row.createCell(4).setCellValue("Teléfono fijo");
		row.createCell(5).setCellValue("Teléfono móvil");
		row.createCell(6).setCellValue("Correo electrónico");
	}

}
