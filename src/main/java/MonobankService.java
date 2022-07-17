import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Currency;
import java.util.List;
import java.util.Optional;

public class MonobankService {
    private static final HttpClient CLIENT =HttpClient.newHttpClient();
    private static final Gson GSON = new Gson();
    private static final String MONOLINK = "https://api.monobank.ua/bank/currency";

    public List<CurrencyExchange> getAllCurrencyExchange() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(MONOLINK))
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return GSON.fromJson(response.body(), new TypeToken<List<CurrencyExchange>>() {}.getType());
    }

    public CurrencyExchange getCurrencyExchangeByCode(int code) throws IOException, InterruptedException {
        List<CurrencyExchange> list = getAllCurrencyExchange();
        for (CurrencyExchange currency : list) {
            if (currency.getCurrencyCodeA() == code) {
                System.out.println(currency.toString());
                return currency;
            }
        }
        throw new IllegalArgumentException("Code not found");
    }
}
