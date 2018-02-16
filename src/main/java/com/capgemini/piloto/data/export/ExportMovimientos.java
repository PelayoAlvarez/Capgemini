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

import com.capgemini.piloto.model.Movimiento;

public class ExportMovimientos {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	private String fileName;
	private Workbook workbook;
	private Sheet sheet;
	
	public ExportMovimientos(String fileName) {
		this.fileName = "C:\\Windows\\Temp\\"+fileName+".xlsx";
		this.workbook = new XSSFWorkbook();
		sheet = workbook.createSheet();
	}
	
	public boolean export(List<Movimiento> movimientos) {
		boolean exportado = false;
		if(movimientos.isEmpty()) {
			return exportado;
		}
		createHeader();
		for (int i = 1; i <= movimientos.size(); i++) {
			Row row = sheet.createRow(i);
			row.createCell(0).setCellValue(movimientos.get(i - 1).getId());
			row.createCell(1).setCellValue(movimientos.get(i - 1).getImporte());
			row.createCell(2).setCellValue(movimientos.get(i - 1).getTipo().name());
			row.createCell(3).setCellValue(movimientos.get(i - 1).getFechahora());
			row.createCell(4).setCellValue(movimientos.get(i - 1).getDescripcion());
			row.createCell(5).setCellValue(movimientos.get(i - 1).getCuentaAsociada().getNumeroCuenta());
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
		row.createCell(1).setCellValue("Importe");
		row.createCell(2).setCellValue("Tipo");
		row.createCell(3).setCellValue("Fecha");
		row.createCell(4).setCellValue("Descripción");
		row.createCell(5).setCellValue("Cuenta Asociada");
	}
}
