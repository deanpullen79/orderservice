package org.amex;

import org.amex.models.offers.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class OrderAppConfiguration {


    @Bean
    @Profile("offers")
    OfferStrategy appleBogofOffer() {
        return new AppleBogofOfferStrategy();
    }

    @Bean
    @Profile("offers")
    OfferStrategy orangeThreeForTwoOffer() {
        return new Orange3For2OfferStrategy();
    }

    @Bean
    @Profile("no-offers")
    OfferStrategy appleNoOffer() {
        return new AppleNoOfferStrategy();
    }

    @Bean
    @Profile("no-offers")
    OfferStrategy orangeNoOffer() {
        return new OrangeNoOfferStrategy();
    }
}
