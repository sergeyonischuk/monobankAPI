import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        MonobankService service = new MonobankService();
        service.getCurrencyExchangeByCode(978);
    }
}
