package com.controledegastos.controlador;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.controledegastos.entidade.Lancamento;
import com.controledegastos.servico.LancamentoServico;


@RestController
@RequestMapping("/lancamentos")
public class LancamentoControlador {
	
	@Autowired
	private LancamentoServico lancamentoServico;
	
	//BuscarTodos
	@GetMapping
	public List<Lancamento> buscarTodos() {
		return lancamentoServico.buscarTodos();
	}
	
	//BuscarPorCodigo
	@GetMapping("/{codigo}")
	public ResponseEntity<Lancamento> buscarPorCodigo(@PathVariable Long codigo) {
		Optional<Lancamento> lancamento = lancamentoServico.buscarPorCodigo(codigo);
		return lancamento.isPresent() ? ResponseEntity.ok(lancamento.get()) : ResponseEntity.notFound().build();
	
	}
	
	@GetMapping("/filtrarPorTexto")
	public ResponseEntity<List<Lancamento>> filtrarLancamentoPorTexto(@RequestParam("texto") String texto) {
		return ResponseEntity.ok(lancamentoServico.filtrarLancamentoPorTexto(texto));
	}
	
	//SalvarLançamento
	@PostMapping
	public ResponseEntity<Lancamento> salvar(@Valid @RequestBody Lancamento lancamento) {
		return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoServico.salvar(lancamento));
	}
		
	//AtualizarLançamento
	@PutMapping("/{codigo}")
	public ResponseEntity<Lancamento> atualizar(@PathVariable("codigo") Long codigo, @Valid @RequestBody Lancamento lancamento) {
		return ResponseEntity.ok(lancamentoServico.atualizar(codigo,  lancamento));
	}	
		
	//DeletarLancamento
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable("codigo") Long codigo) {
		lancamentoServico.deletar(codigo);
	}
	
}
