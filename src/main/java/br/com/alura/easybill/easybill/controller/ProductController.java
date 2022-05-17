package br.com.alura.easybill.easybill.controller;
import br.com.alura.easybill.easybill.dto.CadastroRequest;
import br.com.alura.easybill.easybill.dto.ShowProduct;
import br.com.alura.easybill.easybill.model.Product;
import br.com.alura.easybill.easybill.validator.VerficaPrecoPromocional;
import br.com.alura.easybill.easybill.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/admin/produtos")
public class ProductController {

    private ProductRepository productRepository;
    private VerficaPrecoPromocional verificador;

    public ProductController(ProductRepository productRepository, VerficaPrecoPromocional verificador){
        this.productRepository = productRepository;
        this.verificador = verificador;
    }


    @GetMapping
    public ModelAndView produtos(){
        List<Product> lista = productRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("admin/produto");
        modelAndView.addObject("produtos", ShowProduct.toShowProductList(lista));
        return modelAndView;
    }

    @GetMapping("formulario")
    public String formulario(CadastroRequest requisicao){
        return "admin/produtos/formulario";
    }

    @PostMapping
    public String cadastro(@Valid CadastroRequest requisicao, BindingResult result){
        verificador.verifica(requisicao, result);
        if(result.hasErrors()) {
            return "admin/produtos/formulario";
        }
        Product product = requisicao.toProduto();
        productRepository.save(product);
        return "redirect:/admin/produtos/formulario";
    }

}
