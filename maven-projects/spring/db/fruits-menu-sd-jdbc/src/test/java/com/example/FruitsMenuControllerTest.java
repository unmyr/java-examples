package com.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.jayway.jsonpath.JsonPath;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql({"/schema.sql", "/test-data.sql"})
public class FruitsMenuControllerTest {
  @Autowired
  private JdbcTemplate jdbcTemplate;
  @Autowired
	private MockMvc mockMvc;

  @AfterEach
  public void tearDown() {
    JdbcTestUtils.deleteFromTables(jdbcTemplate, "fruits_menu");
  }

  @Test
	public void getOneNotFound() throws Exception {
    this.mockMvc.perform(
      MockMvcRequestBuilders.get(
        "/api/fruits?name=NotFound"
      ).accept(
        MediaType.APPLICATION_JSON
      )
    ).andDo(print()).andExpect(
      status().isNotFound()
    );
  }

  @Test
	public void putOneByNameNotFound() throws Exception {
    this.mockMvc.perform(
      MockMvcRequestBuilders.put(
        "/api/fruits?name=NotFound"
      ).contentType(
        MediaType.APPLICATION_JSON
      ).accept(
        MediaType.APPLICATION_JSON
      ).content(
        "{\"price\":101}"
      )
    ).andDo(print()).andExpect(
      status().isNotFound()
    );
  }

  @Test
	public void deleteOneByNameOk() throws Exception {
    this.mockMvc.perform(
      MockMvcRequestBuilders.delete(
        "/api/fruits?name=NotFound"
      ).accept(
        MediaType.APPLICATION_JSON
      )
    ).andDo(print()).andExpect(
      status().isOk()
    ).andExpect(
      jsonPath("$.count").value(0)
    );
  }

  @Test
	public void findAll() throws Exception {
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
	public void addAndUpdateByName() throws Exception {
		this.mockMvc.perform(
      MockMvcRequestBuilders.post(
        "/api/fruits"
      ).contentType(
        MediaType.APPLICATION_JSON
      ).accept(
        MediaType.APPLICATION_JSON
      ).content(
        "{\"name\":\"Peach\", \"price\":99}"
      )
    ).andDo(
      print()
    ).andExpect(
      status().isCreated()
    ).andExpect(
      jsonPath("$.name").value("Peach")
    ).andExpect(
      jsonPath("$.price").value(99)
    );

    this.mockMvc.perform(
      MockMvcRequestBuilders.put(
        "/api/fruits?name=Peach"
      ).contentType(
        MediaType.APPLICATION_JSON
      ).accept(
        MediaType.APPLICATION_JSON
      ).content(
        "{\"price\":101}"
      )
    ).andDo(print()).andExpect(
      status().isOk()
    ).andExpect(
      jsonPath("$.count").value(1)
    );

    this.mockMvc.perform(
      MockMvcRequestBuilders.get(
        "/api/fruits?name=Peach"
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
	public void addConflictAndDelete() throws Exception {
		this.mockMvc.perform(
      MockMvcRequestBuilders.post(
        "/api/fruits"
      ).contentType(
        MediaType.APPLICATION_JSON
      ).accept(
        MediaType.APPLICATION_JSON
      ).content(
        "{\"name\":\"Durian\",\"price\":500}"
      )
    ).andDo(
      print()
    ).andExpect(
      status().isCreated()
    ).andExpect(
      jsonPath("$.name").value("Durian")
    ).andExpect(
      jsonPath("$.price").value(500)
    );

    final String content = this.mockMvc.perform(
      MockMvcRequestBuilders.post(
        "/api/fruits"
      ).contentType(
        MediaType.APPLICATION_JSON
      ).accept(
        MediaType.APPLICATION_JSON
      ).content(
        "{\"name\":\"Durian\",\"price\":450}"
      )
    ).andDo(print()).andExpect(
      status().is(HttpStatus.CONFLICT.value())
    ).andExpect(
      jsonPath("$.name").value("Durian")
    ).andExpect(
      jsonPath("$.price").value(500)
    ).andReturn().getResponse().getContentAsString();

    int itemId = JsonPath.read(content, "$.id");
    this.mockMvc.perform(
      MockMvcRequestBuilders.delete(
        String.format("/api/fruits/%d", itemId)
      ).contentType(
        MediaType.APPLICATION_JSON
      ).accept(
        MediaType.APPLICATION_JSON
      )
    ).andDo(print()).andExpect(
      status().isNoContent()
    );
  }
}
