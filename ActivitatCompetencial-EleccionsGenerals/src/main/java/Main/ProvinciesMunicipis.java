package Main;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

public class ProvinciesMunicipis {

    public static void ProvinciesMunicipis() {

      try {

      	//Obtenim el directori actual
      	Path pathActual = Paths.get(System.getProperty("user.dir"));

      	//Concatenem el directori actual amb un subdirectori "dades" i afegim el fitxer "03021911.DAT"
      	String nomFitxer = "02_201911_1.xlsx";

      	Path pathFitxer = Paths.get(pathActual.toString(), "dades",nomFitxer );

      	FileInputStream excelFile = new FileInputStream(new File(pathFitxer.toString()));
      	Workbook workbook = new XSSFWorkbook(excelFile);
      	Sheet fulla = workbook.getSheetAt(0); // Obtenim la primera fulla

      	/*
      	fila 7 comencen les dades
      	Columnes:
      	   1: Nom de la comunitat
      	   2: Codi de Provincia
      	   3: Nom de la Provincia
      	   4: Codi de Municipi
      	   5: Nom de Municipi
      	   6: Població
      	   7: Número de meses
      	   8: Total del cens electoral
      	   9: Total de vots
      	   10: Vots vàlids
      	   11: Vots a candidatures
      	   12: Vots en blanc
      	   13: Vots nuls
      	   14: shee.max_column (partits polítics)
      	*/

      	//fulla.getLastRowNum() retornem índex de la última fila
      	//fila.getLastCellNum() retornem índex de la última cela dins de la fila.

      	//Recorrem  97 files de la fulla
      	for(int i = 7; i<100; i++) {
      		Row fila = fulla.getRow(i);
      		//Imprimim els valors de la columna 4 i 5
      		Cell cela = fila.getCell(3);

      		System.out.print(cela.getNumericCellValue() + "--");

      		System.out.println(fila.getCell(4).getStringCellValue() + "--");
      	}

      	/*
      	-- Construïm un interador sobre la fulla.
      	*/
      	Iterator<Row> rowIterator = fulla.iterator();

      	 // Bucle per recòrrer totes les files i columnes de la fulla
      	while(rowIterator.hasNext()) {

      		Row filaActual  = rowIterator.next();

      		//Construïm un interador per les columnes.
      		Iterator<Cell> cellIterator = filaActual.iterator();
      		while (cellIterator.hasNext()) {

      			Cell celaActual = cellIterator.next();

      			if (celaActual.getCellType() == CellType.STRING) {
      				System.out.print(celaActual.getStringCellValue() + "--");
      			} else if (celaActual.getCellType() == CellType.NUMERIC) {
      				System.out.print(celaActual.getNumericCellValue() + "--");
      			}

      		}
      		System.out.println();
      	}

      } catch (FileNotFoundException e) {
      	e.printStackTrace();
      } catch (IOException e) {
      	e.printStackTrace();
      }

    }

}
