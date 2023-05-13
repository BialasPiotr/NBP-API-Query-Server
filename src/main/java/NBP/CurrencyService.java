package NBP;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.stream.StreamSupport;

@Service
public class CurrencyService {

    private static final String NBP_API_BASE_URL = "http://api.nbp.pl/api/exchangerates/rates";

    public String getExchangeRate(String currency, String date) {
        RestTemplate restTemplate = new RestTemplate();
        String url = NBP_API_BASE_URL + "/a/" + currency + "/" + date;
        JsonNode response = restTemplate.getForObject(url, JsonNode.class);
        BigDecimal midRate = response.get("rates").get(0).get("mid").decimalValue();
        return String.format("The average exchange rate for %s on %s is: %s", currency, date, midRate);
    }

    public String getMinMax(String currency, int days) {
        RestTemplate restTemplate = new RestTemplate();
        String url = NBP_API_BASE_URL + "/a/" + currency + "/last/" + days;
        JsonNode response = restTemplate.getForObject(url, JsonNode.class);
        JsonNode rates = response.get("rates");

        BigDecimal minRate = StreamSupport.stream(rates.spliterator(), false)
                .map(rate -> rate.get("mid").decimalValue())
                .min(Comparator.naturalOrder())
                .orElse(BigDecimal.ZERO);

        BigDecimal maxRate = StreamSupport.stream(rates.spliterator(), false)
                .map(rate -> rate.get("mid").decimalValue())
                .max(Comparator.naturalOrder())
                .orElse(BigDecimal.ZERO);

        return String.format("The min and max average exchange rates for %s in the last %d days are: %s and %s", currency, days, minRate, maxRate);
    }

    public String getDifference(String currency, int days) {
        RestTemplate restTemplate = new RestTemplate();
        String url = NBP_API_BASE_URL + "/c/" + currency + "/last/" + days;
        JsonNode response = restTemplate.getForObject(url, JsonNode.class);
        JsonNode rates = response.get("rates");

        BigDecimal maxDifference = StreamSupport.stream(rates.spliterator(), false)
                .map(rate -> rate.get("ask").decimalValue().subtract(rate.get("bid").decimalValue()))
                .max(Comparator.naturalOrder())
                .orElse(BigDecimal.ZERO);

        return String.format("The major difference between the buy and ask rate for %s in the last %d days is: %s", currency, days, maxDifference);
    }
}
