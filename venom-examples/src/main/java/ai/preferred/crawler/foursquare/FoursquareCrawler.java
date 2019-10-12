package ai.preferred.crawler.foursquare;

import ai.preferred.venom.Crawler;
import ai.preferred.venom.request.Request;
import ai.preferred.venom.request.VRequest;

public class FoursquareCrawler {
    public static void main(String[] args) throws Exception {
        try (Crawler crawler = Crawler.buildDefault().start()) {
                                                                                                                                                                                                                                                          Request request = new VRequest("https://foursquare.com/explore?cat=food&mode=url&near=Singapore%2C%20Singapore&nearGeoId=72057594039808188");
            crawler.getScheduler().add(request, new FoursquareHandler());
        }
    }
}
