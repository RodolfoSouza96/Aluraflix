package br.com.aluraflix.aluraflix.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.aluraflix.aluraflix.modelo.Categoria;
import br.com.aluraflix.aluraflix.modelo.Video;
import br.com.aluraflix.aluraflix.repository.CategoriaRepository;
import br.com.aluraflix.aluraflix.repository.VideoRepository;

public class AtualizarVideo {

	@NotNull @NotEmpty @Length(min = 5)
	private String titulo;
	@NotNull @NotEmpty @Length(min = 10)
	private String descricao;
	private Long idCategoria;
	
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
		
	public Long getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}
	public Video atualizar(Long id, AtualizarVideo atuaVideo, VideoRepository videoRepository, CategoriaRepository categoriaRepository) {
		Video video = videoRepository.getById(id);
		Long Categoriaid = atuaVideo.getIdCategoria() == null ? 1L : atuaVideo.getIdCategoria();
		Categoria categoria = categoriaRepository.getById(Categoriaid);
		video.setCategoria(categoria);
		video.setTitulo(titulo);
		video.setDescricao(descricao);
		return video;
	}
	
	
	
}
