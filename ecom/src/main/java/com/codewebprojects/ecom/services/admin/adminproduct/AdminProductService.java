package com.codewebprojects.ecom.services.admin.adminproduct;

import com.codewebprojects.ecom.dto.ProductDto;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;


public interface AdminProductService  {
    public ProductDto addProduct(ProductDto productDto) throws IOException ;
    public List<ProductDto> getAllProducts();
}