package br.com.alura.easybill.easybill.dto.cliente;

import br.com.alura.easybill.easybill.model.Cliente;


import java.util.ArrayList;
import java.util.List;

public class ClienteResponse {
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private String rua;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void fromCliente(Cliente cliente){
        setBairro(cliente.getBairro());
        setCidade(cliente.getCidade());
        setComplemento(cliente.getComplemento());
        setCpf(cliente.getCpf());
        setEstado(cliente.getEstado());
        setEmail(cliente.getEmail());
        setNome(cliente.getNome());
        setNumero(cliente.getNumero());
        setRua(cliente.getRua());
        setTelefone(cliente.getTelefone());

    }

    public List<ClienteResponse> fromListaCliente(List<Cliente> listaClientes){
        List<ClienteResponse> listaResponse = new ArrayList<>();
        listaClientes.forEach(cliente -> {
            ClienteResponse response = new ClienteResponse();
            response.setBairro(cliente.getBairro());
            response.setCidade(cliente.getCidade());
            response.setComplemento(cliente.getComplemento());
            response.setCpf(cliente.getCpf());
            response.setEstado(cliente.getEstado());
            response.setEmail(cliente.getEmail());
            response.setNome(cliente.getNome());
            response.setNumero(cliente.getNumero());
            response.setRua(cliente.getRua());
            response.setTelefone(cliente.getTelefone());
            listaResponse.add(response);
        });
        return listaResponse;
    }
}
