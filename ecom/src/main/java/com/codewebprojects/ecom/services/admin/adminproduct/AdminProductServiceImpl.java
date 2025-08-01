package com.codewebprojects.ecom.services.admin.adminproduct;

import com.codewebprojects.ecom.dto.ProductDto;
import com.codewebprojects.ecom.entity.Category;
import com.codewebprojects.ecom.entity.Product;
import com.codewebprojects.ecom.repository.CategoryRepository;
import com.codewebprojects.ecom.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminProductServiceImpl implements  AdminProductService{
    private  final ProductRepository productRepository;
    private  final CategoryRepository categoryRepository;

    public AdminProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    public ProductDto   addProduct(ProductDto productDto) throws IOException {
       Product product=new Product();
       product.setName(product.getName());
       product.setDescription(productDto.getDescription());
       product.setPrice(productDto.getPrice());
       product.setImg(productDto.getImg().getBytes());
        Category category=categoryRepository.findById(productDto.getCategoryId()).orElseThrow();

   product.setCategory(category);
   return  productRepository.save(product).getDto();

    }
    public List<ProductDto> getAllProducts(){
        List<Product>products=productRepository.findAll();
        return products.stream().map(Product::getDto).collect(Collectors.toList());
    }
}
