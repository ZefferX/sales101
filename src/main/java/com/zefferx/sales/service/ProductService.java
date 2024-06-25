package com.zefferx.sales.service;

import com.zefferx.sales.dto.NewProductRequest;
import com.zefferx.sales.exceptions.ProductNotFoundException;
import com.zefferx.sales.model.Product;
import com.zefferx.sales.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor


public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    public void addProduct (NewProductRequest request) {
        Product cambio = new Product();
        cambio.setName(request.name());
        cambio.setPrice(request.price());
        cambio.setQuantity(request.quantity());

        productRepository.save(cambio);
    }

    public String deleteProduct(Integer id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            productRepository.deleteById(id);
            return "Producto eliminado con éxito";
        } else {
            return "Producto con ID " + id + " no encontrado";
        }

    }

    public Product getProductById(Integer id){
        Optional < Product> datos = productRepository.findById(id);
        if (datos.isEmpty())throw new ProductNotFoundException("No encontrado");
        return datos.get();
    }

    public Product updateProduct(Integer productId, NewProductRequest request){
        Product cambio = productRepository.findById(productId).get();
        cambio.setName(request.name());
        cambio.setPrice(request.price());
        cambio.setQuantity(request.quantity());

        return productRepository.save(cambio);
    }

    public Product updateProductToInternalUse(Product product){

        return productRepository.save(product);
    }
}
