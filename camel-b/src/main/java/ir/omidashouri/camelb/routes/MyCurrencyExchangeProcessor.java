package ir.omidashouri.camelb.routes;

import ir.omidashouri.camelb.dto.CurrencyExchange;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MyCurrencyExchangeProcessor {
    public void processMessage(final CurrencyExchange currencyExchange){
        log.info("do some processing with currency exchange: {}", currencyExchange.getConversionMultiple());
    }
}
