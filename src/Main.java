/*

 * Yandex-Weather API project
 *
 * Version information
 *
 * 07.11.2025
 *
 * author: AlexandSko
 */


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;

/**

 Main Class.
 *
 *  The Main class provides the method main.
 *
 *  In main method we are setting the variables values (uriForecast, headerName, headerValue)
 *  and passing them to the HttpRequester.getResponseBody method which
 *  returns the body of HTTP-response from Yandex-Weather service in String(json-string) format.
 *
 *  Then we are transforming this String(json-string) to the JsonObject format using external library GSON.
 *
 *  Finally, we are printing some data we need from Json to the console.
 *
 * @version
1.7 07 Nov 2025  * @author
Skotnikov Alexander  */
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        String headerName = "X-Yandex-Weather-Key";
        String headerValue = "demo_yandex_weather_api_key_ca6d09349ba0";
        String uriForecast = "https://api.weather.yandex.ru/v2/forecast?lat=52.37125&lon=4.89388&limit=7";

        String jsonStringForecast = HttpRequester.getResponseBody(uriForecast, headerName, headerValue);

        JsonObject jsonObjectForecast = JsonParser.parseString(jsonStringForecast)
                .getAsJsonObject();

        System.out.println("forecast: " + jsonObjectForecast);
        System.out.println("forecast.fact.temp: " + jsonObjectForecast.getAsJsonObject("fact").get("temp"));
        System.out.println("forecasts.parts.day.temp_avg: " + TempAvgCounter.countTempAvg(jsonObjectForecast, 7));
    }
}
