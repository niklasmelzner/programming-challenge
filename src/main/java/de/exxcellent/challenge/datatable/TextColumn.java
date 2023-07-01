package de.exxcellent.challenge.datatable;

/**
 * {@link Column} with data type {@link String}
 * Can be converted to a {@link NumericalColumn}
 */
public class TextColumn extends Column<String> {
    private NumericalColumn numericalColumn;

    public TextColumn(String[] values) {
        super(values);
    }

    /**
     * Converts this {@link Column} to a {@link NumericalColumn}.
     * The copies are independent, changes in the returned object will not reflect in this instance.
     * The method will return the same object on every method call.
     */
    NumericalColumn toNumericalColumn() {
        throw new UnsupportedOperationException("not implemented");
    }
}

