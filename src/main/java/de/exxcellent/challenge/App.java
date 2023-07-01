package de.exxcellent.challenge;

import de.exxcellent.challenge.datatable.CSVTableSource;
import de.exxcellent.challenge.datatable.DataTable;
import de.exxcellent.challenge.datatable.NumericalColumn;
import de.exxcellent.challenge.datatable.TextColumn;

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
     * @param args The CLI arguments passed
     */
    public static void main(String... args) {

        // Your preparation code …
        DataTable weatherTable = loadTableFromResource("weather.csv");

        String dayWithSmallestTempSpread = findDayWithSmallestTempSpread(weatherTable);     // Your day analysis function call …
        System.out.printf("Day with smallest temperature spread : %s%n", dayWithSmallestTempSpread);

        String teamWithSmallestGoalSpread = "A good team"; // Your goal analysis function call …
        System.out.printf("Team with smallest goal spread       : %s%n", teamWithSmallestGoalSpread);
    }

    /**
     * Returns the first day having the smallest temperature spread
     * @param weatherTable table containing weather data
     * @return Day with the smallest temperature spread
     */
    private static String findDayWithSmallestTempSpread(DataTable weatherTable) {
        TextColumn day = weatherTable.getColumn("Day");
        NumericalColumn maximumTemp = weatherTable.getColumn("MxT").toNumericalColumn();
        NumericalColumn minimumTemp = weatherTable.getColumn("MnT").toNumericalColumn();

        double minDifference = Double.MAX_VALUE;
        String minDifferenceDay = null;
        // iterate over table, store result for minimum temperature spread
        for (int instanceIndex = 0; instanceIndex < day.size(); instanceIndex++) {
            double difference =  maximumTemp.get(instanceIndex) - minimumTemp.get(instanceIndex);
            if (difference>=minDifference) {
                continue;
            }
            minDifference = difference;
            minDifferenceDay = day.get(instanceIndex);
        }

        return minDifferenceDay;
    }

    /**
     * Loads data from an {@link InputStream} using {@link CSVTableSource}
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
