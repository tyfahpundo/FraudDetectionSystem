package zw.ca.hit.frauddetectionsystem.helper;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import zw.ca.hit.frauddetectionsystem.domain.FinancialStatement;

public class ExcelHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = { "Id", "Title", "Description", "Published" };
    static String SHEET = "FinancialStatement";
    public static boolean hasExcelFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }
        public static List<FinancialStatement> excelToFinancialStatement(InputStream is) {
            try {
                Workbook workbook = new XSSFWorkbook(is);
                Sheet sheet = workbook.getSheet(SHEET);
                Iterator<Row> rows = sheet.iterator();
                List<FinancialStatement> statements = new ArrayList<FinancialStatement>();
                int rowNumber = 0;
                while (rows.hasNext()) {
                    Row currentRow = rows.next();
                    // skip header
                    if (rowNumber == 0) {
                        rowNumber++;
                        continue;
                    }
                    Iterator<Cell> cellsInRow = currentRow.iterator();
                    FinancialStatement statement = new FinancialStatement();
                    int cellIdx = 0;
                    while (cellsInRow.hasNext()) {
                        Cell currentCell = cellsInRow.next();
                        switch (cellIdx) {
                            case 0:
                                statement.setId((long) currentCell.getNumericCellValue());
                                break;
                            case 1:
                                statement.setSales((long) currentCell.getNumericCellValue());
                                break;
                            case 2:
                                statement.setAmount(BigDecimal.valueOf(currentCell.getNumericCellValue()));
                                break;
                            case 3:
                                statement.setName(currentCell.getStringCellValue());
                                break;
                            default:
                                break;
                        }
                        cellIdx++;
                    }
                    statements.add(statement);
                }
                workbook.close();
                return statements;
            } catch (IOException e) {
                throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
            }
        }
}
