package br.com.aluraflix.aluraflix.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.aluraflix.aluraflix.modelo.Video;

public interface VideoRepository extends JpaRepository<Video, Long>{



	Page<Video> findByCategoriaId(Long id, Pageable paginacao);

	Page<Video> findByTitulo(String search, Pageable paginacao);
	
	@Query(nativeQuery = true, value = "SELECT * FROM video vd ORDER BY vd.id LIMIT :qt")
	List<Video> findFree(@Param("qt") Integer quantidade);



}
