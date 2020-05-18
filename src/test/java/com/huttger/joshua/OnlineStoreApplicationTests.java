package com.huttger.joshua;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.huttger.joshua.models.Product;

@SpringBootTest
@AutoConfigureMockMvc
class OnlineStoreApplicationTests {
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper jsonMapper;
	
	@Test
	void contextLoads() {
	}
	
	private Product jsonStringToProduct(String jsonString) throws JsonMappingException, JsonProcessingException {
		return jsonMapper.readValue(jsonString, Product.class);
	}
	
	private Product postProduct(String name, String desc,long price) throws JsonProcessingException, Exception {
		Product newProduct = new Product(name,desc,price);
		MvcResult requestResult = mockMvc.perform(post("/products")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonMapper.writeValueAsString(newProduct)))
				.andExpect(status().isOk())
				.andReturn();
		return jsonStringToProduct(requestResult.getResponse().getContentAsString());
	}
	
	private Product getProductById(long id) throws JsonMappingException, JsonProcessingException, UnsupportedEncodingException, Exception {
		return jsonMapper.readValue(mockMvc.perform(get("/products/"+id))
				.andReturn()
				.getResponse()
				.getContentAsString(), 
				Product.class);

	}
	
	@Test
	void testAddProduct() throws JsonProcessingException, Exception {
		Product justCreatedProduct = postProduct("chicken","fried chicken with sauce",1000l);
		Product getApiCallResult = getProductById(justCreatedProduct.getId());
		assertEquals(justCreatedProduct,getApiCallResult);
	}

}
