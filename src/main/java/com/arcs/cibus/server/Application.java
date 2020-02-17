package com.arcs.cibus.server;

import java.math.BigDecimal;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.arcs.cibus.server.domain.Category;
import com.arcs.cibus.server.domain.Product;
import com.arcs.cibus.server.domain.Profile;
import com.arcs.cibus.server.domain.User;
import com.arcs.cibus.server.domain.View;
import com.arcs.cibus.server.repository.CategoryRepository;
import com.arcs.cibus.server.repository.ProductRepository;
import com.arcs.cibus.server.repository.ProfileRepository;
import com.arcs.cibus.server.repository.UserRepository;
import com.arcs.cibus.server.repository.ViewRepository;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ViewRepository viewRepository;
	
	@Autowired
	private ProfileRepository profileRepository;
	
	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
//		================[ INICIALIZAÇÕES ]================		//
		
		Category primeiraCategoria = Category
				.builder()
				.name("Refrigerante")
				.description("Refrigerantes")
			    .active(Boolean.TRUE)
			    .icon("")
				.build();
		
		Category segundaCategoria = Category
				.builder()
				.name("Salgado")
				.description("Salgados")
			    .active(Boolean.TRUE)
			    .icon("")
				.build();
		
		Product primeiroProduct = Product
				.builder()
				.name("Coca Cola")
				.price(new BigDecimal(2.60))
				.minimumStock(new Double(2.0))
				.image("")
				.stockQuantity(new Double(10.0))
				.visible(Boolean.TRUE)
				.build(); 
		
		Product segundoProduct = Product
				.builder()
				.name("Coxinha")
				.price(new BigDecimal(2.60))
				.minimumStock(new Double(2.0))
				.image("")
				.stockQuantity(new Double(10.0))
				.visible(Boolean.TRUE)
				.build(); 
		
		Product terceiroProduct = Product
				.builder()
				.name("Esfirra")
				.price(new BigDecimal(2.60))
				.minimumStock(new Double(2.0))
				.image("")
				.stockQuantity(new Double(10.0))
				.visible(Boolean.TRUE)
				.build();
		
		View home = View
				.builder()
				.name("Home")
				.path("/")
				.build();
		
		View user = View
				.builder()
				.name("User")
				.path("/user")
				.build();
		
		View product = View
				.builder()
				.name("Product")
				.path("/product")
				.build();
		
		View category = View
				.builder()
				.name("Category")
				.path("/category")
				.build();
		
		View profile = View
				.builder()
				.name("Profile")
				.path("/profile")
				.build();
		
		Profile perfil = Profile
				.builder()
				.name("perfilTeste")
				.build();
		
		User usuario = User
				.builder()
				.name("Alysson")
				.email("TESTE")
				.login("TESTE")
				.pass("teste")
				.get(Boolean.TRUE)
				.post(Boolean.TRUE)
				.put(Boolean.FALSE)
				.delete(Boolean.FALSE)
				.profile(perfil)
				.build();
				
				//actions
						
		
//		================[ ASSOCIAÇÕES ]================		//
		
		primeiraCategoria.addProducts(Arrays.asList(primeiroProduct));
		segundaCategoria.addProducts(Arrays.asList(segundoProduct, terceiroProduct));
		
		primeiroProduct.addCategorys(Arrays.asList(primeiraCategoria));
		segundoProduct.addCategorys(Arrays.asList(segundaCategoria));
		terceiroProduct.addCategorys(Arrays.asList(segundaCategoria));
		
		perfil.addViews(Arrays.asList(profile, category));
				
//		================[ PERSISTENCIA ]================		//
		
		categoryRepository.saveAll(Arrays.asList(primeiraCategoria, segundaCategoria));
		productRepository.saveAll(Arrays.asList(primeiroProduct, segundoProduct, terceiroProduct));
		viewRepository.saveAll(Arrays.asList(home, user, category, product, profile));
		profileRepository.saveAll(Arrays.asList(perfil));
		userRepository.saveAll(Arrays.asList(usuario));
	}
}
