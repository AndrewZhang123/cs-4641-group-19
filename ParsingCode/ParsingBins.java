import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
public class ParsingBins {
    static final double bin1 = 20;
    static final double bin2 = 100;
    static final double bin3 = 200;

    public static void main(String[] args) throws IOException {
        gen("test_FinalCountryDatasetCombinedNoMissing_proportions");
        gen("training_FinalCountryDatasetCombinedNoMissing_proportions");
    }

    public static void gen(String name) throws IOException {
        File countries = new File(name + ".csv");
        Scanner sc = new Scanner(countries);
        PrintWriter csvWriter = new PrintWriter(name + "_bins.csv");
        String firstLine = sc.nextLine();
        csvWriter.println(firstLine + ",bin");
        while (sc.hasNextLine()) {
            String currLine = sc.nextLine();
            String[] vals = currLine.split("\\s*,\\s*");
            double fiveDays = Double.parseDouble(vals[vals.length - 3]);
            int bin = 1;
            if (fiveDays <= bin1) {
                bin = 1;
            } else if (fiveDays <= bin2) {
                bin = 2;
            } else if (fiveDays <= bin3) {
                bin = 3;
            } else {
                bin = 4;
            }
            currLine = currLine + "," + bin;
            csvWriter.println(currLine);
        }   
        csvWriter.close();
    }
}


/*



 */