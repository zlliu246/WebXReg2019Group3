package ai.preferred.crawler.foursquare;

import ai.preferred.venom.Handler;
import ai.preferred.venom.Session;
import ai.preferred.venom.Worker;
import ai.preferred.venom.job.Scheduler;
import ai.preferred.venom.request.Request;
import ai.preferred.venom.response.VResponse;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class FoursquareHandler implements Handler {
    @Override
    public void handle(Request request, VResponse vResponse, Scheduler scheduler, Session session, Worker worker) {
        //Elements elements = vResponse.getJsoup().select("#results > ul > li > div > div.infoCol > div.venueBlock > div > div.venueName > h2 > a");
        Elements elements = vResponse.getJsoup().select("#results > ul > li> div > div.infoCol > div.venueBlock > div");
        for (Element element : elements) {
            String name = element.select("div.venueName > h2 > a").text();
            String category = element.select("div.venueMeta > div > div.venueData > span > span.categoryName").text();
            System.out.println(name + " : " + category);
        }
    }
}
