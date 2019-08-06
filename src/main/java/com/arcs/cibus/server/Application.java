package com.arcs.cibus.server;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.arcs.cibus.server.domain.Categoria;
import com.arcs.cibus.server.domain.Cidade;
import com.arcs.cibus.server.domain.Cliente;
import com.arcs.cibus.server.domain.Endereco;
import com.arcs.cibus.server.domain.Estado;
import com.arcs.cibus.server.domain.Pagamento;
import com.arcs.cibus.server.domain.PagamentoBoleto;
import com.arcs.cibus.server.domain.PagamentoCartao;
import com.arcs.cibus.server.domain.Pedido;
import com.arcs.cibus.server.domain.Produto;
import com.arcs.cibus.server.domain.enums.EstadoPagamento;
import com.arcs.cibus.server.domain.enums.TipoCliente;
import com.arcs.cibus.server.repository.CategoriaRepository;
import com.arcs.cibus.server.repository.CidadeRepository;
import com.arcs.cibus.server.repository.ClienteRepository;
import com.arcs.cibus.server.repository.EnderecoRepository;
import com.arcs.cibus.server.repository.EstadoRepository;
import com.arcs.cibus.server.repository.PagamentoRepository;
import com.arcs.cibus.server.repository.PedidoRepository;
import com.arcs.cibus.server.repository.ProdutoRepository;

@SpringBootApplication
public class Application implements CommandLineRunner {

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

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
//		================[ INICIALIZAÇÕES ]================		//
		
		Categoria primeiraCategoria = Categoria
				.builder()
				.nome("Refrigerante")
				.descricao("Refrigerantes")
			    .ativo(Boolean.TRUE)
			    .icone("")
				.build();
		
		Categoria segundaCategoria = Categoria
				.builder()
				.nome("Salgado")
				.descricao("Salgados")
			    .ativo(Boolean.TRUE)
			    .icone("")
				.build();
		
		Produto primeiroProduto = Produto
				.builder()
				.nome("Coca Cola")
				.preco(new BigDecimal(2.60))
				.estoqueMinimo(new Double(2.0))
				.imagem("")
				.quantidadeEstoque(new Double(10.0))
				.visivel(Boolean.TRUE)
				.build(); 
		
		Produto segundoProduto = Produto
				.builder()
				.nome("Coxinha")
				.preco(new BigDecimal(2.60))
				.estoqueMinimo(new Double(2.0))
				.imagem("")
				.quantidadeEstoque(new Double(10.0))
				.visivel(Boolean.TRUE)
				.build(); 
		
		Produto terceiroProduto = Produto
				.builder()
				.nome("Esfirra")
				.preco(new BigDecimal(2.60))
				.estoqueMinimo(new Double(2.0))
				.imagem("")
				.quantidadeEstoque(new Double(10.0))
				.visivel(Boolean.TRUE)
				.build(); 
		
		Estado estadoMinasGerais = Estado
				.builder()
				.nome("Minas Gerais")
				.build(); 
		
		Estado estadoSaoPaulo = Estado
				.builder()
				.nome("São Paulo")
				.build();
		
		Cidade primeiraCidade = Cidade
				.builder()
				.nome("Itajubá")
				.estado(estadoMinasGerais)
				.build();

		
		Cidade segundaCidade = Cidade
				.builder()
				.nome("São José dos Campos")
				.estado(estadoSaoPaulo)
				.build();
		
		Cliente primeiroCliente = Cliente
				.builder()
				.nome("Maria Dheilany")
				.email("maria.dheilany@gmail.com")
				.cpfCnpj("12345678900")
				.tipoCliente(TipoCliente.PESSOA_FISICA)
				.build();
		
		Endereco primeiroEndereco = Endereco
				.builder()
				.logradouro("Rua Flores")
				.numero("300")
				.complemento("Apartamento 301")
				.bairro("Jardim")
				.cep("12345-67")
				.cliente(primeiroCliente)
				.cidade(primeiraCidade)
				.build();
		
		Endereco segundoEndereco = Endereco
				.builder()
				.logradouro("Avenida Matos")
				.numero("105")
				.complemento("Sala 800")
				.bairro("Centro")
				.cep("89101-12")
				.cliente(primeiroCliente)
				.cidade(segundaCidade)
				.build();
		
		Pedido primeiroPedido = Pedido
				.builder()
				.instante(new Date())
				.cliente(primeiroCliente)
				.enderecoEntrega(segundoEndereco)
				.build();
		
		Pedido segundoPedido = Pedido
				.builder()
				.instante(new Date())
				.cliente(primeiroCliente)
				.enderecoEntrega(primeiroEndereco)
				.build();
		
		Pagamento primeiroPagamento = PagamentoCartao
				.builder()
				.estado(EstadoPagamento.QUITADO)
				.pedido(primeiroPedido)
				.numeroParcelas(6)
				.build();
		
		
		Pagamento segundoPagamento = PagamentoBoleto
				.builder()
				.estado(EstadoPagamento.QUITADO)
				.pedido(segundoPedido)
				.dataPagamento(new Date())
				.dataVencimento(new Date())
				.build();
		
//		================[ ASSOCIAÇÕES ]================		//
		
		primeiraCategoria.addProdutos(Arrays.asList(primeiroProduto));
		segundaCategoria.addProdutos(Arrays.asList(segundoProduto, terceiroProduto));
		
		primeiroProduto.addCategorias(Arrays.asList(primeiraCategoria));
		segundoProduto.addCategorias(Arrays.asList(segundaCategoria));
		terceiroProduto.addCategorias(Arrays.asList(segundaCategoria));
		
		estadoMinasGerais.addCidades(Arrays.asList(primeiraCidade));
		estadoSaoPaulo.addCidades(Arrays.asList(segundaCidade));

		primeiroCliente.addTelefones(Arrays.asList("1111 1111", "2222 2222"));
		primeiroCliente.addEnderecos(Arrays.asList(primeiroEndereco, segundoEndereco));
		
		primeiroPedido.setPagamento(primeiroPagamento);
		segundoPedido.setPagamento(segundoPagamento);		
		primeiroCliente.addPedidos(Arrays.asList(primeiroPedido, segundoPedido));
				
//		================[ PERSISTENCIA ]================		//
		
		categoriaRepository.saveAll(Arrays.asList(primeiraCategoria, segundaCategoria));
		produtoRepository.saveAll(Arrays.asList(primeiroProduto, segundoProduto, terceiroProduto));
		estadoRepository.saveAll(Arrays.asList(estadoMinasGerais, estadoSaoPaulo));
		cidadeRepository.saveAll(Arrays.asList(primeiraCidade, segundaCidade));
		clienteRepository.saveAll(Arrays.asList(primeiroCliente));
		enderecoRepository.saveAll(Arrays.asList(primeiroEndereco, segundoEndereco));
		pedidoRepository.saveAll(Arrays.asList(primeiroPedido, segundoPedido));
		pagamentoRepository.saveAll(Arrays.asList(primeiroPagamento, segundoPagamento));
	}
}
