package ai.preferred.crawler.Kickstarter;

import ai.preferred.venom.Crawler;
import ai.preferred.venom.request.Request;
import ai.preferred.venom.request.VRequest;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class PageCrawler {
    public static void main(String[] args) throws Exception {
        String csvFile = "data.csv";
        String splitter = "";

        List<List<String>> records = new ArrayList<>();
        int recordsNo = 0;
        try (Scanner scanner = new Scanner(new File(csvFile))) {
            while (scanner.hasNextLine()) {
                records.add(Collections.singletonList(scanner.nextLine()));
                recordsNo++;
            }
        }
        System.out.println(recordsNo+" urls found!");
        //String i = "https://www.kickstarter.com/projects/ecoflow/delta-the-new-standard-of-battery-powered-generator?ref=discovery_category";
        long start = System.currentTimeMillis();
        try (PrintWriter writer = new PrintWriter(new FileWriter("extracted_data.csv", true))) {
            //remvove below if appending to existing file
            writer.append("projectName,amountPledged,goal,backers,projectLove,category,country,rewardsNum,graphicsNum,paragraph");
            writer.append('\n');

            writer.close();
            System.out.println("done!");

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (Crawler crawler = Crawler.buildDefault().start()) {//try with resource
            //System.out.println("projectName , amountPledged , goal , backers , daysLeft , projectLove , category , country , rewardsNum , graphicsNum , paragraph");
            for (int i = 0 ; i < recordsNo ; i++) {
                Request request = new VRequest(records.get(i).get(0));
                crawler.getScheduler().add(request, new PageHandler());
            }
        }
        long end = System.currentTimeMillis();
        long duration = end - start;
        long hours = (duration / 1000) / 3600;
        long minutes = (duration / 1000) / 60;
        long seconds = (duration / 1000) % 60;
        System.out.format("%d Milliseconds = %d hours %d minutes and %d seconds.", duration, hours, minutes, seconds);
    }
}
