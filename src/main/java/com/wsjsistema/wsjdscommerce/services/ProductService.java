package com.wsjsistema.wsjdscommerce.services;

import com.wsjsistema.wsjdscommerce.dto.ProductDTO;
import com.wsjsistema.wsjdscommerce.entities.Product;
import com.wsjsistema.wsjdscommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {


    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public ProductDTO findByIDto(Long id ){
        Optional<Product> result = repository.findById(id);
        Product product = result.get();
        ProductDTO dto = new ProductDTO(product);
        return  dto;

    }
//Buscando todos sem ser paginados
//    @Transactional(readOnly = true)
//    public List<ProductDTO>  findAll() {
//        List<Product> result = repository.findAll();
//        return  result.stream().map(x -> new ProductDTO(x)).toList();
@Transactional(readOnly = true)
public Page<ProductDTO> findAll(Pageable pageable) {
    Page<Product> result = repository.findAll(pageable);
    return result.map((x -> new ProductDTO(x)));
}
}
