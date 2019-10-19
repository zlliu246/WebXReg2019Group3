package ai.preferred.crawler.Kickstarter;

import ai.preferred.venom.Handler;
import ai.preferred.venom.Session;
import ai.preferred.venom.Worker;
import ai.preferred.venom.job.Scheduler;
import ai.preferred.venom.request.Request;
import ai.preferred.venom.response.VResponse;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PageHandler implements Handler {
    // total attributes: 12
    private static final Logger LOGGER = LoggerFactory.getLogger(PageHandler.class);

    @Override
    public void handle(Request request, VResponse vResponse, Scheduler scheduler, Session session, Worker worker) {
        String projectName = vResponse.getJsoup().select("#react-project-header > div > div > div.grid-row.pt7-lg.mt3.mt0-lg.mb6-lg.order-2-md.order-1-lg > div > div.grid-row.hide-md.flex.flex-column.flex-row-md.relative > div:nth-child(1) > h2").text();
        projectName = projectName.replaceAll("\\p{Punct}","");

        String amountPledged = vResponse.getJsoup().select("#react-project-header > div > div > div.grid-row.grid-row.mb5-lg.mb0-md.order-1-md.order-2-lg > div.grid-col-12.grid-col-4-md.hide.block-lg > div.flex.flex-column-lg.mb4.mb5-sm > div:nth-child(1) > div.flex.items-center > span > span").text();
        amountPledged = amountPledged.replace(",", "");

        String goal = vResponse.getJsoup().select("#react-project-header > div > div > div:nth-child(3) > div > div.flex.flex-column-lg.mb4.mb5-sm > div:nth-child(1) > span > span.inline-block-sm.hide > span").text();
        goal = goal.replace(",", "");

        String backers = vResponse.getJsoup().select("#react-project-header > div > div > div:nth-child(3) > div > div.flex.flex-column-lg.mb4.mb5-sm > div.ml5.ml0-lg.mb4-lg > div > span").text();

        String daysLeft = vResponse.getJsoup().select("#react-project-header > div > div > div:nth-child(3) > div > div.flex.flex-column-lg.mb4.mb5-sm > div:nth-child(3) > div > div > span").text();
        //failed^

        String projectLove;
        String category;
        String prompt1 = vResponse.getJsoup().select("#react-project-header > div > div > div.order-1-md.hide-lg.border-top.border-bottom.border-top-none-md.border-none-lg.nested-full-width-xs.nested-full-width-sm.nested-full-width-md.mb4.mb5-sm.mb0-md > div > div > div > a:nth-child(1) > span > span").text();
        if (prompt1 == "Project We Love") {
            projectLove = "Yes";
            category = vResponse.getJsoup().select("#react-project-header > div > div > div.order-1-md.hide-lg.border-top.border-bottom.border-top-none-md.border-none-lg.nested-full-width-xs.nested-full-width-sm.nested-full-width-md.mb4.mb5-sm.mb0-md > div > div > div > a:nth-child(2) > span > span").text();
        } else {
            projectLove = "No";
            category = prompt1;
        }

        String country = vResponse.getJsoup().select("#react-project-header > div > div > div.order-1-md.hide-lg.border-top.border-bottom.border-top-none-md.border-none-lg.nested-full-width-xs.nested-full-width-sm.nested-full-width-md.mb4.mb5-sm.mb0-md > div > div > div > a:nth-child(2)").text();
        if (country.contains(",")) {
            country = vResponse.getJsoup().select("#react-project-header > div > div > div.order-1-md.hide-lg.border-top.border-bottom.border-top-none-md.border-none-lg.nested-full-width-xs.nested-full-width-sm.nested-full-width-md.mb4.mb5-sm.mb0-md > div > div > div > a:nth-child(2)").text();
        }
        country = country.replace(",", ";");

        Elements noOfRewards = vResponse.getJsoup().select("#content-wrap > div.NS_projects__content > section.js-project-content.js-project-description-content.project-content > div > div > div > div.col.col-4 > div > div.mobile-hide > div > ol > li > div.pledge__info");
        int rewardsNum = 0;
        for (Element element : noOfRewards) {
            String noOfReward = element.text();
            rewardsNum++;
        }

        Elements noOfGraphics = vResponse.getJsoup().select("#content-wrap > div.NS_projects__content > section.js-project-content.js-project-description-content.project-content > div > div > div > div.col.col-8.description-container > div > div > div.rte__content.js-full-description.responsive-media > div > figure > img");
        int graphicsNum = 0;
        for (Element element : noOfGraphics) {
            String noOfGraphic = element.text();
            graphicsNum++;
        }

        String paragraph = vResponse.getJsoup().select("#content-wrap > div.NS_projects__content > section.js-project-content.js-project-description-content.project-content > div > div > div > div.col.col-8.description-container > div > div > div.rte__content.js-full-description.responsive-media > p").text();
        // remove punctuation
        paragraph = paragraph.replaceAll("\\p{Punct}","");

        if (isNullOrEmpty(projectName)) {
            try (PrintWriter writer = new PrintWriter(new FileWriter("extracted_data.csv", true))) {

                writer.append(projectName+","+amountPledged+","+goal+","+backers+","+projectLove+","+category+","+country+","+rewardsNum+","+graphicsNum+","+paragraph);
                writer.append('\n');

                writer.close();
                LOGGER.info(projectName+" - done!");

            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
            //System.out.println("-> "+graphicsNum);
            //System.out.println(projectName+" , "+amountPledged+" , "+goal+" , "+backers+" , "+daysLeft+" , "+projectLove+" , "+category+" , "+country+" , "+rewardsNum+" , "+graphicsNum+" , "+paragraph);
        }
    }

    public static boolean isNullOrEmpty(String str) {
        if(str != null && !str.trim().isEmpty())
            return true;
        return false;
    }
}
