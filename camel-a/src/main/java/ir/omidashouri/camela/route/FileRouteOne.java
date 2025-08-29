package ir.omidashouri.camela.route;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

import java.io.IOException;

//@Component
public class FileRouteOne extends RouteBuilder{

    @Override
    public void configure() throws Exception {

        onException(IOException.class)
                .handled(true)
                .log("IOException occurred due: ${exception.message}")
                .transform().simple("Error ${exception.message}")
                .to("mock:error");


        from("file:files/input")
                .log(LoggingLevel.INFO, "File: ${file:name}")
                .to("file:files/output");
    }
}
