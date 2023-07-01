package de.exxcellent.challenge.datatable;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * CSV-{@link TableSource}, expects input data in CSV format where the first line includes column names
 */
public class CSVTableSource implements TableSource {

    private final DataTable table;

    /**
     * Loads a {@link DataTable} from the given {@link InputStream}
     * @param inputStream data-stream in CSV-format
     * @throws IOException if {@link InputStream#readAllBytes()} fails
     */
    public CSVTableSource(InputStream inputStream) throws IOException {
        // read data from stream
        String data = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);

        String[] lines = data.split("[\n\r]+");

        // extract column names from first line
        String[] columnNames = lines[0].split(",");

        // prepare table to store remaining data
        String[][] columns = new String[columnNames.length][lines.length - 1];

        // split remaining lines and write to columns
        for (int lineIndex = 1; lineIndex < lines.length; lineIndex++) {
            String[] values = lines[lineIndex].split(",");
            for (int valueIndex = 0; valueIndex < values.length; valueIndex++) {
                columns[valueIndex][lineIndex - 1] = values[valueIndex];
            }
        }

        // transform to data table
        table = new DataTable();
        for (int columnIndex = 0; columnIndex < columnNames.length; columnIndex++) {
            table.addColumn(columnNames[columnIndex], new TextColumn(columns[columnIndex]));
        }
    }

    @Override
    public DataTable getData() {
        return table;
    }
}
