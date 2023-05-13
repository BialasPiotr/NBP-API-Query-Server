package NBP;

import NBP.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @GetMapping("/exchange-rate/{currency}/{date}")
    public String getExchangeRate(@PathVariable String currency, @PathVariable String date) {
        return currencyService.getExchangeRate(currency, date);
    }

    @GetMapping("/exchange-rate/{currency}/min-max")
    public String getMinMax(@PathVariable String currency, @RequestParam("days") int days) {
        return currencyService.getMinMax(currency, days);
    }

    @GetMapping("/exchange-rate/{currency}/difference")
    public String getDifference(@PathVariable String currency, @RequestParam("days") int days) {
        return currencyService.getDifference(currency, days);
    }
}
