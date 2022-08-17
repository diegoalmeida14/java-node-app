package me.aluga.inventory.service.impl;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import me.aluga.inventory.dto.Search;
import me.aluga.inventory.entity.Aluguel;
import me.aluga.inventory.entity.Cliente;
import me.aluga.inventory.entity.Produto;
import me.aluga.inventory.repository.AluguelRepository;
import me.aluga.inventory.services.AluguelService;
import me.aluga.inventory.services.ClienteService;
import me.aluga.inventory.services.ProdutoService;
import me.aluga.inventory.services.impl.AluguelServiceImpl;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
    AluguelRepository.class,  ClienteService.class , ProdutoService.class 

})
public class AluguelServiceImplTest {
    @MockBean
    private AluguelRepository aluguelRepository;
    
    @MockBean
    private ClienteService clienteService;

    final static long tempoDevolucaoInMillisseconds = 7 * 24 * 60 * 60 * 1000;

    @MockBean
    private ProdutoService produtoService;

    private AluguelService aluguelService;
    
    @BeforeEach
    void beforeEach() {        
       this.aluguelService = new AluguelServiceImpl( aluguelRepository, clienteService, produtoService);
    }

    @Test
    void test(){
        System.out.println("teste");
    }
    @Test
    void whenSaveReturn(){
        // ARRANGE
        Aluguel aluguel = new Aluguel();
        Produto produto = new Produto();
        Cliente cliente = new Cliente();
        cliente.setId(1);
        aluguel.setCliente(cliente);
        aluguel.getProdutos().add(produto);
        produto.setId(2);
        produto.setQuantity(50);
        Mockito.when(clienteService.findById(aluguel.getCliente().getId())).thenReturn(Optional.of(cliente));
        Search search = new Search();
        search.setNome("");
        List<Integer> ids = aluguel.getProdutos().stream().map(x -> x.getId()).collect(Collectors.toList());
        List<Produto> produtosMock = Collections.singletonList(produto);
        Mockito.when( produtoService.findByIds(ids)).thenReturn(produtosMock);
        aluguel.setDtIncio(new Date().getTime());
        aluguel.setDtVencimento(new Date().getTime() + tempoDevolucaoInMillisseconds);
        Mockito.when(aluguelRepository.save(aluguel)).thenReturn(aluguel);
        // ACT
        Aluguel aluguelReturn = aluguelService.save(aluguel);
        // ASSERT
        assertEquals(  aluguelReturn, aluguel);
    }

}
