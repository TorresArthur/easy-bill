package br.com.alura.easybill.easybill.dto.cliente;

import br.com.alura.easybill.easybill.model.Cliente;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.*;

public class ClienteRequest {

    @NotBlank
    private String nome;
    @NotBlank @CPF
    private String cpf;
    @NotBlank @Size(min = 11, max =11)
    private String telefone;
    @NotBlank @Email
    private String email;
    @NotBlank
    private String rua;
    @NotNull
    private String numero;

    private String complemento;

    @NotBlank
    private String bairro;
    @NotBlank
    private String cidade;
    @NotBlank
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

    public Cliente toCliente(){
        Cliente cliente = new Cliente();

        cliente.setCidade(cidade);
        cliente.setBairro(bairro);
        cliente.setComplemento(complemento);
        cliente.setCpf(cpf);
        cliente.setNome(nome);
        cliente.setEmail(email);
        cliente.setNumero(numero);
        cliente.setRua(rua);
        cliente.setEstado(estado);
        cliente.setTelefone(telefone);
        return cliente;
    }
}
