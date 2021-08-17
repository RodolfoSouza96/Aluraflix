package br.com.aluraflix.aluraflix.controller;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
class CategoriaControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void deveriaRetornarTodosAsCategoriasCadastradas() throws Exception {
		
		URI uri = new URI("/categoria");
		
		mockMvc.perform(MockMvcRequestBuilders.get(uri))
		.andExpect(MockMvcResultMatchers.status().is(200));
	}
	
	@Test
	public void deveriaRetornarApenasUmaCategoria() throws Exception {
		URI uri = new URI("/categoria/1");
		
		mockMvc.perform(MockMvcRequestBuilders.get(uri))
		.andExpect(MockMvcResultMatchers.status().is(200));
	}
	
	@Test
	public void deveriaRetornar404AoTentarRetornarCategoriaInexistente() throws Exception {
		URI uri = new URI("/categoria/20");
		
		mockMvc.perform(MockMvcRequestBuilders.get(uri))
		.andExpect(MockMvcResultMatchers.status().is(404));
	}
	
	@Test
	public void deveriaRetornar201AoCadastrarUmANovACategoria() throws Exception {
		URI uri = new URI("/categoria");
		String json = "{\"titulo\":\"AÇÃO\",\"cor\":\"#C1C\"}";
		mockMvc.perform(MockMvcRequestBuilders.post(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(201));
	}
	
	@Test
	public void deveriaRetornar200AoEditarUmACategoria() throws Exception {
		URI uri = new URI("/categoria/3");
		String json = "{\"titulo\":\"ROMANCE\",\"cor\":\"#C1C\"}";
		mockMvc.perform(MockMvcRequestBuilders.put(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(200));
	}
	
	@Test
	public void deveriaRetornar404AoEditarCategoriaObrigatoria() throws Exception {
		URI uri = new URI("/categoria/1");
		String json = "{\"titulo\":\"ROMANCE\",\"cor\":\"#C1C\"}";
		mockMvc.perform(MockMvcRequestBuilders.put(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

}
