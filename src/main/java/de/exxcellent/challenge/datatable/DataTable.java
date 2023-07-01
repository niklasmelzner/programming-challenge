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
        throw new UnsupportedOperationException("not implemented");
    }

    public static class ColumnNotFoundException extends RuntimeException {

    }


}

