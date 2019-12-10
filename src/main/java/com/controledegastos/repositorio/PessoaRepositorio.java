package com.controledegastos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.controledegastos.entidade.Pessoa;

public interface PessoaRepositorio extends JpaRepository<Pessoa, Long> {

}
