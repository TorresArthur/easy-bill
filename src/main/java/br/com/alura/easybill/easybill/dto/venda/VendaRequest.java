package br.com.alura.easybill.easybill.dto.venda;

import br.com.alura.easybill.easybill.model.Cliente;
import br.com.alura.easybill.easybill.model.ItemVenda;
import br.com.alura.easybill.easybill.repository.ClienteRepository;
import br.com.alura.easybill.easybill.repository.ProductRepository;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;

public class VendaRequest {
    @NotNull @Positive
    private Long clienteId;
    @NotNull
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

    public Cliente retornaCliente(ClienteRepository clienteRepository){
       Cliente cliente = clienteRepository.findById(clienteId).get();
        return cliente;
    }

    public List<ItemVenda> retornaListaItemVenda(ProductRepository repository){
        List<ItemVenda> itens = new ArrayList<>();
        itensRequest.forEach(itemRequest -> {
            itens.add(itemRequest.toItemVenda(repository));
        });
            return itens;
    }
}
