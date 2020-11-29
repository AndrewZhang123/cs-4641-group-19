import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class ParsingSplit {

    private static double TRAININGPERCENT = .8;
    private static String FILENAME = "FinalCountryDatasetCombinedNoMissing.csv";

    public static void main(String[] args) throws IOException {
        File countries = new File(FILENAME);
        Scanner sc = new Scanner(countries);
        PrintWriter trainWriter = new PrintWriter("training_"+ FILENAME);
        PrintWriter testWriter = new PrintWriter("test_"+ FILENAME);


        String firstLine = sc.nextLine(); // get first line for each file

        trainWriter.println(firstLine);
        testWriter.println(firstLine);

        ArrayList<String> points = new ArrayList<String>(); // the array list we will shuffle

        while (sc.hasNextLine()) {
            String currLine = sc.nextLine();
            points.add(currLine);
        }
        Collections.shuffle(points); // randomize order of points

        int cutoff = (int) (TRAININGPERCENT * points.size());  // calculate last datapoint that should be in training
        for (int i = 0; i < cutoff; i++) {  // put all those datapoints in training
            trainWriter.println(points.get(i));
        }

        for (int i = cutoff; i < points.size(); i++) { // put all points after cutoff in testing
            testWriter.println(points.get(i));
        }



        trainWriter.close();
        testWriter.close();
    }
}


/*



 */