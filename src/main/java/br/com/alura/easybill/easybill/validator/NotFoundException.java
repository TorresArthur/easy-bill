package br.com.alura.easybill.easybill.validator;

public class NotFoundException extends RuntimeException{

    public NotFoundException(String mensagem){
        super(mensagem);
    }
}
