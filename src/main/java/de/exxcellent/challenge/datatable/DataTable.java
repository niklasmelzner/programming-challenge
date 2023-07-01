package de.exxcellent.challenge.datatable;

import java.util.HashMap;
import java.util.Map;

/**
 * Content of a {@link TableSource} in table format
 */
public class DataTable {
    private final Map<String, TextColumn> columns = new HashMap<>();

    public void addColumn(String name, TextColumn column) {
        columns.put(name, column);
    }

    public TextColumn getColumn(String name) {
        TextColumn column = columns.get(name);
        if (column == null) {
            throw new ColumnNotFoundException(name);
        }
        return column;
    }

    public static class ColumnNotFoundException extends RuntimeException {

        ColumnNotFoundException(String name) {
            super("Column '" + name + "' was not found in this data table");
        }

    }

}

