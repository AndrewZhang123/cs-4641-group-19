import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
public class ParsingOneHot {
    public static void main(String[] args) throws IOException {
        File countries = new File("country_profile_variables_clean_split_columns.csv");
        Scanner sc = new Scanner(countries);
        PrintWriter csvWriter = new PrintWriter("country_profile_variables_one_hot_split.csv");

        String[] regionsArr = {"SouthernAsia","SouthernEurope","NorthernAfrica",
                "Polynesia","MiddleAfrica","Caribbean","SouthAmerica","WesternAsia","Oceania","WesternEurope",
                "EasternEurope","CentralAmerica","WesternAfrica","NorthernAmerica","SouthernAfrica","South-easternAsia",
                "EasternAfrica","NorthernEurope","EasternAsia","Micronesia","CentralAsia","Melanesia"};

        ArrayList<String> regions = new ArrayList<>(Arrays.asList(regionsArr));
        System.out.println(regions);
        boolean isFirst = true;
        while (sc.hasNextLine()) {
            String currLine = sc.nextLine();
            if (isFirst) {
                for (String currRegion : regionsArr) {
                    currLine = currLine + "," + currRegion;
                }
                csvWriter.println(currLine);
                isFirst = false;
            } else {
                int oneHotIndex = 0;
                String[] vals = currLine.split("\\s*,\\s*", 4);
                oneHotIndex = regions.indexOf(vals[2]);
                for (int i = 0; i < regions.size(); i++) {
                    if (i == oneHotIndex) {
                        currLine = currLine + ",1";
                    } else {
                        currLine = currLine +",0";
                    }
                }
                csvWriter.println(currLine);
            }
        }
        sc.close();
        csvWriter.close();

    }
}
