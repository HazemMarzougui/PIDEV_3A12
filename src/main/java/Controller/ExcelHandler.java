package Controller;
import javafx.collections.ObservableList;
import Entities.Conseil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import Services.ConseilService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ExcelHandler {
    ConseilService conseilService = new ConseilService();
    // Méthode pour lire un fichier Excel
    public void readExcelFile(String filePath) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File(filePath));
        Workbook workbook = WorkbookFactory.create(fileInputStream);
        Sheet sheet = workbook.getSheetAt(0); // Get the first sheet

        // Iterate over rows
        for (Row row : sheet) {
            // Iterate over cells
            for (Cell cell : row) {
                System.out.print(cell.toString() + "\t");
            }
            System.out.println();
        }

        workbook.close();
        fileInputStream.close();
    }

    // Méthode pour écrire dans un fichier Excel
    public void writeExcelFile(String filePath) throws IOException {
        try {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Conseils");

            // Création de l'en-tête
            Row headerRow = sheet.createRow(0);
            String[] headers = {"ID", "NOM_CONSEIL", "VIDEO", "DESCRIPTION", "PRODUIT", "TYPE_CONSEIL"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                // Appliquer le style au titre
                CellStyle style = workbook.createCellStyle();
                Font font = workbook.createFont();
                font.setBold(true);
                style.setFont(font);
                cell.setCellStyle(style);
            }
            ConseilService conseilService1 = new ConseilService();
            // Remplissage des données
            List<Conseil> conseilList = conseilService1.displayConseil();
            for (int i = 0; i < conseilList.size(); i++) {
                Conseil conseil = conseilList.get(i);
                Row row = sheet.createRow(i + 1);
                row.createCell(0).setCellValue(conseil.getId_conseil());
                row.createCell(1).setCellValue(conseil.getNom_conseil());
                row.createCell(2).setCellValue(conseil.getVideo());
                row.createCell(3).setCellValue(conseil.getDescription());
                row.createCell(4).setCellValue(conseil.getId_produit());
                row.createCell(5).setCellValue(conseil.getId_typeC());
            }

            // Redimensionner automatiquement les colonnes pour s'adapter au contenu
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // Sauvegarde du fichier Excel
            FileOutputStream fileOut = new FileOutputStream("Conseils.xlsx");
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
