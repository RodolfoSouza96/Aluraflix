package br.com.aluraflix.aluraflix.controller.dto;

import org.springframework.data.domain.Page;

import br.com.aluraflix.aluraflix.modelo.Video;

public class VideoDto {

	private Long id;
	private String titulo;
	private String descricao;
	private String url;
	private Long categoriaId;
	
	
	public VideoDto(Video video) {
		this.id = video.getId();
		this.titulo = video.getTitulo();
		this.categoriaId = video.getCategoria().getId();
		this.descricao = video.getDescricao();
		this.url = video.getUrl();
	}
	
	public Long getId() {
		return id;
	}
	public String getTitulo() {
		return titulo;
	}
	
	public Long getCategoriaId() {
		return categoriaId;
	}
	
	public String getDescricao() {
		return descricao;
	}
	public String getUrl() {
		return url;
	}


	public static Page<VideoDto> converter(Page<Video> video) {
		return video.map(VideoDto::new);
	}
	
		
	
}
