package br.com.alura.easybill.easybill.controller;

import br.com.alura.easybill.easybill.dto.ProductList;
import br.com.alura.easybill.easybill.model.Product;
import br.com.alura.easybill.easybill.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api")
public class ProdutoApiController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping("produtos")
    @ResponseBody
    public List<ProductList> retornaLista(){
        List<Product> lista = productRepository.findAll();
        return ProductList.converte(lista);
    }
}
