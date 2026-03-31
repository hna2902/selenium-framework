package framework.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {
    public static Object[][] getData(String relativePath, String sheetName) {
        // Tự động nối đường dẫn tuyệt đối để không bao giờ lỗi FileNotFound
        String fullPath = System.getProperty("user.dir") + File.separator + relativePath;
        try (FileInputStream fis = new FileInputStream(fullPath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheet(sheetName);
            int lastRow = sheet.getLastRowNum();
            int lastCol = sheet.getRow(0).getLastCellNum();
            Object[][] data = new Object[lastRow][lastCol];

            for (int r = 1; r <= lastRow; r++) {
                Row row = sheet.getRow(r);
                for (int c = 0; c < lastCol; c++) {
                    data[r - 1][c] = getCellValue(row.getCell(c));
                }
            }
            return data;
        } catch (IOException e) {
            throw new RuntimeException("Lỗi đọc Excel tại: " + fullPath, e);
        }
    }

    private static String getCellValue(Cell cell) {
        if (cell == null) return "";
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue().trim();
            case NUMERIC -> String.valueOf((long) cell.getNumericCellValue());
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            case FORMULA -> String.valueOf(cell.getNumericCellValue());
            default -> "";
        };
    }
}