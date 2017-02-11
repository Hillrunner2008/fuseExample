package com.rh.consulting.twitterroute;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spi.ComponentResolver;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicyOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author dcnorris
 */
@Component(immediate = true, service = org.apache.camel.RoutesBuilder.class)
public class TwitterRoute extends RouteBuilder {

    private static final Logger LOG = LoggerFactory.getLogger(TwitterRoute.class);

    @Override
    public void configure() throws Exception {
        from("twitter://timeline/home?type=polling&delay=120000&consumerKey=...")
                .process((Exchange exchange) -> {
                    exchange.getIn().getBody();
                })
                .log("${body}");
    }

    @Reference(cardinality = ReferenceCardinality.MANDATORY,
            policyOption = ReferencePolicyOption.GREEDY,
            target = "(component=twitter)")
    public void setTwitterComponent(ComponentResolver componentResolver) {
        LOG.info("twitter component resolved");
    }

}
