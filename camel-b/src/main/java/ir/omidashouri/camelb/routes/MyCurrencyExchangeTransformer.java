package ir.omidashouri.camelb.routes;

import ir.omidashouri.camelb.dto.CurrencyExchange;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Slf4j
public class MyCurrencyExchangeTransformer {

    public CurrencyExchange transformMessage(final CurrencyExchange currencyExchange){
        currencyExchange.setConversionMultiple(currencyExchange.getConversionMultiple().multiply(BigDecimal.TEN));
        log.info("do some transformation with currency exchange multiple 10: {}",currencyExchange.getConversionMultiple());
        return currencyExchange;
    }
}
