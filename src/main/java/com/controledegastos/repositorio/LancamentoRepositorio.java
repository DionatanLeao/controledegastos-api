package com.controledegastos.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.controledegastos.entidade.Lancamento;

public interface LancamentoRepositorio extends JpaRepository <Lancamento, Long> {
	
	@Query("Select l from Lancamento l where l.descricao like %:texto%")
	List<Lancamento> findByDescricaoPorTexto(@Param("texto") String texto);
}
