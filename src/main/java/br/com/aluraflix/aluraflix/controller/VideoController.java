package br.com.aluraflix.aluraflix.controller;


import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.aluraflix.aluraflix.controller.dto.VideoDto;
import br.com.aluraflix.aluraflix.controller.form.AtualizarVideo;
import br.com.aluraflix.aluraflix.controller.form.VideoForm;
import br.com.aluraflix.aluraflix.modelo.Video;
import br.com.aluraflix.aluraflix.repository.CategoriaRepository;
import br.com.aluraflix.aluraflix.repository.VideoRepository;

@RestController
@RequestMapping("/videos")
public class VideoController {

	@Autowired
	private VideoRepository videoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping
	@Cacheable(value = "listaDeVideos")
	public Page<VideoDto> lista(@RequestParam(required = false) String search, 
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
				
			if(search == null) {
				Page<Video> video = videoRepository.findAll(paginacao);
				return VideoDto.converter(video);
			}else {
				Page<Video> video = videoRepository.findByTitulo(search, paginacao);
				return VideoDto.converter(video);
			}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<VideoDto> detalhar(@PathVariable Long id){
		Optional<Video> video = videoRepository.findById(id);
			if(video.isPresent()) {
				return ResponseEntity.ok(new VideoDto(video.get()));
			}
				return ResponseEntity.notFound().build();
		
		
	}
	
	@GetMapping("/free")
	public ResponseEntity<List<VideoDto>> listafree(){
		List<Video> video = videoRepository.findFree(10);
		
		return ResponseEntity.ok(VideoDto.converterlista(video));
	}
		
	
	@PostMapping
	@Transactional
	@CacheEvict(value = "listaDeVideos", allEntries = true)
	public ResponseEntity<VideoDto> cadastrar(@RequestBody @Valid VideoForm videoform, UriComponentsBuilder uriBuilder) {
		Video video = videoform.converter(videoform, categoriaRepository) ;
		
			
				videoRepository.save(video);
				URI uri = uriBuilder.path("/videos/{id}").buildAndExpand(video.getId()).toUri();
				return ResponseEntity.created(uri).body(new VideoDto(video));

					
			
				
			
	}
	
	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaDeVideos", allEntries = true)
	public ResponseEntity<VideoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizarVideo atuaVideo){
			Video video = atuaVideo.atualizar(id, atuaVideo, videoRepository, categoriaRepository);
			return ResponseEntity.ok(new VideoDto(video));
		
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaDeVideos", allEntries = true)
	public ResponseEntity<Video> deletar(@PathVariable Long id) {
		Optional<Video> video = videoRepository.findById(id);
		if(video.isPresent()) {
			videoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	
}
