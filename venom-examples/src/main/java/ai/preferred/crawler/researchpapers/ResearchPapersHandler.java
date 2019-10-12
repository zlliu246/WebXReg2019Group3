package ai.preferred.crawler.researchpapers;

import ai.preferred.venom.Handler;
import ai.preferred.venom.Session;
import ai.preferred.venom.Worker;
import ai.preferred.venom.job.Scheduler;
import ai.preferred.venom.request.Request;
import ai.preferred.venom.response.VResponse;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ResearchPapersHandler implements Handler {
    @Override
    public void handle(Request request, VResponse vResponse, Scheduler scheduler, Session session, Worker worker) {
        Elements elements = (Elements) vResponse.getJsoup().select("#page > div > div > div > div.content > div > article > div > ul > li > a");
        for (Element element : elements) {
            String title = element.text();
            System.out.println(title);
        }

    }
}
