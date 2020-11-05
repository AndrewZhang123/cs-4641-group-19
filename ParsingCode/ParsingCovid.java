import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
public class ParsingCovid {
    static final int DAY1 = 5;
    static final int DAY2 = 25;
    static final int DAY3 = 50;
    static final int WINDOW = 4;
    public static void main(String[] args) throws IOException {
        File countries = new File("Covid-Total-Case.csv");
        Scanner sc = new Scanner(countries);
        PrintWriter csvWriter = new PrintWriter("Covid-Total-Case-days.csv");
        String firstLine = "Countries," + DAY1 + "," + DAY2+  "," + DAY3;
        csvWriter.println(firstLine);
        sc.nextLine(); // get rid of first line

        while (sc.hasNextLine()) {
            String currLine = sc.nextLine();
            String[] vals = currLine.split("\\s*,\\s*");
            int firstIndex = -1;

            // find first nonzero index
            System.out.println(vals[0]);
            for (int i = 1; i < vals.length; i++) {
                if (!vals[i].equals("") && !vals[i + 1].equals("")) {
                    firstIndex = i;
                    break;
                }
            }

            // find day 5, 25, 50
            int firstDay = firstIndex + DAY1 - 1;
            int secondDay = firstIndex + DAY2 - 1;
            int thirdDay = firstIndex + DAY3 - 1;

            int counter = 0;
            if(vals.length == 1) {
                csvWriter.println(vals[0]+",,,");
                continue;
            }
            // if that value is missing, find a nearby one
            if (vals.length != 215) {
                System.out.println(vals[0]);
                System.out.println(vals.length);
            }
            while((vals[firstDay].equals("") || vals[secondDay].equals("") || vals[thirdDay].equals("")) && counter < WINDOW) {
                if (vals[firstDay].equals("")) {
                    firstDay++;
                }
                if (vals[secondDay].equals("")) {
                    secondDay++;
                }
                if (vals[thirdDay].equals("")) {
                    thirdDay++;
                }
                counter++;
            }
            // generate the values to print
            String newAppend = vals[0] + ","+ vals[firstDay] + "," + vals[secondDay]+  "," + vals[thirdDay];
            csvWriter.println(newAppend);

        }
        csvWriter.close();

    }
}


/*



 */