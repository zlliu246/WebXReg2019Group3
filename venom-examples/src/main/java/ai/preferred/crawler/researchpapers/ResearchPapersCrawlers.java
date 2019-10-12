package ai.preferred.crawler.researchpapers;

import ai.preferred.venom.Crawler;
import ai.preferred.venom.request.Request;
import ai.preferred.venom.request.VRequest;

public class ResearchPapersCrawlers {
    public static void main(String[] args) throws Exception {
        try (Crawler crawler = Crawler.buildDefault().start()){
            Request request= new VRequest("https://preferred.ai/publications/");
            crawler.getScheduler().add(request, new ResearchPapersHandler());
        }
    }
}
