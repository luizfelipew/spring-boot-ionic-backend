package com.luizfelipe.cursomc;

import com.luizfelipe.cursomc.domain.*;
import com.luizfelipe.cursomc.domain.enums.EstadoPagamento;
import com.luizfelipe.cursomc.domain.enums.TipoCliente;
import com.luizfelipe.cursomc.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Arrays;

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

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;


	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {

		Categoria categoria1 = new Categoria();
		categoria1.setNome("Informática");

		Categoria categoria2 = new Categoria();
		categoria2.setNome("Escritório");

		Categoria categoria3 = new Categoria();
		categoria3.setNome("Cama mesa e banho");

		Categoria categoria4 = new Categoria();
        categoria4.setNome("Eletrônicos");

		Categoria categoria5 = new Categoria();
        categoria5.setNome("Jardinagem");

		Categoria categoria6 = new Categoria();
        categoria6.setNome("Decoracao");

		Categoria categoria7 = new Categoria();
        categoria7.setNome("Perfumaria");

		//Categoria categoria1 = new Categoria(null, "Informática");
		//Categoria categoria2 = new Categoria(null, "Ecritório");

		/*Produto produto1 = new Produto();
		produto1.setNome("Computador");
		produto1.setPreco( 2000.00);

		Produto produto2 = new Produto();
		produto2.setNome("Impressora");
		produto2.setPreco(800.00);

		Produto produto3 = new Produto();
		produto3.setNome("Mouse");
		produto3.setPreco(80.00);*/

		Produto produto1 = new Produto(null, "Computador", 2000.00);
		Produto produto2 = new Produto(null, "Impressora", 800.00);
		Produto produto3 = new Produto(null, "Mouse", 80.00);
		Produto produto4 = new Produto(null, "Mesa de escritorio", 300.00);
		Produto produto5 = new Produto(null, "Toalha", 50.00);
		Produto produto6 = new Produto(null, "Colcha", 200.00);
		Produto produto7 = new Produto(null, "TV true color", 1200.00);
		Produto produto8 = new Produto(null, "Rocadeira", 800.00);
		Produto produto9 = new Produto(null, "Abajour", 100.00);
		Produto produto10 = new Produto(null, "Pendente", 180.00);
		Produto produto11 = new Produto(null, "Shampoo", 90.00);

		categoria1.getProdutos().addAll(Arrays.asList(produto1, produto2, produto3));
		categoria2.getProdutos().addAll(Arrays.asList(produto2, produto4));
		categoria3.getProdutos().addAll(Arrays.asList(produto5, produto6));
		categoria4.getProdutos().addAll(Arrays.asList(produto1, produto2, produto3, produto7));
		categoria5.getProdutos().addAll(Arrays.asList(produto8));
		categoria6.getProdutos().addAll(Arrays.asList(produto9, produto10));
		categoria7.getProdutos().addAll(Arrays.asList(produto11));

		produto1.getCategorias().addAll(Arrays.asList(categoria1));
		produto2.getCategorias().addAll(Arrays.asList(categoria1, categoria2, categoria4));
		produto3.getCategorias().addAll(Arrays.asList(categoria1, categoria4));
		produto4.getCategorias().addAll(Arrays.asList(categoria2));
		produto5.getCategorias().addAll(Arrays.asList(categoria3));
		produto6.getCategorias().addAll(Arrays.asList(categoria3));
		produto7.getCategorias().addAll(Arrays.asList(categoria4));
		produto8.getCategorias().addAll(Arrays.asList(categoria5));
		produto9.getCategorias().addAll(Arrays.asList(categoria6));
		produto10.getCategorias().addAll(Arrays.asList(categoria6));
		produto11.getCategorias().addAll(Arrays.asList(categoria7));


		categoriaRepository.save(Arrays.asList(categoria1, categoria2, categoria3, categoria4, categoria5, categoria6, categoria7));
		produtoRepository.save(Arrays.asList(produto1, produto2, produto3, produto4
				                           , produto5, produto6, produto7, produto8
				                           , produto9, produto10, produto11));


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


		Cliente cliente1 = new Cliente();
		cliente1.setNome("Maria Silva");
		cliente1.setEmail("maria@gmail.com");
		cliente1.setCpfOuCnpj("36378912377");
		cliente1.setTipo(TipoCliente.PESSOAFISICA.getCod());

		cliente1.getTelefones().addAll(Arrays.asList("27363323","93838393"));

		Endereco endereco1 = new Endereco();
		endereco1.setLogradouro("Rua Flores");
		endereco1.setNumero("300");
		endereco1.setComplemento("Apto 303");
		endereco1.setBairro("Jardim");
		endereco1.setCep("38220834");
		endereco1.setCliente(cliente1);
		endereco1.setCidade(cidade1);

		Endereco endereco2 = new Endereco();
		endereco2.setLogradouro("Avenida Matos");
		endereco2.setNumero("105");
		endereco2.setComplemento("Sala 80");
		endereco2.setBairro("Centro");
		endereco2.setCep("38777012");
		endereco2.setCliente(cliente1);
		endereco2.setCidade(cidade2);


		cliente1.getEnderecos().addAll(Arrays.asList(endereco1, endereco2));

		clienteRepository.save(Arrays.asList(cliente1));
		enderecoRepository.save(Arrays.asList(endereco1, endereco2));

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");

		Pedido pedido1 = new Pedido();
		pedido1.setInstate(simpleDateFormat.parse("30/09/2017 10:32"));
		pedido1.setCliente(cliente1);
		pedido1.setEnderecoDeEntrega(endereco1);

		Pedido pedido2 = new Pedido();
		pedido2.setInstate(simpleDateFormat.parse("10/10/2017 19:35"));
		pedido2.setCliente(cliente1);
		pedido2.setEnderecoDeEntrega(endereco2);

		Pagamento pagamento1 = new PagamentoComCartao(EstadoPagamento.QUITADO,pedido1, 6);
		pedido1.setPagamento(pagamento1);


		Pagamento pagamento2 = new PagamentoComBoleto(EstadoPagamento.PENDENTE,pedido2, simpleDateFormat.parse("20/10/2017 00:00"), null);
		pedido2.setPagamento(pagamento2);

		cliente1.getPedidos().addAll(Arrays.asList(pedido1, pedido2));


		pedidoRepository.save(Arrays.asList(pedido1, pedido2));
		pagamentoRepository.save(Arrays.asList(pagamento1, pagamento2));


		ItemPedido itemPedido1 = new ItemPedido(pedido1, produto1, 0.00,1, 2000.00);
		ItemPedido itemPedido2 = new ItemPedido(pedido1, produto3, 0.00,2, 80.00);
		ItemPedido itemPedido3 = new ItemPedido(pedido2, produto2, 100.00,1, 800.00);

		pedido1.getItens().addAll(Arrays.asList(itemPedido1, itemPedido2));
		pedido2.getItens().addAll(Arrays.asList(itemPedido3));


		produto1.getItens().addAll(Arrays.asList(itemPedido1));
		produto2.getItens().addAll(Arrays.asList(itemPedido3));
		produto3.getItens().addAll(Arrays.asList(itemPedido2));

		itemPedidoRepository.save(Arrays.asList(itemPedido1, itemPedido2, itemPedido3));

	}
}
