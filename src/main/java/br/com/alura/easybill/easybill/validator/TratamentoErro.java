package br.com.alura.easybill.easybill.validator;



import br.com.alura.easybill.easybill.dto.MensagemErro;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class TratamentoErro {

    MessageSource mensagem;

    public TratamentoErro(MessageSource mensagem){
        this.mensagem = mensagem;
    }


    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<MensagemErro> trataErro(MethodArgumentNotValidException exception){

        List<MensagemErro> mensagens = new ArrayList<>();
       List<FieldError> list = exception.getBindingResult().getFieldErrors();
       list.forEach(error -> {
           String message = mensagem.getMessage(error, LocaleContextHolder.getLocale());
           MensagemErro mensagem = new MensagemErro(error.getField(), message);
           mensagens.add(mensagem);
       });
        return mensagens;
    }

}
