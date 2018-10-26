package br.edu.iftm.ExtensaoSB.ExtensaoSB.services;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AtividadeResourceTest {
	@Autowired
	private WebApplicationContext context;
	
	private MockMvc mvc;
		
	@Before
	public void setup() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}
	
	@Test
	public void test01RequestSuccess() throws Exception {
		String url = "/atividades/1";
		
		this.mvc.perform(get(url).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("nome", equalTo("Visita Tecnica GDG 2018")));
	}
	
	
	@Test
	public void test02RequestFailed() throws Exception {
		String url = "/atividades/5";
		
		this.mvc.perform(get(url))
			.andExpect(status().isNotFound());
	}
	
	@Test
	public void test03RequestNomeContainingSuccess() throws Exception {
		String url = "/atividades/like/gdg";
		
		this.mvc.perform(get(url))
			.andExpect(status().isOk())
			.andExpect(content().string(containsString("GDG 2018")));
	}
	
	@Test
	public void test04RequestNomeContainingFailed() throws Exception {
		String url = "/atividades/like/vlads";
		
		this.mvc.perform(get(url))
			.andExpect(status().isNotFound());
	}
	
	
	@Test
	public void test05DeleteSuccess() throws Exception {
		String url = "/atividades/1";
		
		this.mvc.perform(delete(url))
			.andExpect(status().isNoContent());
	}
	
	@Test
	public void test06DeleteSuccess() throws Exception {
		String url = "/atividades/10";
		
		this.mvc.perform(delete(url))
			.andExpect(status().isNotFound());
	}
	
	@Test
	public void test07SaveSuccess() throws Exception {
		String url = "/atividades";
		String body = "{\"nome\": \"tovlads\"}";
		
		this.mvc.perform(post(url).content(body).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated())
			.andExpect(content().string(containsString("vlads")))
			.andExpect(header().string("Location", is("http://localhost/atividades/4")))
			.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void test08SaveFailed() throws Exception {
		String url = "/atividades";
		String body = "{\"nodme\": \"tovlads\"}";
		
		this.mvc.perform(post(url).content(body).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isBadRequest());
	}	

	@Test
	public void test09SaveFailed2() throws Exception {
		String url = "/atividades";
		String body = "{\"nodme\": \"\"}";
		
		this.mvc.perform(post(url).content(body).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isBadRequest())
			.andExpect(content().string(containsString("Nome é obrigatório.")));
	}
	
	@Test
	public void test10PutSuccess() throws Exception {
		String url = "/atividades";
		String body = "{\"id\": 2,\"nome\": \"GDG TOPPER\"}";
		
		this.mvc.perform(put(url).content(body).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNoContent())
			.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void test11PutFailed() throws Exception {
		String url = "/atividades";
		String body = "{\"id\": 10,\"nome\": \"GDG TOPPER\"}";
		
		this.mvc.perform(put(url).content(body).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotFound());
	}	

	
}
