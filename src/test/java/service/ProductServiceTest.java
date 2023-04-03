package service;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.example.model.Product;
import org.example.repository.ProductRepository;
import org.example.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class ProductServiceTest {
    @InjectMocks
    ProductService productService;
    @Mock
    ProductRepository productRepository;

    @BeforeEach
    public void setup(){
        productService = new ProductService();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void deveSalvarUmProdutoCorretamente(){
        //given: um produto
        Product product = new Product(0, "produto teste");
        when(productRepository.buscarProduto(product.getId())).thenReturn(null);
        //when: saved
        productService.cadastrarProduto(product);
        //then: it was saved correctly
        assertEquals(product, productService.buscarProdutoPorId(0));
    }

    @Test
    public void naoDeveSalvarProdutosRepetidos(){
        //given: um produto
        Product product = new Product(0, "produto teste");
        //when: saved
        productService.cadastrarProduto(product);
        //then: nao deve ser salvo novamente
        assertThrows(IllegalArgumentException.class, ()-> {
            productService.cadastrarProduto(product);
        });
    }
    @Test
    public void deveAtualizarUmProdutoExistente(){
        //given: um produto salvo no repository
        Product productOriginal = new Product(0, "Produto Original");
        productService.cadastrarProduto(productOriginal);
        //when: esse produto for modificado
        Product productModificado = new Product(0, "Produto Modificado");
        productService.atualizaProduto(0, productModificado);
        //then: a modificação deve possuir o mesmo id
        assertEquals(productModificado, productService.buscarProdutoPorId(0));
    }
    @Test
    public void naoDeveAtualizarUmProdutoNaoExistente(){
        //given: um produto nao salvo no repository
        Product productOriginal = new Product(0, "Produto Original");
        //when: esse produto for modificado
        //then: nao devera ser salvo
        assertThrows(IllegalArgumentException.class,
                ()-> {productService.atualizaProduto(0, productOriginal);});

    }
    @Test
    public void deveRetornarListaDeProdutos(){
        Product product = new Product(0, "Nome Teste");
        Product product2 = new Product(1, "Nome Teste 1");
        productService.cadastrarProduto(product);
        productService.cadastrarProduto(product2);
        String productText = "[" + product.toString() + ", " + product2.toString() + "]";
        assertEquals(productText, productService.listarProdutos());
    }
}
