package com.example;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        URL url;
        try {
            // Create a neat value object to hold the URL
            url = new URL("https://fakerestapi.azurewebsites.net/api/v1/Authors");

            // Open a connection(?) on the URL(??) and cast the response(???)
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Now it's "open", we can set the request method, headers etc.
            connection.setRequestProperty("Accept", "application/json");

            // This line makes the request
            InputStream responseStream = connection.getInputStream();

            // Manually converting the response body InputStream to APOD using Jackson
            ObjectMapper mapper = new ObjectMapper();
            FakeRestApiAuthors[] authors = mapper.readValue(
                responseStream, FakeRestApiAuthors[].class
            );

            // Finally we have the response
            for (FakeRestApiAuthors author : authors) {
                System.out.println(author.toString());
            }
        } catch (java.net.MalformedURLException e) {
            e.printStackTrace();
            System.exit(1);
        } catch(java.io.IOException e) {
            e.printStackTrace();
        }
    }
}
