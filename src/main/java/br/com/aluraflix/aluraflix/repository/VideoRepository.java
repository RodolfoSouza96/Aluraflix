package br.com.aluraflix.aluraflix.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aluraflix.aluraflix.modelo.Video;

public interface VideoRepository extends JpaRepository<Video, Long>{



	Page<Video> findByCategoriaId(Long id, Pageable paginacao);

	Page<Video> findByTitulo(String search, Pageable paginacao);



}
