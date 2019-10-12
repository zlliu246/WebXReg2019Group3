package ai.preferred.crawler.kickstarter;

import ai.preferred.venom.Handler;
import ai.preferred.venom.Session;
import ai.preferred.venom.Worker;
import ai.preferred.venom.job.Scheduler;
import ai.preferred.venom.request.Request;
import ai.preferred.venom.response.VResponse;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class PageHandler implements Handler {
    @Override
    public void handle(Request request, VResponse vResponse, Scheduler scheduler, Session session, Worker worker) {
        String projectName = vResponse.getJsoup().select("#react-project-header > div > div > div.grid-row.pt7-lg.mt3.mt0-lg.mb6-lg.order-2-md.order-1-lg > div > div.grid-row.hide-md.flex.flex-column.flex-row-md.relative > div:nth-child(1) > h2").text();
        String amountPledged = vResponse.getJsoup().select("#react-project-header > div > div > div.grid-row.grid-row.mb5-lg.mb0-md.order-1-md.order-2-lg > div.grid-col-12.grid-col-4-md.hide.block-lg > div.flex.flex-column-lg.mb4.mb5-sm > div:nth-child(1) > div.flex.items-center > span > span").text();
        String goal = vResponse.getJsoup().select("#react-project-header > div > div > div:nth-child(3) > div > div.flex.flex-column-lg.mb4.mb5-sm > div:nth-child(1) > span > span.inline-block-sm.hide > span").text();
        String backers = vResponse.getJsoup().select("#react-project-header > div > div > div:nth-child(3) > div > div.flex.flex-column-lg.mb4.mb5-sm > div.ml5.ml0-lg.mb4-lg > div > span").text();
        String daysLeft = vResponse.getJsoup().select("#react-project-header > div > div > div:nth-child(3) > div > div.flex.flex-column-lg.mb4.mb5-sm > div:nth-child(3) > div > div > span.block.type-16.type-28-md.bold.dark-grey-500").text();
        String projectLove;
        try {
             projectLove = vResponse.getJsoup().select("#react-project-header > div > div > div.order-1-md.hide-lg.border-top.border-bottom.border-top-none-md.border-none-lg.nested-full-width-xs.nested-full-width-sm.nested-full-width-md.mb4.mb5-sm.mb0-md > div > div > div > a:nth-child(1) > span > span").text();
             projectLove = "1";
        } catch (Exception e) {
            projectLove = "0";
        }
        String category = vResponse.getJsoup().select("#react-project-header > div > div > div.order-1-md.hide-lg.border-top.border-bottom.border-top-none-md.border-none-lg.nested-full-width-xs.nested-full-width-sm.nested-full-width-md.mb4.mb5-sm.mb0-md > div > div > div > a:nth-child(2) > span > span").text();
        String country = vResponse.getJsoup().select("#react-project-header > div > div > div.order-1-md.hide-lg.border-top.border-bottom.border-top-none-md.border-none-lg.nested-full-width-xs.nested-full-width-sm.nested-full-width-md.mb4.mb5-sm.mb0-md > div > div > div > a:nth-child(3)").text();
        Elements noOfRewards = vResponse.getJsoup().select("#content-wrap > div.NS_projects__content > section.js-project-content.js-project-description-content.project-content > div > div > div > div.col.col-4 > div > div.mobile-hide > div > ol > li > div.pledge__info");
        int i = 0;
        for (Element element : noOfRewards) {
            String noOfReward = element.text();
            i++;
        }
        Elements noOfGraphics = vResponse.getJsoup().select("#content-wrap > div.NS_projects__content > section.js-project-content.js-project-description-content.project-content > div > div > div > div.col.col-8.description-container > div > div > div.rte__content.js-full-description.responsive-media > div > figure > img");
        int k = 0;
        for (Element element : noOfGraphics) {
            String noOfGraphic = element.text();
            k++;
        }
        String paragraph = vResponse.getJsoup().select("#content-wrap > div.NS_projects__content > section.js-project-content.js-project-description-content.project-content > div > div > div > div.col.col-8.description-container > div > div > div.rte__content.js-full-description.responsive-media > p").text();
        System.out.println(projectName);
    }
}
