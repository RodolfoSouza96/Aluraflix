package br.com.aluraflix.aluraflix.controller.dto;

import org.springframework.data.domain.Page;

import br.com.aluraflix.aluraflix.modelo.Categoria;

public class CategoriaDto {

	private Long id;
	private String titulo;
	private String cor;
	
	public CategoriaDto(Categoria categoria) {
		this.id = categoria.getId();
		this.titulo = categoria.getTitulo();
		this.cor = categoria.getCor();
	}

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getCor() {
		return cor;
	}

	public static Page<CategoriaDto> converter(Page<Categoria> categoria) {
		return categoria.map(CategoriaDto::new);
	}
	
	
	
}
