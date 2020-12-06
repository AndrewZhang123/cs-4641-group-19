import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
public class ParsingProportions {
    static final int DAY1 = 5;
    static final int DAY2 = 25;
    static final int DAY3 = 50;
    static final int WINDOW = 4;
    public static void main(String[] args) throws IOException {
        gen("test_FinalCountryDatasetCombinedNoMissing");
        gen("training_FinalCountryDatasetCombinedNoMissing");
    }

    public static void gen(String name) throws IOException {
        File countries = new File(name + ".csv");
        Scanner sc = new Scanner(countries);
        PrintWriter csvWriter = new PrintWriter(name + "_proportions.csv");
        String firstLine = sc.nextLine();
        csvWriter.println(firstLine);
        while (sc.hasNextLine()) {
            String currLine = sc.nextLine();
            String[] vals = currLine.split("\\s*,\\s*");
            double fiveDays = Double.parseDouble(vals[vals.length - 3]);
            double twentyFiveDays = Double.parseDouble(vals[vals.length - 2]);
            double fiftyDays = Double.parseDouble(vals[vals.length - 1]);
            double pop = Double.parseDouble(vals[2]);
            String newLine = "";
            for(int i = 0; i < vals.length - 3; i++) {
                newLine = newLine + vals[i] + ",";
            }
            newLine = newLine + ((fiveDays / pop) * 1000) + ",";
            newLine = newLine + ((twentyFiveDays / pop) *  1000) + ",";
            newLine = newLine + ((fiftyDays / pop) * 1000);
            csvWriter.println(newLine);
        }   
        csvWriter.close();
    }
}


/*



 */