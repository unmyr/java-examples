package com.example;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try (CloseableHttpClient client = HttpClients.createDefault()) {

            HttpGet getRequest = new HttpGet(
                "https://fakerestapi.azurewebsites.net/api/v1/Authors"
            );

            // Set the API media type in http accept header
            getRequest.addHeader("Accept", "application/json");
            try (CloseableHttpResponse httpResponse = client.execute(getRequest)) {
                ObjectMapper mapper = new ObjectMapper();
                FakeRestApiAuthors[] authors = mapper.readValue(
                    httpResponse.getEntity().getContent(),
                    FakeRestApiAuthors[].class
                );
    
                for (FakeRestApiAuthors author : authors) {
                    System.out.println(author.toString());
                }                    
            }
        } catch (java.net.MalformedURLException e) {
            e.printStackTrace();
            System.exit(1);
        } catch(java.io.IOException e) {
            e.printStackTrace();
        }
    }
}
