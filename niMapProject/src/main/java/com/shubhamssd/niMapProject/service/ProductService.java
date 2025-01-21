package com.shubhamssd.niMapProject.service;


import com.shubhamssd.niMapProject.entities.Product;
import com.shubhamssd.niMapProject.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }


    public void createProduct(Product product) {
        
        productRepository.save(product);
    }

    public Product getProductById(Integer id) {
        return productRepository.findById(id).orElse(null);
    }

    public boolean deleteProductById(Integer id) {
        try {
            productRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean updateProduct(Integer id, Product updatedProduct) {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isPresent()){
            Product product = productOptional.get();
            product.setProductName(updatedProduct.getProductName());
            product.setPrice(updatedProduct.getPrice());
            productRepository.save(product);
            return true;
        }
        return false;

    }
}
