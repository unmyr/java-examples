package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FruitsMenuControllerTest {
  @Autowired
	private MockMvc mockMvc;

  @Test
	public void findAll() throws Exception {
		this.mockMvc.perform(
      MockMvcRequestBuilders.post(
        "/api/fruits/Apple"
      ).contentType(
        MediaType.APPLICATION_JSON
      ).accept(
        MediaType.APPLICATION_JSON
      ).content(
        "{\"price\":100}]"
      )
    ).andDo(print()).andExpect(status().isOk()).andExpect(
      jsonPath("$.message").value("OK")
    );

    this.mockMvc.perform(
      MockMvcRequestBuilders.post(
        "/api/fruits/Banana"
      ).contentType(
        MediaType.APPLICATION_JSON
      ).accept(
        MediaType.APPLICATION_JSON
      ).content(
        "{\"price\":120}]"
      )
    ).andDo(print()).andExpect(status().isOk()).andExpect(
      jsonPath("$.message").value("OK")
    );

    this.mockMvc.perform(
      MockMvcRequestBuilders.post(
        "/api/fruits/Orange"
      ).contentType(
        MediaType.APPLICATION_JSON
      ).accept(
        MediaType.APPLICATION_JSON
      ).content(
        "{\"price\":110}]"
      )
    ).andDo(print()).andExpect(status().isOk()).andExpect(
      jsonPath("$.message").value("OK")
    );

    this.mockMvc.perform(
      MockMvcRequestBuilders.get(
        "/api/fruits"
      ).contentType(
        MediaType.APPLICATION_JSON
      ).accept(
        MediaType.APPLICATION_JSON
      )
    ).andDo(print()).andExpect(status().isOk()).andExpect(
      jsonPath("$.length()").value(3)
    ).andExpect(
      jsonPath("$[*].name").value(containsInAnyOrder("Apple", "Banana", "Orange"))
    ).andExpect(
      jsonPath("$[*].price").value(containsInAnyOrder(100, 120, 110))
    ).andExpect(
      jsonPath("$.[?(@.name == \"Apple\" && @.price == 100)]").exists()
    ).andExpect(
      jsonPath("$.[?(@.name == \"Banana\" && @.price == 120)]").exists()
    ).andExpect(
      jsonPath("$.[?(@.name == \"Orange\" && @.price == 110)]").exists()
    );
  }

  @Test
	public void addAndUpdate() throws Exception {
		this.mockMvc.perform(
      MockMvcRequestBuilders.post(
        "/api/fruits/Peach"
      ).contentType(
        MediaType.APPLICATION_JSON
      ).accept(
        MediaType.APPLICATION_JSON
      ).content(
        "{\"price\":99}"
      )
    ).andDo(print()).andExpect(status().isOk()).andExpect(
      jsonPath("$.message").value("OK")
    );

    this.mockMvc.perform(
      MockMvcRequestBuilders.put(
        "/api/fruits/Peach"
      ).contentType(
        MediaType.APPLICATION_JSON
      ).accept(
        MediaType.APPLICATION_JSON
      ).content(
        "{\"price\":101}"
      )
    ).andDo(print()).andExpect(status().isOk()).andExpect(
      jsonPath("$.message").value("OK")
    );

    this.mockMvc.perform(
      MockMvcRequestBuilders.get(
        "/api/fruits/Peach"
      ).contentType(
        MediaType.APPLICATION_JSON
      ).accept(
        MediaType.APPLICATION_JSON
      )
    ).andDo(print()).andExpect(status().isOk()).andExpect(
      jsonPath("$.price").value(101)
    ).andExpect(
      jsonPath("$.name").value("Peach")
    );
  }

  @Test
	public void addAndDelete() throws Exception {
		this.mockMvc.perform(
      MockMvcRequestBuilders.post(
        "/api/fruits/Durian"
      ).contentType(
        MediaType.APPLICATION_JSON
      ).accept(
        MediaType.APPLICATION_JSON
      ).content(
        "{\"price\":500}"
      )
    ).andDo(print()).andExpect(status().isOk()).andExpect(
      jsonPath("$.message").value("OK")
    );

    this.mockMvc.perform(
      MockMvcRequestBuilders.delete(
        "/api/fruits/Durian"
      ).contentType(
        MediaType.APPLICATION_JSON
      ).accept(
        MediaType.APPLICATION_JSON
      )
    ).andDo(print()).andExpect(status().isOk()).andExpect(
      jsonPath("$.message").value("OK")
    );
  }
}
