package com.zefferx.sales.controller;

import com.zefferx.sales.dto.NewProductRequest;
import com.zefferx.sales.exceptions.ProductNotFoundException;
import com.zefferx.sales.model.Product;
import com.zefferx.sales.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
@AllArgsConstructor
public class ProductController {
    private final ProductService productServices;

    @GetMapping
    public List<Product> getProducts(){
        return productServices.getProducts();
    }


    @PostMapping
    public void addProduct (@RequestBody NewProductRequest request) {
        productServices.addProduct(request);
    }

    @DeleteMapping("/{productId}")
    public String deleteProduct(@PathVariable("productId")Integer id) throws ProductNotFoundException {
        return productServices.deleteProduct(id);
    }


    @GetMapping("/{productId}")
    public Product getProductById(@PathVariable("productId")Integer id){
        return productServices.getProductById(id);
    }

    @PutMapping("/{productId}")
    public Product updateProduct(@PathVariable Integer productId, @RequestBody NewProductRequest request){
        return productServices.updateProduct(productId, request);
    }
}