package com.example;
import java.io.InputStream;

import org.apache.hc.client5.http.ClientProtocolException;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.message.StatusLine;

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

            FakeRestApiAuthors[] authors = client.execute(getRequest, response -> {
                if (response.getCode() >= 300) {
                    throw new ClientProtocolException(new StatusLine(response).toString());
                }

                final HttpEntity responseEntity = response.getEntity();
                if (responseEntity == null) {
                    return null;
                }

                try (InputStream inputStream = responseEntity.getContent()) {
                    ObjectMapper mapper = new ObjectMapper();
                    return mapper.readValue(
                        inputStream,
                        FakeRestApiAuthors[].class
                    );
                }
            });
            client.close();

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
