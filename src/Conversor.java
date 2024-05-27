import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Conversor {
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/692118a4077d45cae681318d/latest/"; // URL de ejemplo

    // Método para obtener las tasas de cambio basado en la moneda
    public static JsonObject getConvertidor(String fromCurrency) throws IOException, InterruptedException {
        String url = API_URL + fromCurrency; //fromCurrency es la moneda que se solicita en el menú
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url)) //url porque si colocas el nombre de la url api el resultado da error
                .build();

        // Enviar la solicitud y obtener la respuesta
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        // Parsear la respuesta JSON y devolverla como un objeto JsonObject
        return JsonParser.parseString(response.body()).getAsJsonObject();
    }

    // Método para obtener la tasa de cambio entre dos monedas
    public static double getConvertidor(String fromCurrency,String currencyCode) throws IOException, InterruptedException {
        // Obtener todas las tasas de cambio
        JsonObject convertidor = getConvertidor(fromCurrency);
        // Obtener el objeto de tasas dentro del JSON nota: "conversion_rates es la moneda"
        JsonObject conversion_rates = convertidor.getAsJsonObject("conversion_rates");
        // Devolver la tasa de cambio específica para la moneda solicitada
        return conversion_rates.get(currencyCode).getAsDouble();
    }
}
