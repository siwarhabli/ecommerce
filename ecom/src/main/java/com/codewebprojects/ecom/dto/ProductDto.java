package com.codewebprojects.ecom.dto;


import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
@Data
public class ProductDto {

        private Long id;
        private String name;
        private Long price ;

        private String description;

        private byte[] byteImg;
        private  Long  CategoryId;
        private MultipartFile img;
    private String base64Image;

private String categoryName;

    public byte[] getByteImg() {
        return byteImg;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }


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

    public byte[] getByteImg(byte[] img) {
        return byteImg;
    }

    public void setByteImg(byte[] byteImg) {
        this.byteImg = byteImg;
    }

    public Long getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(Long categoryId) {
        CategoryId = categoryId;
    }

    public MultipartFile getImg() {
        return img;
    }

    public void setImg(MultipartFile img) {
        this.img = img;
    }
}
