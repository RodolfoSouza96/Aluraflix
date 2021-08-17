package br.com.aluraflix.aluraflix.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aluraflix.aluraflix.modelo.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
	
}
