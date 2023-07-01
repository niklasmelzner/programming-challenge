package de.exxcellent.challenge;

import de.exxcellent.challenge.datatable.CSVTableSource;
import de.exxcellent.challenge.datatable.DataTable;
import de.exxcellent.challenge.datatable.NumericalColumn;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * The entry class for your solution. This class is only aimed as starting point and not intended as baseline for your software
 * design. Read: create your own classes and packages as appropriate.
 *
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
public final class App {

    /**
     * This is the main entry method of your program.
     *
     * @param args The CLI arguments passed
     */
    public static void main(String... args) {

        // Your preparation code …
        DataTable weatherTable = loadTableFromResource("weather.csv");

        String dayWithSmallestTempSpread = weatherTable.getColumn("Day").get(
                findIndexWithMinAbsDistance(weatherTable, "MnT", "MxT")
        );     // Your day analysis function call …
        System.out.printf("Day with smallest temperature spread : %s%n", dayWithSmallestTempSpread);

        DataTable footballTable = loadTableFromResource("football.csv");

        String teamWithSmallestGoalSpread = footballTable.getColumn("Team").get(
                findIndexWithMinAbsDistance(footballTable, "Goals", "Goals Allowed")
        );      // Your goal analysis function call …
        System.out.printf("Team with smallest goal spread       : %s%n", teamWithSmallestGoalSpread);
    }

    /**
     * The order of provided columns doesn't mather
     * Returns the first instance having the smallest distance between the two columns
     * @return Instance index, -1 if the table is empty
     */
    private static int findIndexWithMinAbsDistance(DataTable table, String firstColumnName, String secondColumnName) {
        NumericalColumn firstColumn = table.getColumn(firstColumnName).toNumericalColumn();
        NumericalColumn secondColumn = table.getColumn(secondColumnName).toNumericalColumn();

        double minDifference = Double.MAX_VALUE;
        int minDifferenceIndex = -1;
        // iterate over table, store result for minimum temperature spread
        for (int instanceIndex = 0; instanceIndex < firstColumn.size(); instanceIndex++) {
            double difference = Math.abs(firstColumn.get(instanceIndex) - secondColumn.get(instanceIndex));
            if (difference >= minDifference) {
                continue;
            }
            minDifference = difference;
            minDifferenceIndex = instanceIndex;
        }

        return minDifferenceIndex;
    }

    /**
     * Loads data from an {@link InputStream} using {@link CSVTableSource}
     *
     * @param resourceName name of the resource (in the scope of {@link App})
     * @return data in form of a {@link DataTable}
     */
    private static DataTable loadTableFromResource(String resourceName) {
        try (InputStream inputStream = App.class.getResourceAsStream(resourceName)) {
            if (inputStream == null) {
                throw new FileNotFoundException("resource '" + resourceName + "' was not found");
            }
            return new CSVTableSource(inputStream).getData();
        } catch (IOException e) {
            throw new RuntimeException("Error while loading 'weather.csv'", e);
        }
    }
}
