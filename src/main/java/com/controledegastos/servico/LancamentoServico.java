package com.controledegastos.servico;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.controledegastos.entidade.Lancamento;
import com.controledegastos.excecao.RegraNegocioException;
import com.controledegastos.repositorio.LancamentoRepositorio;

@Service
public class LancamentoServico {
	
	@Autowired
	private LancamentoRepositorio lancamentoRepositorio;
	
	//BuscarTodos
	public List<Lancamento> buscarTodos() {
		return lancamentoRepositorio.findAll();
	}
	
	//BuscarPorCodigo
	public Optional<Lancamento> buscarPorCodigo(Long codigo) {
		return lancamentoRepositorio.findById(codigo);
	}
	//FiltrarPorTexto
	public List<Lancamento> filtrarLancamentoPorTexto(String texto) {
		return lancamentoRepositorio.findByDescricaoPorTexto(texto);
	}
	
	//SalvarLançamento
	public Lancamento salvar(Lancamento lancamento) {
		return lancamentoRepositorio.save(lancamento);
	}
	
	//AtualizarLançamento
	public Lancamento atualizar(Long codigo, Lancamento lancamento) {
		Lancamento lancamentoSalvar = verificarLancamentoExiste(codigo);
		BeanUtils.copyProperties(lancamento, lancamentoSalvar, "codigo");
		return lancamentoRepositorio.save(lancamentoSalvar);
	}
	
	//DeletarLançamento
	public void deletar(Long codigo) {
		verificarLancamentoExiste(codigo);
		lancamentoRepositorio.deleteById(codigo);
	}
	
	//VerificarExiste
	private Lancamento verificarLancamentoExiste(Long codigo) {
		Optional<Lancamento> lancamento = buscarPorCodigo(codigo);
		if(!lancamento.isPresent()) {
			throw new RegraNegocioException(String.format("O Lançamento de código %s não existe!", codigo));
		}
		return lancamento.get();
	}
	
	
}









