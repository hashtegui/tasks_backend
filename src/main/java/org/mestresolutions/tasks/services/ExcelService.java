package org.mestresolutions.tasks.services;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

@Service
public class ExcelService<T> {
  // precisa receber um tipo T que vai verificar o tamanho do array e dos
  // atributos da classe para gerar o excel
  public void gerarExcel(List<T> arrayDeObjetos, Class<T> clzz) {
    // armazenar lista de campos do tipo
    List<String> fields = new ArrayList<>();

    for (Field field : clzz.getDeclaredFields()) {
      fields.add(field.getName().toUpperCase());
    }

    System.out.println(fields);

    
  }

  private Workbook criarDocumentoWorkbook() {
    Workbook workbook = new XSSFWorkbook();

    return workbook;

  }

  private void montarCabecalhoEColunas(List<String> fields) {
    Workbook workbook = criarDocumentoWorkbook();
  }
}
