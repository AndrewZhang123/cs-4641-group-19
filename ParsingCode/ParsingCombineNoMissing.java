import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import java.util.HashMap;
public class ParsingCombineNoMissing {
    static final int DAY1 = 5;
    static final int DAY2 = 25;
    static final int DAY3 = 50;

    public static void main(String[] args) throws IOException {

        // scanners for relevant files: country dataset, cases dataset, and the mapping between the country names

        File countries = new File("FinalCountryDataset.csv");
        Scanner countriesSc = new Scanner(countries);
        File conversions = new File("conversion.csv");
        Scanner conversionSc = new Scanner(conversions);
        File cases = new File("Covid-Total-Case-days.csv");
        Scanner casesSc = new Scanner(cases);

        // creating mapping between the two e.g. Republic of Korea -> South Korea
        // null represents missing from cases dataset
        HashMap<String, String> conversionsMap = new HashMap<>();
        conversionSc.nextLine();
        while (conversionSc.hasNext()) {
            String currLine = conversionSc.nextLine();
            String[] vals = currLine.split("\\s*,\\s*");
            if (vals.length == 2) {
                conversionsMap.put(vals[0], vals[1]);
            } else {
                conversionsMap.put(vals[0], null);
            }
            
        }

        // Map of countries to cases e.g. South Korea -> [50, 500, 5000]
        // null represents missing data
        HashMap<String, String[]> casesMap = new HashMap<>();
        casesSc.nextLine();
        while (casesSc.hasNext()) {
            String currLine = casesSc.nextLine();
            String[] vals = currLine.split("\\s*,\\s*");
            if (vals.length == 4) {
                String[] numCases = {vals[1], vals[2], vals[3]};
                casesMap.put(vals[0], numCases);
            } else {
                casesMap.put(vals[0], null);
            }
        }


        conversionSc.close();
        casesSc.close();

        PrintWriter csvWriter = new PrintWriter("FinalCountryDatasetCombinedNoMissing.csv");


        boolean isFirst = true;
        while (countriesSc.hasNextLine()) {
            String currLine = countriesSc.nextLine();
            if (isFirst) {
                currLine = currLine + ",Day" + DAY1 + ",Day" + DAY2 + ",Day" + DAY3;
                csvWriter.println(currLine);
                isFirst = false;
            } else {
                String[] vals = currLine.split("\\s*,\\s*");
                String covidCountry = conversionsMap.get(vals[0]);

                if (covidCountry == null) {
                    // we don't have covid data for this country
                    continue;
                } else {
                    String[] numCases = casesMap.get(covidCountry);

                    if (numCases == null) {
                        // we don't have values for these days for the country
                        continue;
                    } else {
                        currLine = currLine + "," + numCases[0] + "," + numCases[1] + "," + numCases[2];
                        csvWriter.println(currLine);
                    }
                }
            }
        }
        countriesSc.close();
        csvWriter.close();
    }
}
