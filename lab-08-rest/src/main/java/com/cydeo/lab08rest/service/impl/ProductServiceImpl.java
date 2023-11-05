package com.cydeo.lab08rest.service.impl;

import com.cydeo.lab08rest.dto.ProductDTO;
import com.cydeo.lab08rest.entity.Product;
import com.cydeo.lab08rest.mapper.MapperUtil;
import com.cydeo.lab08rest.repository.ProductRepository;
import com.cydeo.lab08rest.service.ProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final MapperUtil mapperUtil;

    public ProductServiceImpl(ProductRepository productRepository, MapperUtil mapperUtil) {
        this.productRepository = productRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream().map(product-> mapperUtil.convert(product, new ProductDTO())).collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProductByName(String name) {
        Product product = productRepository.findFirstByName(name);
        return mapperUtil.convert(product, new ProductDTO());
    }

    @Override
    public List<ProductDTO> getTop3Product() {
        List<Product> list = productRepository.findTop3ByOrderByPriceDesc();
        return list.stream().map(product->mapperUtil.convert(product, new ProductDTO())).collect(Collectors.toList());
    }

    @Override
    public Integer countProductThanPrice(BigDecimal price) {
        return productRepository.countProductByPriceGreaterThan(price);
    }

    @Override
    public List<ProductDTO> retrieveProductListGreaterThanPriceAndLowerThanRemainingQuantity(BigDecimal price, Integer quantity) {
        List<Product> list = productRepository.retrieveProductListGreaterThanPriceAndLowerThanRemainingQuantity(price,quantity);
        return list.stream().map(product-> mapperUtil.convert(product, new ProductDTO())).collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> retrieveProductListByCategory(Long categoryId) {
        List<Product> list = productRepository.retrieveProductListByCategory(categoryId);
        return list.stream().map(product->mapperUtil.convert(product, new ProductDTO())).collect(Collectors.toList());
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
     return  mapperUtil.convert(productRepository.save(mapperUtil.convert(productDTO , new Product())), new ProductDTO());
    }

    @Override
    public List<ProductDTO> retrieveProductListByCategory(List<Long> categoryId, BigDecimal price) {
        List<Product> list = productRepository.retrieveProductListByCategory(categoryId,price);
        return list.stream().map(product-> mapperUtil.convert(product , new ProductDTO())).collect(Collectors.toList());
    }

    @Override
    public ProductDTO  updateProduct( ProductDTO productDTO) {

        Product productToSave = mapperUtil.convert(productDTO, new Product());
        productRepository.save(productToSave);
        ProductDTO updatedProduct = mapperUtil.convert(productToSave,new ProductDTO());
        updatedProduct.setId(updatedProduct.getId());
        updatedProduct.setPrice(updatedProduct.getPrice());
        updatedProduct.setQuantity(updatedProduct.getQuantity());
        updatedProduct.setRemainingQuantity(updatedProduct.getRemainingQuantity());
        updatedProduct.setName(updatedProduct.getName());

        return updatedProduct;
//      Product product = mapperUtil.convert(productDTO , new Product());
//
//      productRepository.findById(product.getId()).ifPresent(dbProduct->{
//          dbProduct.setId(product.getId());
//          dbProduct.setPrice(product.getPrice());
//          dbProduct.setQuantity(product.getQuantity());
//          dbProduct.setRemainingQuantity(product.getRemainingQuantity());
//          dbProduct.setName(product.getName());
//          dbProduct.setCategoryList(product.getCategoryList());
//
//         Product product1 = productRepository.save(dbProduct);
//
//      });

    }
}
