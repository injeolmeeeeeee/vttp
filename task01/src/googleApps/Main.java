package googleApps;
//did the code for task01 in the wrong folder, hence had to copy over the files/folders into the repository afterwards

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        //read the file, if no filename is provided, print error message and stop
        if (args.length <= 0) {
            System.out.println("Invalid file name. Please provide the CSV file name");
            System.exit(1);
        }

        //to save data from CSV into HashMap
        Map<String, CategoryStatistics> categoryStatsMap = new HashMap<>();

        //process file
        System.out.printf("Processing /s%n", args[0]);
    try {FileReader fr = new FileReader(args[0]);
        BufferedReader br = new BufferedReader(fr);
        //read header row, before reading the rest of the file,
        String header = br.readLine();

        String line;
        int totalLines = 0; //to see how many lines has been read

        while ((line = br.readLine()) != null) {
            totalLines++;
            
            String[] fields= line.split(",");

            if (fields.length < 3) { //invalid records in the file
                continue;
            }

            String appName = fields[0].trim().toUpperCase();
            String category = fields[1].trim().toUpperCase();
            double rating;

            //if rating is NaN, then value wont be parsed, hence catch the error
            try {
                rating = Double.parseDouble(fields[2]);
            } catch (NumberFormatException e) {
                CategoryStatistics.discardedCount();
                continue;
            }

            //to check if category already exists, add new catergory if it doesn't exist
            CategoryStatistics categoryStats =  categoryStatsMap.get(category);
            if (categoryStats == null) {
                categoryStats = new CategoryStatistics(); 
                categoryStatsMap.put(category, categoryStats);
            }
            categoryStats.appStats(appName, rating);
        }


        categoryStatsMap.forEach((category, stats) -> {
            System.out.println("Category: " + category);
            CategoryStatistics.printStats();
        });

        System.out.println("Total lines in file: " + totalLines);
        // System.out.println("Discarded count: " + );
        br.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
}