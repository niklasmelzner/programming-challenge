package de.exxcellent.challenge;

import de.exxcellent.challenge.datatable.CSVTableSource;
import de.exxcellent.challenge.datatable.DataTable;
import de.exxcellent.challenge.datatable.NumericalColumn;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Tests {@link CSVTableSource}
 */
public class DataTableTest {

    @Test
    public void loadTableTest() throws IOException {
        loadTable();
    }

    @Test
    public void getColumnTest() throws IOException {
        DataTable table = loadTable();
        Assertions.assertDoesNotThrow(
                () -> table.getColumn("MxT")
        );
        Assertions.assertThrows(DataTable.ColumnNotFoundException.class,
                () -> table.getColumn("This column doesn't exist")
        );
    }

    @Test
    public void convertColumnToNumericTest() throws IOException {
        DataTable table = loadTable();
        table.getColumn("MxT").toNumericalColumn();
    }

    @Test
    public void verifyDataTest() throws IOException {
        DataTable table = loadTable();
        NumericalColumn column = table.getColumn("Mxt").toNumericalColumn();
        Assertions.assertEquals(90, column.get(4));
    }


    private static DataTable loadTable() throws IOException {
        try (InputStream source = DataTableTest.class.getResourceAsStream("weather.csv")) {
            if (source == null) {
                throw new FileNotFoundException("resource doesn't exist");
            }
            return new CSVTableSource(source).getData();
        }
    }

}
