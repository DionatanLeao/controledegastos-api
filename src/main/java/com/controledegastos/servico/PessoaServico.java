package com.controledegastos.servico;

import org.springframework.beans.BeanUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.controledegastos.entidade.Pessoa;
import com.controledegastos.excecao.RegraNegocioException;
import com.controledegastos.repositorio.PessoaRepositorio;
import java.util.List;
import java.util.Optional;

@Service
public class PessoaServico {
	
	@Autowired
	private PessoaRepositorio pessoaRepositorio;
	
	//BuscarTodos
	public List<Pessoa> buscarTodos() {	
		
		return pessoaRepositorio.findAll();
	}
	
	//BuscarPorId
	public Optional<Pessoa> buscarPorId(Long codigo) {
		return pessoaRepositorio.findById(codigo);
	}
	
	//SalvarPessoa
	public Pessoa salvar(Pessoa pessoa ) {
		return pessoaRepositorio.save(pessoa);
	}
	
	//AtualizarPessoa
	public Pessoa atualizar(Long codigo, Pessoa pessoa) {
		Pessoa pessoaSalvar = verificarPessoaExiste(codigo);
		BeanUtils.copyProperties(pessoa, pessoaSalvar, "codigo");
		return pessoaRepositorio.save(pessoaSalvar);
	}
	
	//DeletarPessoa
	public void deletar(Long codigo) {
		verificarPessoaExiste(codigo);
		pessoaRepositorio.deleteById(codigo);
	}
	
	//VerificarExiste
	private Pessoa verificarPessoaExiste(Long codigo) {
		Optional<Pessoa> pessoa = buscarPorId(codigo);
		if(!pessoa.isPresent()) {
			throw new RegraNegocioException(String.format("A Pessoa de código %s não foi encontrada!", codigo));
		}
		return pessoa.get();
	}

}










