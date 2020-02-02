package com.controledegastos.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.controledegastos.entidade.Pessoa;

public interface PessoaRepositorio extends JpaRepository<Pessoa, Long> {
	
	@Query("select p from Pessoa p where p.nome like %:texto%")
	List<Pessoa> findByCidades(@Param("texto") String texto);
}
