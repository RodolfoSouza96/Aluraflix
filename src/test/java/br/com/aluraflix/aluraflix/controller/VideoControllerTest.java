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
public class VideoControllerTest {

	@Autowired
	private MockMvc mockMvc;
		
	@Test
	public void deveriaRetornarTodosOsVideosCadastrados() throws Exception {
		URI uri = new URI("/videos");
		
		mockMvc.perform(MockMvcRequestBuilders.get(uri))
		.andExpect(MockMvcResultMatchers.status().is(200));
	}
	
	@Test
	public void deveriaRetornarUmVideoPeloId() throws Exception {
		URI uri = new URI("/videos/2");
		
		mockMvc.perform(MockMvcRequestBuilders.get(uri))
		.andExpect(MockMvcResultMatchers.status().is(200));
	}
	
	@Test
	public void deveriaRetornarErro404AoTentarRetornarVideoQueNaoEstaCadastrado() throws Exception {
		URI uri = new URI("/videos/200");
		
		mockMvc.perform(MockMvcRequestBuilders.get(uri))
		.andExpect(MockMvcResultMatchers.status().is(404));
	}
	
	@Test
	public void deveriaRetornar201AoCadastrarUmNovoVideo() throws Exception {
		URI uri = new URI("/videos");
		String json = "{\"titulo\":\"Mib Homens de Preto\",\"descricao\":\"Homens de Preto\",\"url\":\"http://www.youtube.com/mib\",\"idCategoria\":\"\"}";
		
		mockMvc.perform(MockMvcRequestBuilders.post(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(201));
	}

		
	@Test
	public void deveriaRetornar200AoEditarOVideo() throws Exception {
		URI uri = new URI("/videos/18");
		String json = "{\"titulo\":\"Os Vingadores2\",\"descricao\":\"Vingadores\",\"url\":\"http://www.youtube.com/OsVingadores\",\"idCategoria\":\"\"}";
		
		mockMvc.perform(MockMvcRequestBuilders.put(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(200));
	}
		
}
