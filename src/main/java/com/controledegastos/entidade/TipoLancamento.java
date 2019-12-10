package com.controledegastos.entidade;

public enum TipoLancamento {
	
	RECEITA,
	DESPESA;
	
	private String descricao;
	
	public String getDescricao() {
		return descricao;
	}
}
