package com.controledegastos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.controledegastos.entidade.Lancamento;

public interface LancamentoRepositorio extends JpaRepository <Lancamento, Long> {

}
