package googleApps;

public class CategoryStatistics {
    private static int count;
    private static int discardedCount;
    private static String categoryName;
    private static double totalRating;
    private static String highestAppName;
    private static double highestAppRating;
    private static String lowestAppName;
    private static double lowestAppRating;

    //to get the lowest/highest rating app, go through the records and get the lowest number by comparing each value/record
    public void appStats(String appName,double rating) {
        count++;
        if (rating < lowestAppRating) {
            lowestAppRating = rating;
            lowestAppName = appName;
        } else if (rating > lowestAppRating) {
            highestAppRating = rating;
            highestAppName = appName;
        }
        totalRating += rating;
    }

    public static void discardedCount() {
        discardedCount++;
    }

    public static void printStats() {
        System.out.println("Category: " + categoryName);
        System.out.println("Highest: " + highestAppName + highestAppRating);
        System.out.println("Lowest: " + lowestAppName + lowestAppRating);
        System.out.println("Average: " + (totalRating/count));
        System.out.println("Count: " + count);
        System.out.println("Discarded: " + discardedCount);

    }
}
