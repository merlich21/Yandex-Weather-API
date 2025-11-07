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
 *  This method takes weather for every day from the weather forecast using cycle FOR.
 *  Then it takes "temp_avg" parameter for every day and summarize them.
 *  Finally, it calculates the average value of the "temp_avg" for those days;
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
