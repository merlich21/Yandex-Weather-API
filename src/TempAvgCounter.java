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

/**

 TempAvgCounter Class.
 *
 *  The TempAvgCounter class provides the static method countTempAvg.
 *
 *  This method takes following parameters:
 *  1) Weather forecast as JsonObject,
 *  2) number of days in weather forecast as int value.
 *
 *  This method takes weather for every day from the weather forecast using cycle FOR.
 *  Then it takes "temp_avg" parameter for every day and summarize them.
 *  Finally, it calculates the average value of the "temp_avg" for those days;
 *
 *  and returns this value as integer.
 *
 * @version
1.7 07 Nov 2025  * @author
Skotnikov Alexander  */
public class TempAvgCounter {

    public static int countTempAvg(JsonObject jsonObjectForecast, int daysNumber)
            throws NullPointerException, ArrayIndexOutOfBoundsException {
        int tempSum = 0;
        int tempAvg = 0;

        if (daysNumber <= 0) {
            System.out.println(Colorizer.ANSI_YELLOW +
                    "ERROR! daysNumber parameter must be a positive value ( daysNumber > 0 )!");
            return 34404;
        }
        for (int i = 0; i < daysNumber; i++) {
            JsonObject dayForecast = jsonObjectForecast.getAsJsonArray("forecasts")
                    .get(i)
                    .getAsJsonObject();

            tempSum += dayForecast.getAsJsonObject("parts")
                    .getAsJsonObject("day")
                    .get("temp_avg").getAsShort();
        }
        tempAvg = tempSum / daysNumber;

        return tempAvg;
    }
}
