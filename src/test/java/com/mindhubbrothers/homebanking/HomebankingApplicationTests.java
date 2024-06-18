package com.mindhubbrothers.homebanking;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindhubbrothers.homebanking.dto.LoginDTO;
import com.mindhubbrothers.homebanking.dto.RegisterDto;
import com.mindhubbrothers.homebanking.dto.TransferDTO;
import com.mindhubbrothers.homebanking.repositories.AccountRepository;
import com.mindhubbrothers.homebanking.utils.AccountNumberGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class HomebankingApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void contextLoads() {
	}

//	@Test
//	public void userCanSignUp() throws Exception {
//		RegisterDto newUser = new RegisterDto("Test", "Mindhub", "Test@mindhub.com", "789");
//		mockMvc.perform(
//				post("/api/auth/signup")
//						.contentType("application/json")
//						.content(
//								objectMapper.writeValueAsString(newUser)
//						)
//						).andExpect(status().isCreated());
//	}
//
//	@Test
//	public void userCanLogin() throws Exception{
//		LoginDTO loginDTO = new LoginDTO("melba@mindhub.com", "123");
//		mockMvc.perform(
//				post("/api/auth/login")
//						.contentType("application/json")
//						.content(
//								objectMapper.writeValueAsString(loginDTO)
//						)
//						).andExpect(status().isOk());
//	}
//
//	@Test
//	public void loginAndSeeClientInformation() throws Exception{
//		MvcResult result = mockMvc.perform(
//				post("/api/auth/login")
//						.contentType("application/json")
//						.content(
//								objectMapper.writeValueAsString(new LoginDTO("melba@mindhub.com", "123"))
//						)
//		).andExpect(status().isOk()).andReturn();
//		String token = result.getResponse().getContentAsString();
//		mockMvc.perform((get("/api/auth/current").header("Authorization", "Bearer " + token)
//				.accept("application/json"))).andExpect(status().isOk())
//				.andExpect(jsonPath("firstName").value("Melba"))
//				.andExpect(jsonPath("lastName").value("Morel"))
//				.andExpect(jsonPath("email").value("melba@mindhub.com"));
//	}

//	@Test
//	public void joaquinCanGetAnotherAccount() throws Exception{
//		MvcResult result = mockMvc.perform(
//				post("/api/auth/login")
//						.contentType("application/json")
//						.content(
//								objectMapper.writeValueAsString(new LoginDTO("joaquinneulist@gmail.com", "456"))
//						)
//		).andExpect(status().isOk()).andReturn();
//		String token = result.getResponse().getContentAsString();
//		mockMvc.perform(post("/api/clients/current/accounts").header("Authorization", "Bearer " + token)
//				.accept("application/json")).andExpect(status().isCreated())
//				.andExpect(jsonPath("$").isNotEmpty());
//	}
//
//	@Test
//	public void melbaCanDoATransaction() throws Exception{
//		MvcResult result = mockMvc.perform(
//				post("/api/auth/login")
//						.contentType("application/json")
//						.content(
//								objectMapper.writeValueAsString(new LoginDTO("melba@mindhub.com", "123"))
//						)
//		).andExpect(status().isOk()).andReturn();
//		String token = result.getResponse().getContentAsString();
//		mockMvc.perform(post("/api/transactions").header("Authorization", "Bearer " + token)
//				.contentType("application/json")
//				.content(
//						objectMapper.writeValueAsString(new TransferDTO("VIN001", "VIN003", 10.0, "Test Transaction")
//						)
//				)
//		).andExpect(status().isCreated());
//	}
}
