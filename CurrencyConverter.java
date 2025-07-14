import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONObject;

public class CurrencyConverter {

    private static final String API_URL = "https://api.exchangerate-api.com/v4/latest/";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("🌐 Currency Converter");

        System.out.print("Enter base currency (e.g., USD, INR): ");
        String baseCurrency = scanner.nextLine().toUpperCase();

        System.out.print("Enter target currency (e.g., EUR, GBP): ");
        String targetCurrency = scanner.nextLine().toUpperCase();

        System.out.print("Enter amount to convert: ");
        double amount = scanner.nextDouble();

        try {
            double rate = getExchangeRate(baseCurrency, targetCurrency);
            if (rate == -1) {
                System.out.println("❌ Failed to fetch exchange rate.");
                return;
            }

            double convertedAmount = amount * rate;
            System.out.printf("✅ %.2f %s = %.2f %s%n", amount, baseCurrency, convertedAmount, targetCurrency);
        } catch (Exception e) {
            System.out.println("❌ Error occurred: " + e.getMessage());
        }

        scanner.close();
    }

    public static double getExchangeRate(String base, String target) {
        try {
            URL url = new URL(API_URL + base);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Read response
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = in.readLine()) != null) {
                response.append(line);
            }

            in.close();

            // Parse JSON
            JSONObject json = new JSONObject(response.toString());
            JSONObject rates = json.getJSONObject("rates");

            return rates.getDouble(target);

        } catch (Exception e) {
            return -1;
        }
    }
}
