package me.aluga.inventory.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import me.aluga.inventory.dto.Search;
import me.aluga.inventory.entity.Produto;
import me.aluga.inventory.repository.ProdutoRepository;
import me.aluga.inventory.services.ProdutoService;
import me.aluga.inventory.services.impl.ProdutoServiceImpl;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
    ProdutoRepository.class
})
public class ProdutoServiceImplTest {

    @MockBean
    private ProdutoRepository produtoRepository;

    private ProdutoService produtoService;

    @BeforeEach
    void beforeEach() {
       this.produtoService = new ProdutoServiceImpl(produtoRepository);
    }
    @Test
    void test(){
        System.out.println("teste");
    }
    @Test
    void whenSearchReturnsEmpty(){
        // ARRANGE
        Mockito.when(produtoRepository.findByName(Mockito.anyString())).thenReturn(Collections.emptyList());
        Search search = new Search();
        search.setNome("");
        // ACT
        List<Produto> produtosReturn = produtoService.search(search);
        // ASSERT
        assertEquals(  produtosReturn.isEmpty(), true);
    }

    @Test
    void whenSearchReturns(){
        // ARRANGE
        List<Produto> produtosMock = new ArrayList<Produto>();
        Produto produtoMock = new Produto();
        produtosMock.add(produtoMock);
        Mockito.when(produtoRepository.findByName(Mockito.anyString())).thenReturn(produtosMock);
        Search search = new Search();
        search.setNome("");
        // ACT
        List<Produto> produtosReturn = produtoService.search(search);
        // ASSERT
        assertEquals(  produtosReturn.isEmpty(), false);
    }

    @Test
    void whenAddReturn(){
        // ARRANGE
        Produto produtoMock = new Produto();
        produtoMock.setNome("teste");
        produtoMock.setQuantity(50);
        produtoMock.setTipo("tipo");
        produtoMock.setDescription("desc");
        Mockito.when(produtoRepository.save(produtoMock)).thenReturn(produtoMock);
        // ACT
        Produto produtosReturn = produtoService.save(produtoMock);
        // ASSERT
        assertEquals(produtoMock, produtosReturn);
    }
}
