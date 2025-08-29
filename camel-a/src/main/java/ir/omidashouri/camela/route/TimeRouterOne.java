package ir.omidashouri.camela.route;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.function.Supplier;

//@Component
@RequiredArgsConstructor
public class TimeRouterOne extends RouteBuilder {

    private final GetCurrentTimeBean getCurrentTimeBean;
    private final GetCurrentTimeBeanSupplier getCurrentTimeBeanSupplier;
    private final ProcessLogMessage processLogMessage;
    private final ProcessorLog processorLog;

    @Override
    public void configure() throws Exception {
        //timer
        //transformation
        //log

        from("timer:foo?period=2000")
                .log("log 1-> ${body}")
                .transform().constant("my first timer")
                .log("log 2-> ${body}")
                .bean(getCurrentTimeBean, "getCurrentTime")
                .bean(getCurrentTimeBeanSupplier, "getCurrentTime")
                .log("log 3-> ${body}")
                .log("log 4-> ${bean:getLogCurrentTimeBean?method=getCurrentTime}")
                .bean(getCurrentTimeBean, "getCurrentTime")
                .bean(processLogMessage, "getMessage")
                .process(processorLog)
        .to("log:first-timer");

    }
}

@Component
class GetCurrentTimeBean {
    public String getCurrentTime() {
        return "current time is: "+LocalDateTime.now();
    }
}

@Component
class GetCurrentTimeBeanSupplier {
    public String getCurrentTime() {
         Supplier<String> getCurrentTime = () -> "supplier time is: " + LocalDateTime.now();
         return getCurrentTime.get();
    }
}

@Component
@Slf4j
class ProcessLogMessage {
    public void getMessage(String message) {
      log.info("process message: {}",message);
    }
}

@Component
@Slf4j
class ProcessorLog implements Processor{

    @Override
    public void process(Exchange exchange) throws Exception {
        log.info("processor exchange: {}",exchange.getMessage().getBody().toString());
    }
}

@Component
class GetLogCurrentTimeBean {
    public String getCurrentTime() {
        return " "+LocalDateTime.now();
    }
}