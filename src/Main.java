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
    public static void main(String[] args) {

        String headerName = "X-Yandex-Weather-Key";
        String headerValue = "demo_yandex_weather_api_key_ca6d09349ba0";
        String uriForecast = "https://api.weather.yandex.ru/v2/forecast?lat=52.37125&lon=4.89388&limit=7";
        String jsonStringForecast = null;
        JsonObject jsonObjectForecast = null;

        try {
            jsonStringForecast = HttpRequester.getResponseBody(uriForecast, headerName, headerValue);
        } catch (Exception e) {
            System.out.println(Colorizer.ANSI_YELLOW + "ERROR! BAD (may be NULL) RESPONSE FROM THE WEATHER SERVICE!");
            return;
        }

        try {
            jsonObjectForecast = JsonParser.parseString(jsonStringForecast)
                    .getAsJsonObject();
        } catch (Exception e) {
            System.out.println(Colorizer.ANSI_YELLOW + "ERROR! BAD JSON-SYNTAX IN WEATHER SERVICE`S RESPONSE!");
            return;
        }

        System.out.println(Colorizer.ANSI_GREEN + "forecast: "
                + Colorizer.ANSI_BLUE + jsonObjectForecast);
        try {
            System.out.println(Colorizer.ANSI_GREEN + "forecast.fact.temp: "
                    + Colorizer.ANSI_BLUE
                    + jsonObjectForecast.getAsJsonObject("fact").get("temp"));
        } catch (Exception e) {
            System.out.println(Colorizer.ANSI_YELLOW
                    + "ERROR! BAD JSON-SYNTAX IN WEATHER SERVICE`S RESPONSE (or NULL RESPONSE)!");
            return;
        }
        try {
            System.out.println(Colorizer.ANSI_GREEN + "forecasts.parts.day.temp_avg: "
                    + Colorizer.ANSI_BLUE + TempAvgCounter.countTempAvg(jsonObjectForecast, 7));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {
            System.out.println(Colorizer.ANSI_YELLOW
                    + "ERROR! BAD (may be NULL) RESPONSE FROM THE WEATHER SERVICE");
        } catch (IndexOutOfBoundsException e) {
            System.out.println(Colorizer.ANSI_YELLOW
                    + "ERROR! daysNumber PARAMETER IS TOO BIG");
        }
    }
}
