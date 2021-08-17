package br.com.aluraflix.aluraflix.controller.form;

import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.NotFound;
import org.hibernate.validator.constraints.Length;

import br.com.aluraflix.aluraflix.modelo.Categoria;
import br.com.aluraflix.aluraflix.repository.CategoriaRepository;

public class AtualizarCategoria {

	@NotFound @NotEmpty @Length(min = 4)
	private String titulo;
	
	@NotFound @NotEmpty @Length(min = 3)
	private String cor;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public Categoria atualizar() {

		return null;
	}

	public Categoria atualizar(Long id, CategoriaRepository categoriaRepository) {
		@SuppressWarnings("deprecation")
		Categoria categoria = categoriaRepository.getOne(id);
		categoria.setTitulo(titulo);
		categoria.setCor(cor);
		return categoria;
	}
	
	
	
}
