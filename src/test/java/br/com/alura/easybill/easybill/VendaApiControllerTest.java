package br.com.alura.easybill.easybill;

import br.com.alura.easybill.easybill.dto.venda.ItemVendaRequest;
import br.com.alura.easybill.easybill.dto.venda.VendaRequest;
import br.com.alura.easybill.easybill.model.Cliente;
import br.com.alura.easybill.easybill.model.Endereco;
import br.com.alura.easybill.easybill.model.Product;
import br.com.alura.easybill.easybill.repository.ClienteRepository;
import br.com.alura.easybill.easybill.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.net.URI;

@RunWith(SpringRunner.class)
@SpringBootTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@DataJpaTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class VendaApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProductRepository productRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void deveInserirUmaNovaVenda() throws Exception {
        Cliente cliente = clienteTeste();
        VendaRequest vendaRequest = criaVendaRequest(cliente);
        String vendaRequestJson = objectMapper.writeValueAsString(vendaRequest);


        URI uri = new URI("/api/vendas");


        mockMvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(vendaRequestJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(201))
                .andExpect(MockMvcResultMatchers.header().exists("Location"));
    }

    private VendaRequest criaVendaRequest(Cliente cliente) {
        VendaRequest vendaRequest = new VendaRequest();
        vendaRequest.setClienteId(cliente.getId());

        ItemVendaRequest itemVendaRequest = new ItemVendaRequest();
        itemVendaRequest.setObservacao("alguma coisa");
        itemVendaRequest.setQunatidade(2);

        Product product = productTeste();
        itemVendaRequest.setProdutoId(product.getId());

        vendaRequest.adicionaItem(itemVendaRequest);
        return vendaRequest;
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

}