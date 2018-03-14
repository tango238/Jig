package jig.classlist;

import jig.domain.model.list.ConverterCondition;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Logger;

@Component
public class ExcelWriter extends AbstractListWriter {

    private static final Logger logger = Logger.getLogger(ExcelWriter.class.getName());

    public void writeTo(Path output) {

        try (Workbook book = new XSSFWorkbook();
             OutputStream os = Files.newOutputStream(output)) {
            Sheet sheet = book.createSheet();
            writeRow(modelKind.headerLabel(), sheet.createRow(0));

            for (ConverterCondition condition : list()) {
                writeRow(modelKind.row(condition), sheet.createRow(sheet.getLastRowNum() + 1));
            }

            book.write(os);
            logger.info(output.toAbsolutePath() + "を出力しました。");
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private void writeRow(List<String> list, Row row) {
        list.forEach(item -> {
            short lastCellNum = row.getLastCellNum();
            Cell cell = row.createCell(lastCellNum == -1 ? 0 : lastCellNum);
            cell.setCellValue(item);
        });
    }
}
