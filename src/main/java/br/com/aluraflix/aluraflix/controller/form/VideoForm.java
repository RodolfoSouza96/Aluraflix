package br.com.aluraflix.aluraflix.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import br.com.aluraflix.aluraflix.modelo.Categoria;
import br.com.aluraflix.aluraflix.modelo.Video;
import br.com.aluraflix.aluraflix.repository.CategoriaRepository;

public class VideoForm {

	@NotNull @NotEmpty @Length(min = 5)
	private String titulo;
	@NotNull @NotEmpty @Length(min = 10)
	private String descricao;
	@URL(protocol = "http")
	@NotNull @NotEmpty @Length(max = 60)
	private String url;
	private Long idCategoria;
		
	public VideoForm() {
	}
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
		
	public Long getIdCategoria() {
		return idCategoria;
	}
		
	public Video converter(VideoForm videoform, CategoriaRepository categoriaRepository) {
		Video video = new Video();
		
		Long Categoriaid = videoform.getIdCategoria() == null ? 1L : videoform.getIdCategoria();
		Categoria categoria = categoriaRepository.getById(Categoriaid);
		video.setCategoria(categoria);
		return new Video(titulo, descricao, url, categoria);
	}
	
	
	
	
	
	
}
