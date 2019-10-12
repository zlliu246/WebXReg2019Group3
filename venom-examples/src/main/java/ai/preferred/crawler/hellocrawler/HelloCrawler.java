package ai.preferred.crawler.hellocrawler;

import ai.preferred.venom.Crawler;
import ai.preferred.venom.request.Request;
import ai.preferred.venom.request.VRequest;

public class HelloCrawler {
    public static void main(String[] args) throws Exception {
        try (Crawler crawler = Crawler.buildDefault().start()) {//try with resource
            Request request = new VRequest("https://venom.preferred.ai/");
            crawler.getScheduler().add(request, new HelloHandler());
        }
    }

}
