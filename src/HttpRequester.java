/*

 * Yandex-Weather API project
 *
 * Version information
 *
 * 07.11.2025
 *
 * author: AlexandSko
 */


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**

 *
 HttpRequester Class.
 *
 *  The HttpRequester class provides the static method getResponseBody.
 *
 *  This method takes following parameters:
 *  1) uri of Yandex Weather service,
 *  2) X-Yandex-Weather-Key information (those parameters are passed as the request header):
 *    2.1) key name,
 *    2.2) key value;
 *
 *  creates the new HttpClient,
 *  builds the HTTP-request using input parameters (1, 2 (2.1, 2.2)),
 *  sends the HTTP-request to the Weather service
 *  and gets the HTTP-response from it.
 *
 *  Finally, this method returns the HTTP-response body as String value.
 *
 * @version
1.7 07 Nov 2025  * @author
Skotnikov Alexander  */
public class HttpRequester {

    public static String getResponseBody(String uri, String headerName, String headerValue)
            throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header(headerName, headerValue)
                .header("Content-Type", "application/json")
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}

