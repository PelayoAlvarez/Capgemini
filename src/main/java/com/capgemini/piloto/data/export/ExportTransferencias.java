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

import com.capgemini.piloto.model.dto.ListarTransferenciasNumeroCuentaDTO;

public class ExportTransferencias {
	private Logger log = LoggerFactory.getLogger(getClass());

	private String fileName;
	private Workbook workbook;
	private Sheet sheet;

	public ExportTransferencias(String fileName) {
		this.fileName = "C:\\Windows\\Temp\\"+fileName+".xlsx";
		this.workbook = new XSSFWorkbook();
		sheet = workbook.createSheet();
	}

	@SuppressWarnings("deprecation")
	public boolean export(List<ListarTransferenciasNumeroCuentaDTO> list) {
		boolean exportado = false;
		createHeader();
		for (int i = 1; i <= list.size(); i++) {
			Row row = sheet.createRow(i);
			row.createCell(0).setCellValue(list.get(i - 1).getIdDestino());
			row.createCell(1).setCellValue(list.get(i - 1).getCuenta());
			row.createCell(2).setCellValue(list.get(i - 1).getImporte());
			row.createCell(3).setCellValue(list.get(i - 1).getFechaRealizacion().toLocaleString());
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
		row.createCell(0).setCellValue("Cuenta de destino");
		row.createCell(1).setCellValue("Cuenta Origen");
		row.createCell(2).setCellValue("Importe");
		row.createCell(3).setCellValue("Fecha de realizacion");
	}

}