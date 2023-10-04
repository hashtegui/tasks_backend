package org.mestresolutions.tasks.services;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

@Service
public class ExcelService<T> {
  // precisa receber um tipo T que vai verificar o tamanho do array e dos
  Field[] camposClasse = null;

  // atributos da classe para gerar o excel
  public void gerarExcel(List<T> arrayDeObjetos) {
    // armazenar lista de campos do tipo
    List<String> fields = new ArrayList<>();

    Workbook workbook = new XSSFWorkbook();
    var clazz = arrayDeObjetos.get(0).getClass();

    camposClasse = clazz.getDeclaredFields();

    for (Field field : camposClasse) {
      fields.add(field.getName());
    }

    Sheet planilhaCriada = criarPanilha(workbook, fields);

    inserirDados(planilhaCriada, arrayDeObjetos, fields);

    try {
      FileOutputStream fileOut = new FileOutputStream("output.xlsx");
      workbook.write(fileOut);
      fileOut.close();
      workbook.close();
      System.out.println("Excel file generated successfully.");

    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  private Sheet criarPanilha(Workbook workbook, List<String> fields) {

    Sheet planilha = workbook.createSheet("Sheet 1");
    criarCabeçalho(planilha, fields);

    return planilha;
  }

  private void criarCabeçalho(Sheet planilha, List<String> campos) {

    Row cabecalho = planilha.createRow(0);
    CellStyle cabecalhoStyle = planilha.getWorkbook().createCellStyle();
    Font font = planilha.getWorkbook().createFont();
    font.setBold(true);

    cabecalhoStyle.setFont(font);

    for (int i = 0; i < campos.size(); i++) {
      Cell cabecalhoCell = cabecalho.createCell(i);
      cabecalhoCell.setCellValue(campos.get(i).toUpperCase());
      cabecalhoCell.setCellStyle(cabecalhoStyle);
      planilha.autoSizeColumn(i);

    }

  }

  private void inserirDados(Sheet planilha, List<T> arrayDeObjetos, List<String> campos) {
    for (int i = 0; i < arrayDeObjetos.size(); i++) {
      Row row = planilha.createRow(i + 1);
      var obj = arrayDeObjetos.get(i);

      for (int j = 0; j < campos.size(); j++) {
        Cell cell = row.createCell(j);

        var valor = recuperarValorObjeto(obj, obj.getClass(), campos.get(j));
        cell.setCellValue(null == valor ? "" : valor.toString());

      }
    }

  }

  private Object recuperarValorObjeto(Object obj, Class<? extends Object> class1, String campo) {
    try {
      Field field = class1.getDeclaredField(campo);
      field.setAccessible(true);
      // Object tipoDoCampo = field.getType();
      Object valor = field.get(obj);
      return valor;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
