package com.example;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        HttpClient client = HttpClientBuilder.create().build();
        try {

            HttpGet getRequest = new HttpGet(
                "https://fakerestapi.azurewebsites.net/api/v1/Authors"
            );

            // Set the API media type in http accept header
            getRequest.addHeader("Accept", "application/json");
            HttpResponse httpResponse = client.execute(getRequest);

            ObjectMapper mapper = new ObjectMapper();
            FakeRestApiAuthors[] authors = mapper.readValue(
                httpResponse.getEntity().getContent(),
                FakeRestApiAuthors[].class
            );

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
