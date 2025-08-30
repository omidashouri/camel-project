package ir.omidashouri.camelb.routes;

import ir.omidashouri.camelb.dto.CurrencyExchange;
import lombok.RequiredArgsConstructor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ActiveMqXmlReceiverRouter extends RouteBuilder {

    private final MyCurrencyExchangeProcessor myCurrencyExchangeProcessor;
    private final MyCurrencyExchangeTransformer myCurrencyExchangeTransformer;

    @Override
    public void configure() throws Exception {
/*        from("activemq:my-activemq-queue")
                .log("log:receive-message-from-activeMq");*/

        from("file:files/xml")
                .log("${body}")
                .to("activemq:my-activemq-xml-queue");


        from("activemq:my-activemq-xml-queue")
                .unmarshal()
                .jacksonXml(CurrencyExchange.class)
                .bean(myCurrencyExchangeProcessor,"processMessage")
                .bean(myCurrencyExchangeTransformer,"transformMessage")
                .log("log:receive message from activeMq ${body}");

    }
}
