package ir.omidashouri.camelb.routes;

import ir.omidashouri.camelb.dto.CurrencyExchange;
import lombok.RequiredArgsConstructor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ActiveMqJsonReceiverRouter extends RouteBuilder {

    private final MyCurrencyExchangeProcessor myCurrencyExchangeProcessor;
    private final MyCurrencyExchangeTransformer myCurrencyExchangeTransformer;

    @Override
    public void configure() throws Exception {
/*        from("activemq:my-activemq-queue")
                .log("log:receive-message-from-activeMq");*/

        from("file:files/json")
                .log("${body}")
                .to("activemq:my-activemq-queue");


        from("activemq:my-activemq-queue")
                .unmarshal().json(JsonLibrary.Jackson, CurrencyExchange.class)
                .bean(myCurrencyExchangeProcessor,"processMessage")
                .bean(myCurrencyExchangeTransformer,"transformMessage")
                .log("log:receive message from activeMq ${body}");

    }
}
