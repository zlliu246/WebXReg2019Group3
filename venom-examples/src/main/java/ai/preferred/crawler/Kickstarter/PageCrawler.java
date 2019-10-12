package ai.preferred.crawler.kickstarter;

import ai.preferred.venom.Crawler;
import ai.preferred.venom.request.Request;
import ai.preferred.venom.request.VRequest;

public class PageCrawler {
    public static void main(String[] args) throws Exception {
        try (Crawler crawler = Crawler.buildDefault().start()) {//try with resource
            Request request = new VRequest("https://www.kickstarter.com/projects/ecoflow/delta-the-new-standard-of-battery-powered-generator?ref=discovery_category");
            crawler.getScheduler().add(request, new PageHandler());

        }
    }

}
