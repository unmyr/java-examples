package com.example;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    /**
     * Test for standard output
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        App.main(new String[]{});

        final String outputExpected = "Hello world!";
        final String outputActual = outputStreamCaptor.toString(StandardCharsets.UTF_8);
        Assertions.assertTrue(
            outputActual.contains(outputExpected),
            "expect: " + outputExpected + "\n" + "actual: " + outputActual
        );
    }
}
