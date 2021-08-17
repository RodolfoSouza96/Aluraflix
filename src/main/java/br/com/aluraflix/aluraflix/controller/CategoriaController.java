package br.com.aluraflix.aluraflix.controller;


import java.net.URI;
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

import br.com.aluraflix.aluraflix.controller.dto.CategoriaDto;
import br.com.aluraflix.aluraflix.controller.dto.VideoDto;
import br.com.aluraflix.aluraflix.controller.form.AtualizarCategoria;
import br.com.aluraflix.aluraflix.controller.form.CategoriaForm;
import br.com.aluraflix.aluraflix.modelo.Categoria;
import br.com.aluraflix.aluraflix.modelo.Video;
import br.com.aluraflix.aluraflix.repository.CategoriaRepository;
import br.com.aluraflix.aluraflix.repository.VideoRepository;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private VideoRepository videoRepository;
	
	@GetMapping
	@Cacheable(value = "listaDeCategorias")
	public Page<CategoriaDto> listar(@RequestParam(required = false) String titulo,
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10)  Pageable paginacao) {
				
			Page<Categoria> categoria = categoriaRepository.findAll(paginacao);
			return CategoriaDto.converter(categoria);

		
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoriaDto> detalhar(@PathVariable Long id){
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		if(categoria.isPresent()) {
			return ResponseEntity.ok(new CategoriaDto(categoria.get()));
		}
			return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/{id}/videos")
	public ResponseEntity<Page<VideoDto>> buscarPorCategoria(@PathVariable Long id, Pageable paginacao){
				
		Page<Video> lista = videoRepository.findByCategoriaId(id, paginacao);
		if(lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(VideoDto.converter(lista));
	}
	
	@PostMapping
	@Transactional
	@CacheEvict(value = "listaDeCategorias", allEntries = true)
	public ResponseEntity<String> cadastrar(@RequestBody @Valid CategoriaForm catform, UriComponentsBuilder uriBuilder){
		Categoria categoria = catform.converter();
		categoriaRepository.save(categoria);
		
		URI uri = uriBuilder.path("/categoria/{id}").buildAndExpand(categoria.getId()).toUri();
		return ResponseEntity.created(uri).body("Categoria cadastrada com sucesso");
	}
	
	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaDeCategorias", allEntries = true)
	public ResponseEntity<String> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizarCategoria atuacat ) {
		
		if(id == 1) {
			return ResponseEntity.badRequest().body("Nao é possivel alterar este id");
		}
			atuacat.atualizar(id, categoriaRepository);
			return ResponseEntity.ok().body("Atualizado com sucesso");
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaDeCategorias", allEntries = true)
	public ResponseEntity<String> deletar(@PathVariable Long id){
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		
		if (id == 1) {
				return ResponseEntity.badRequest().body("Nao é possivel deletar este id, pois o mesmo é obrigatório");
			} else if (categoria.isPresent()) {
				categoriaRepository.deleteById(id);
				return ResponseEntity.ok().body("Categoria excluida conforme solicitado");
			} else {
				return ResponseEntity.notFound().build();
			}			
	}
	
}
