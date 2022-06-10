package br.com.alura.easybill.easybill;

import br.com.alura.easybill.easybill.dto.venda.VendasPorProdutoProjection;
import br.com.alura.easybill.easybill.model.*;
import br.com.alura.easybill.easybill.repository.ClienteRepository;
import br.com.alura.easybill.easybill.repository.ItemVendaRepository;
import br.com.alura.easybill.easybill.repository.ProductRepository;
import br.com.alura.easybill.easybill.repository.VendaRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class VendaPorProdutoTest {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ItemVendaRepository itemVendaRepository;

    public VendaPorProdutoTest(){

    }

    @Test
    public void deveriaRetornarProdutoComNomeCerto() {

        itemVendaRepository.save(itemVendaTeste1());
        List<VendasPorProdutoProjection> itens = itemVendaRepository.findProdutoPorQuantidade();
        itens.forEach(item -> Assert.assertEquals("Televisão", item.getNomeProduto()));
    }

    @Test
    public void deveriaRetornarAQuantidadeCertaDoProduto(){
        itemVendaRepository.save(itemVendaTeste1());
        itemVendaRepository.save(itemVendaTeste2());
        List<VendasPorProdutoProjection> itens = itemVendaRepository.findProdutoPorQuantidade();
        itens.get(0).getQuantidadeProduto();
        Assert.assertEquals(13L, itens.get(0).getQuantidadeProduto().longValue());
    }


    private Product productTeste(){
        Product product = new Product();
        product.setUrlImagem("alguma url");
        product.setClasseFiscal("2222.22.22");
        product.setPreco(new BigDecimal("10000"));
        product.setNome("Televisão");
        product.setDescricao("SmarTV");
        productRepository.save(product);
        return product;
    }

    private Product productTeste2(){
        Product product2 = new Product();
        product2.setUrlImagem("alguma url");
        product2.setClasseFiscal("2222.22.22");
        product2.setPreco(new BigDecimal("10000"));
        product2.setNome("Televisão");
        product2.setDescricao("SmarTV");
        productRepository.save(product2);
        return product2;
    }

    private Endereco enderecoTeste(){
        Endereco endereco = new Endereco();
        endereco.setCidade("Goiânia");
        endereco.setBairro("Centro");
        endereco.setComplemento("numero 80");
        endereco.setEstado("Goiás");
        endereco.setRua("Rua 2");
        return endereco;
    }

    private Cliente clienteTeste(){
        Cliente cliente = new Cliente();
        cliente.setEndereco(enderecoTeste());
        cliente.setEmail("aluno@email.com");
        cliente.setTelefone("982245519");
        cliente.setNome("Arthur");
        cliente.setCpf("05606350106");
        clienteRepository.save(cliente);
        return cliente;
    }

    private Venda vendaTeste(){
        Venda venda = new Venda();
        venda.setCliente(clienteTeste());
        venda.setStatus(Status.REALIZADA);
        venda.setDataVenda(LocalDateTime.now());
        vendaRepository.save(venda);
        return venda;
    }

    private ItemVenda itemVendaTeste1(){
        ItemVenda itemVenda = new ItemVenda();
        itemVenda.setVenda(vendaTeste());
        itemVenda.setQuantidade(5);
        itemVenda.setObservacao("Alguma observação");
        itemVenda.setPrecoUnitario(null);
        itemVenda.setProduct(productTeste());
        return itemVenda;
    }

    private ItemVenda itemVendaTeste2(){
        ItemVenda itemVenda = new ItemVenda();
        itemVenda.setVenda(vendaTeste());
        itemVenda.setQuantidade(8);
        itemVenda.setObservacao("Alguma observação 2");
        itemVenda.setPrecoUnitario(null);
        itemVenda.setProduct(productTeste2());
        return itemVenda;
    }

}
