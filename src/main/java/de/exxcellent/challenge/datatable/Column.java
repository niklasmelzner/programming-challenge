package de.exxcellent.challenge.datatable;

/**
 * Single, named column of a {@link DataTable}
 * This implementation is read only
 * @param <T> data type of the column
 */
public class Column<T> {
    private final String name;
    private final T[] values;

    public Column(String name, T[] values) {
        this.name = name;
        this.values = values;
    }

    public int size() {
        return values.length;
    }

    public T get(int index) {
        return values[index];
    }
}
