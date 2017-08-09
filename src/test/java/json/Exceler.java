package json;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.CellType;

public class Exceler {
    public static void main(String [] args) throws IOException, InvalidFormatException {
        Workbook wb = WorkbookFactory.create(new File("Book1.xlsx"));

        for(Sheet s: wb) {
            processSheet(s);
        }
    }

    private static void processSheet(Sheet s) {
        DataFormatter df = new DataFormatter();
        System.out.println(s.getSheetName());
        Iterator<Row> ri = s.rowIterator();
        for(Row row: s) {
            for(Cell cell: row) {
                System.out.print(cell.toString() + " -- ");
                System.out.println(f(cell.getCellTypeEnum(), cell));
            }
        }

    }

    private static String f (CellType tpe, Cell cell) {
        switch (tpe) {
            case STRING:
                return(cell.getRichStringCellValue().getString());
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return(cell.getDateCellValue().toString());
                } else {
                    return(String.valueOf(cell.getNumericCellValue()));
                }
            case BOOLEAN:
                return(String.valueOf(cell.getBooleanCellValue()));
            case FORMULA:
                return(f(cell.getCachedFormulaResultTypeEnum(), cell));

            case BLANK:
                return("blank");
            default:
                return("other");
        }
    }
}
