package br.com.aluraflix.aluraflix.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.aluraflix.aluraflix.config.validacao.Cor;
import br.com.aluraflix.aluraflix.modelo.Categoria;

public class CategoriaForm {


	@NotNull (message = "O Campo deve ser obrigatório")
	@NotEmpty (message = "O Campo deve ser obrigatório")
	@Length(min = 4, message = "O Campo deve ser preenchido no minimo com 4 letras")
	private String titulo;
	
	@Cor 
	@NotNull (message = "O Campo deve ser obrigatório")
	@NotEmpty (message = "O Campo deve ser obrigatório")
	@Length(min = 4, message = "O campo deve ter no minimo 4 letras")
	@Length(max = 7, message = "O campo deve ter no maximo 7 letras")
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

	public Categoria converter() {
		return new Categoria(titulo, cor);
	}
	
	
	
}
