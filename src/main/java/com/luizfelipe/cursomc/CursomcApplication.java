package com.luizfelipe.cursomc;

import com.luizfelipe.cursomc.domain.Categoria;
import com.luizfelipe.cursomc.domain.Cidade;
import com.luizfelipe.cursomc.domain.Estado;
import com.luizfelipe.cursomc.domain.Produto;
import com.luizfelipe.cursomc.repositories.CategoriaRepository;
import com.luizfelipe.cursomc.repositories.CidadeRepository;
import com.luizfelipe.cursomc.repositories.EstadoRepository;
import com.luizfelipe.cursomc.repositories.ProdutoRepository;
import org.hibernate.annotations.NaturalId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EmptyStackException;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		Categoria categoria1 = new Categoria();
		categoria1.setNome("Informática");

		Categoria categoria2 = new Categoria();
		categoria2.setNome("Escritório");

		//Categoria categoria1 = new Categoria(null, "Informática");
		//Categoria categoria2 = new Categoria(null, "Ecritório");

		Produto produto1 = new Produto();
		produto1.setNome("Computador");
		produto1.setPreco( 2000.00);

		Produto produto2 = new Produto();
		produto2.setNome("Impressora");
		produto2.setPreco(800.00);

		Produto produto3 = new Produto();
		produto3.setNome("Mouse");
		produto3.setPreco(80.00);

		//Produto produto1 = new Produto(null, "Computador", 2000.00);
		//Produto produto2 = new Produto(null, "Impressora", 800.00);
		//Produto produto3 = new Produto(null, "Mouse", 80.00);

		categoria1.getProdutos().addAll(Arrays.asList(produto1, produto2, produto3));
		categoria2.getProdutos().addAll(Arrays.asList(produto2));

		produto1.getCategorias().addAll(Arrays.asList(categoria1));
		produto2.getCategorias().addAll(Arrays.asList(categoria1, categoria2));
		produto3.getCategorias().addAll(Arrays.asList(categoria1));


		categoriaRepository.save(Arrays.asList(categoria1, categoria2));
		produtoRepository.save(Arrays.asList(produto1, produto2, produto3));


		// estado com cidades
		Estado estado1 = new Estado();
		estado1.setNome("Minas Gerais");

		Estado estado2 = new Estado();
		estado2.setNome("São Paulo");

		Cidade cidade1 = new Cidade();
		cidade1.setNome("Uberlândia");
		cidade1.setEstado(estado1);

		Cidade cidade2 = new Cidade();
		cidade2.setNome("São Paulo");
		cidade2.setEstado(estado2);

		Cidade cidade3 = new Cidade();
		cidade3.setNome("Campinas");
		cidade3.setEstado(estado2);

		estado1.getCidades().addAll(Arrays.asList(cidade1));
		estado2.getCidades().addAll(Arrays.asList(cidade2, cidade3));


		estadoRepository.save(Arrays.asList(estado1, estado2));
		cidadeRepository.save(Arrays.asList(cidade1, cidade2, cidade3));

	}
}
