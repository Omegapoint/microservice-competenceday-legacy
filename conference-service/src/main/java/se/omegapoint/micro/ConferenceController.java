package se.omegapoint.micro;

import org.apache.log4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ConferenceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConferenceController.class);

    @Autowired
    Tracer tracer;

    @RequestMapping("/conferences")
    public List<String> conferences() throws InterruptedException {
        LOGGER.error("I'm collecting some conferences for you!");

        String traceId = MDC.get("X-B3-TraceId").toString();
        Span span = tracer.getCurrentSpan();
        LOGGER.error("I huvudtråd " + span.toString());

        Thread thread = new Thread(new MyWorker(span, tracer));

        thread.start();
        thread.join();

        return Arrays.asList("Devoxx", "OpKoKo", "QCon", "JFokus");
    }

    public static class MyWorker implements Runnable {

        private final Tracer tracer;
        private final Span span;

        public MyWorker(Span span, Tracer tracer) {
            this.span = span;
            this.tracer = tracer;
        }

        @Override
        public void run() {
            tracer.continueSpan(span);

            LOGGER.error("I en annan tråd: " + tracer.getCurrentSpan());
        }
    }
}
