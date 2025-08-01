package com.codewebprojects.ecom.controller.admin;

import com.codewebprojects.ecom.dto.ProductDto;
import com.codewebprojects.ecom.services.admin.adminproduct.AdminProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/admin")

public class AdminProductController {
    private  final AdminProductService adminProductService;

    public AdminProductController(AdminProductService adminProductService) {
        this.adminProductService = adminProductService;
    }
    @PostMapping("/product")
    public ResponseEntity<ProductDto>addProduct(@ModelAttribute ProductDto productDto) throws IOException {
     ProductDto productDto1=adminProductService.addProduct(productDto);
     return  ResponseEntity.status(HttpStatus.CREATED).body(productDto1);
    }
    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        List<ProductDto>productDtos=adminProductService.getAllProducts();
        return ResponseEntity.ok(productDtos);
    }
}
