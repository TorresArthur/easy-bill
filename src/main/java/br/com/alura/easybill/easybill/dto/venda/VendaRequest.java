package br.com.alura.easybill.easybill.dto.venda;

import br.com.alura.easybill.easybill.model.Cliente;
import br.com.alura.easybill.easybill.model.ItemVenda;
import br.com.alura.easybill.easybill.model.Venda;
import br.com.alura.easybill.easybill.repository.ClienteRepository;
import br.com.alura.easybill.easybill.repository.ProductRepository;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static br.com.alura.easybill.easybill.model.Status.REALIZADA;

public class VendaRequest {
    @NotNull @Positive
    private Long clienteId;
    @NotEmpty
    private List<ItemVendaRequest> itensRequest;


    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public List<ItemVendaRequest> getItens() {
        return itensRequest;
    }

    public void setItens(List<ItemVendaRequest> itensRequest) {
        this.itensRequest = itensRequest;
    }

    private Cliente retornaCliente(ClienteRepository clienteRepository){
       Cliente cliente = clienteRepository.findById(clienteId).get();
        return cliente;
    }

    private List<ItemVenda> retornaListaItemVenda(ProductRepository repository){
        List<ItemVenda> itens = new ArrayList<>();
        itensRequest.forEach(itemRequest -> {
            itens.add(itemRequest.toItemVenda(repository));
        });
            return itens;
    }

    public List<ItemVenda> toItemVenda(ClienteRepository clienteRepository, ProductRepository productRepository) {
        Venda venda = new Venda();
        venda.setCliente(retornaCliente(clienteRepository));
        venda.setDataVenda(LocalDateTime.now());
        venda.setStatus(REALIZADA);
        List<ItemVenda> itens = retornaListaItemVenda(productRepository);

        itens.forEach(item ->{
            item.setVenda(venda);
        });
        return itens;
    }
}
