package br.com.alura.easybill.easybill.controller;
import br.com.alura.easybill.easybill.dto.CadastroProduto;
import br.com.alura.easybill.easybill.model.Product;
import br.com.alura.easybill.easybill.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("/admin/produtos")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public String produtos(){
        return "admin/produto";
    }
    @GetMapping("formulario")
    public String formulario(CadastroProduto requisicao){
        return "admin/produtos/formulario";
    }
    @PostMapping
    public String cadastro(@Valid CadastroProduto requisicao, BindingResult result){
        if(result.hasErrors()) {
            System.out.println("teste");
            return "admin/produtos/formulario";
        }
        Product product = requisicao.toProduto();
        productRepository.save(product);
        return "admin/produto";
    }

}
