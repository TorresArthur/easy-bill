package br.com.alura.easybill.easybill.validator;

import br.com.alura.easybill.easybill.dto.CadastroRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import java.util.Objects;

@Component
public class VerficaPrecoPromocional {

    private BindingResult result;

    public void verifica(CadastroRequest cadastroRequest, BindingResult result ){

        if(Objects.isNull(cadastroRequest.getPrecoPromocional())){
            return;
        }

        Integer compare = cadastroRequest.getPreco().compareTo(cadastroRequest.getPrecoPromocional());
        if(compare == 1){
            return;
        }
        result.rejectValue("precoPromocional", "precoPromocionalMarioPrecoNormal", "");
    }


}
