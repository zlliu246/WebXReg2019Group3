package ai.preferred.crawler.hellocrawler;

import ai.preferred.venom.Handler;
import ai.preferred.venom.Session;
import ai.preferred.venom.Worker;
import ai.preferred.venom.job.Scheduler;
import ai.preferred.venom.request.Request;
import ai.preferred.venom.response.VResponse;

public class HelloHandler implements Handler {
    @Override
    public void handle(Request request, VResponse vResponse, Scheduler scheduler, Session session, Worker worker) {
        String aboutText = vResponse
                .getJsoup()
                .select("#about > div > div")
                .text();
        System.out.println(aboutText);
    }
}
