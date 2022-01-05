package com.example;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Hello world!
 *
 */
public class App 
{
    static class Hello {
        public String message;
    }

    public static void main( String[] args )
    {
        try {
            String json_str = "{\"message\": \"Hello world.\"}";
            ObjectMapper mapper = new ObjectMapper();
            Hello hello = mapper.readValue(json_str, Hello.class);
            System.out.println( hello.message );    
        } catch(JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
