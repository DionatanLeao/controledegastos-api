package com.controledegastos.servico;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.controledegastos.entidade.Categoria;
import com.controledegastos.excecao.RegraNegocioException;
import com.controledegastos.repositorio.CategoriaRepositorio;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class CategoriaServico {
	
	@Autowired
	private CategoriaRepositorio categoriaRepositorio;
	
	//BuscarTodos
	public List<Categoria> buscarTodos() {

		return categoriaRepositorio.findAll();
	}
	
	//BuscarPorId
	public Optional<Categoria> buscarPorId(Long codigo) {
		return categoriaRepositorio.findById(codigo);
	}
	
	//BuscarPorTexto
	public List<Categoria> filtarCategoriaPorTexto(String texto) {
		return categoriaRepositorio.findByNomePorTexto(texto);
	}
	//SalvarCategoria
	public Categoria salvar(Categoria categoria) {
		return categoriaRepositorio.save(categoria);
	}
	
	//AtualizarCategoria
	public Categoria atualizar(Long codigo, Categoria categoria) {
		Categoria categoriaSalvar = verificarCategoriaExiste(codigo);
		BeanUtils.copyProperties(categoria, categoriaSalvar, "codigo");
		return categoriaRepositorio.save(categoriaSalvar);
	}
	
	//DeletarCategoria
	public void deletar(Long codigo) {
		verificarCategoriaExiste(codigo);
		categoriaRepositorio.deleteById(codigo);
	}
	
	//VerificarExiste
	private Categoria verificarCategoriaExiste(Long codigo) {
		Optional<Categoria> categoria = buscarPorId(codigo);
		if(!categoria.isPresent()) {
			throw new RegraNegocioException(String.format("A categoria de código %s não foi encontrada!", codigo));
		}
		return categoria.get();
	}
}
