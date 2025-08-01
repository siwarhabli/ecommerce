package com.codewebprojects.ecom.entity;

import com.codewebprojects.ecom.dto.ProductDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Base64;

@Entity
@Data
@Table(name="product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long price ;
    @Lob
    private String description;
    @Lob

    @Column(columnDefinition = "longblob")
    private byte[] img;


    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name="category_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Category category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    public ProductDto getDto(){
        ProductDto productDto=new ProductDto()
                ;
        productDto.setId(id);
        productDto.setName(name);

        productDto.setPrice(price);
        productDto.setDescription(description);
        productDto.setByteImg(img);
        if (img != null) {
            String base64 = Base64.getEncoder().encodeToString(img);
            productDto.setBase64Image(base64);
        } else {
            productDto.setBase64Image(null);
        }
        productDto.setCategoryId(category.getId());
        productDto.setCategoryName(category.getName());
        return  productDto;

    }
}
