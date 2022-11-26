package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FruitsMenuControllerTest {
  @Autowired
	private MockMvc mockMvc;

  // @Test
	// public void findAll() throws Exception {
	// 	this.mockMvc.perform(
  //     get("/api/fruits").accept(MediaType.APPLICATION_JSON)
  //   ).andDo(print()).andExpect(status().isOk()).andExpect(
  //     jsonPath("$[0].name").value("Apple")
  //   );
  // }
  @Test
	public void add() throws Exception {
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
        "{\"price\":99}]"
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
        "{\"price\":101}]"
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
        "{\"price\":500}]"
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
