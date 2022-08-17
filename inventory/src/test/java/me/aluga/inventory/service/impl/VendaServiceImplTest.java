package me.aluga.inventory.service.impl;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;
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
import me.aluga.inventory.entity.Cliente;
import me.aluga.inventory.entity.Produto;
import me.aluga.inventory.entity.Venda;
import me.aluga.inventory.repository.VendaRepository;
import me.aluga.inventory.services.ClienteService;
import me.aluga.inventory.services.ProdutoService;
import me.aluga.inventory.services.VendaService;
import me.aluga.inventory.services.impl.VendaServiceImpl;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
    VendaRepository.class,  ClienteService.class , ProdutoService.class 

})
public class VendaServiceImplTest {
    @MockBean
    private VendaRepository vendaRepository;
    
    @MockBean
    private ClienteService clienteService;

    final static long tempoDevolucaoInMillisseconds = 7 * 24 * 60 * 60 * 1000;

    @MockBean
    private ProdutoService produtoService;

    private VendaService vendaService;
    
    @BeforeEach
    void beforeEach() {        
       this.vendaService = new VendaServiceImpl( vendaRepository, clienteService, produtoService);
    }

    @Test
    void test(){
        System.out.println("teste");
    }
    @Test
    void whenSaveReturn(){
        // ARRANGE
        Venda venda = new Venda();
        Produto produto = new Produto();
        Cliente cliente = new Cliente();
        cliente.setId(1);
        venda.setCliente(cliente);
        venda.getProdutos().add(produto);
        produto.setId(2);
        produto.setQuantity(50);
        Mockito.when(clienteService.findById(venda.getCliente().getId())).thenReturn(Optional.of(cliente));
        Search search = new Search();
        search.setNome("");
        List<Integer> ids = venda.getProdutos().stream().map(x -> x.getId()).collect(Collectors.toList());
        List<Produto> produtosMock = Collections.singletonList(produto);
        Mockito.when( produtoService.findByIds(ids)).thenReturn(produtosMock);
        Mockito.when(vendaRepository.save(venda)).thenReturn(venda);
        // ACT
        Venda vendaReturn = vendaService.save(venda);
        // ASSERT
        assertEquals(  vendaReturn, venda);
    }

}
