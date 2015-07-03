package org.projeto.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.projeto.model.Produto;

@Named
@ViewScoped
public class HomeBean implements Serializable {

	private List<Produto> listaDeProdutos;

	private static final long serialVersionUID = 1L;

	@PostConstruct
	public void init() {
		Produto produto = new Produto();
		produto.setId(1);
		produto.setNome("Sabão em pó");
		produto.setDescrição("Sabão em pó de um 1kg");
		produto.setQuantidadeEmEstoque(10);
		produto.setValorUnitario(new BigDecimal("10.20"));

		this.listaDeProdutos = new ArrayList<Produto>();
		listaDeProdutos.add(produto);
		listaDeProdutos.add(produto);
		listaDeProdutos.add(produto);
		listaDeProdutos.add(produto);
		listaDeProdutos.add(produto);
		listaDeProdutos.add(produto);
		listaDeProdutos.add(produto);
		listaDeProdutos.add(produto);
		listaDeProdutos.add(produto);
		listaDeProdutos.add(produto);
		listaDeProdutos.add(produto);
		listaDeProdutos.add(produto);
		listaDeProdutos.add(produto);
		listaDeProdutos.add(produto);
		listaDeProdutos.add(produto);
		listaDeProdutos.add(produto);
		listaDeProdutos.add(produto);
	}

	public List<Produto> getListaDeProdutos() {
		return listaDeProdutos;
	}

}
