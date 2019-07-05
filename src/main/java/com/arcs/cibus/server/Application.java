package com.arcs.cibus.server;

import java.math.BigDecimal;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.arcs.cibus.server.domain.Categoria;
import com.arcs.cibus.server.domain.Cidade;
import com.arcs.cibus.server.domain.Estado;
import com.arcs.cibus.server.domain.Produto;
import com.arcs.cibus.server.repository.CategoriaRepository;
import com.arcs.cibus.server.repository.CidadeRepository;
import com.arcs.cibus.server.repository.EstadoRepository;
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
		
//		================[ ASSOCIAÇÕES ]================		//
		
		primeiraCategoria.addProdutos(Arrays.asList(primeiroProduto));
		segundaCategoria.addProdutos(Arrays.asList(segundoProduto, terceiroProduto));
		
		primeiroProduto.addCategorias(Arrays.asList(primeiraCategoria));
		segundoProduto.addCategorias(Arrays.asList(segundaCategoria));
		terceiroProduto.addCategorias(Arrays.asList(segundaCategoria));
		
		estadoMinasGerais.addCidades(Arrays.asList(primeiraCidade));
		estadoSaoPaulo.addCidades(Arrays.asList(segundaCidade));
				
//		================[ PERSISTENCIA ]================		//
		
		categoriaRepository.saveAll(Arrays.asList(primeiraCategoria, segundaCategoria));
		produtoRepository.saveAll(Arrays.asList(primeiroProduto, segundoProduto, terceiroProduto));
		estadoRepository.saveAll(Arrays.asList(estadoMinasGerais, estadoSaoPaulo));
		cidadeRepository.saveAll(Arrays.asList(primeiraCidade, segundaCidade));
	}
}
