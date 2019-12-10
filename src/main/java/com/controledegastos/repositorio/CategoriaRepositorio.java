package com.controledegastos.repositorio;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.controledegastos.entidade.Categoria;

public interface CategoriaRepositorio extends JpaRepository<Categoria, Long> {
	
	@Query("select c from Categoria c where c.nome like %:texto%")
	List<Categoria> findByNomePorTexto(@Param("texto") String texto);
}
