package de.exxcellent.challenge.datatable;

/**
 * Single, named column of a {@link DataTable}
 * This implementation is read only
 * @param <T> data type of the column
 */
public class Column<T> {
    private final T[] values;

    public Column(T[] values) {
        this.values = values;
    }

    public int size() {
        return values.length;
    }

    public T get(int index) {
        return values[index];
    }
}
