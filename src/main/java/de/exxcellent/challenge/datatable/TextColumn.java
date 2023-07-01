package de.exxcellent.challenge.datatable;

/**
 * {@link Column} with data type {@link String}
 * Can be converted to a {@link NumericalColumn}
 */
public class TextColumn extends Column<String> {
    /**
     * Buffer für {@link #toNumericalColumn()}, damit die Datenkonvertierung nur einmal durchgeführt wird.
     **/
    private NumericalColumn numericalColumn;

    public TextColumn(String[] values) {
        super(values);
    }

    /**
     * Converts this {@link Column} to a {@link NumericalColumn}.
     * The copies are independent, changes in the returned object will not reflect in this instance.
     * The method will return the same object on every method call.
     *
     * @throws NumberFormatException if at least one value is not convertible to {@link Double}
     */
    public NumericalColumn toNumericalColumn() {
        if (numericalColumn == null) {
            Double[] values = new Double[size()];
            for (int i = 0; i < size(); i++) {
                values[i] = Double.valueOf(get(i));
            }
            numericalColumn = new NumericalColumn(values);
        }
        return numericalColumn;
    }
}

