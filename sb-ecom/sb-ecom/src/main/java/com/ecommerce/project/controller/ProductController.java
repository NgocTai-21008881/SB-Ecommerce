package com.ecommerce.project.controller;

import com.ecommerce.project.config.AppContains;
import com.ecommerce.project.payload.ProductDTO;
import com.ecommerce.project.payload.ProductResponse;
import com.ecommerce.project.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/admin/categories/{categoryId}/product")
    public ResponseEntity<ProductDTO> addProduct(@Valid @RequestBody ProductDTO productDTO,
                                                 @PathVariable Long categoryId){
        ProductDTO savedProductDTO = productService.addProduct(categoryId, productDTO);
        return new ResponseEntity<>(savedProductDTO, HttpStatus.CREATED);
    }

    @GetMapping("/public/products")
    public ResponseEntity<ProductResponse> getAllProducts(
            @RequestParam(name = "pageNumber", defaultValue = AppContains.PAGE_NUMBER, required = false) int pageNumber,
            @RequestParam(name = "pageSize",defaultValue = AppContains.PAGE_SIZE, required = false) int pageSize,
            @RequestParam(name = "sortBy",defaultValue = AppContains.SORT_PRODUCTS_BY, required = false) String sortBy,
            @RequestParam(name = "sortOrder",defaultValue = AppContains.SORT_DIR, required = false) String sortOrder
    ) {
        ProductResponse productResponse = productService.getAllProduct(pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @GetMapping("/public/categories/{categoryId}/products")
    public ResponseEntity<ProductResponse> getProductByCategory(
            @PathVariable Long categoryId,
            @RequestParam(name = "pageNumber", defaultValue = AppContains.PAGE_NUMBER, required = false) int pageNumber,
            @RequestParam(name = "pageSize",defaultValue = AppContains.PAGE_SIZE, required = false) int pageSize,
            @RequestParam(name = "sortBy",defaultValue = AppContains.SORT_PRODUCTS_BY, required = false) String sortBy,
            @RequestParam(name = "sortOrder",defaultValue = AppContains.SORT_DIR, required = false) String sortOrder) {
        ProductResponse productResponse = productService.searchByCategory(categoryId,pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @GetMapping("/public/products/keyword/{keyword}")
    public ResponseEntity<ProductResponse> getProductByKeyword(
            @PathVariable String keyword,
            @RequestParam(name = "pageNumber", defaultValue = AppContains.PAGE_NUMBER, required = false) int pageNumber,
            @RequestParam(name = "pageSize",defaultValue = AppContains.PAGE_SIZE, required = false) int pageSize,
            @RequestParam(name = "sortBy",defaultValue = AppContains.SORT_PRODUCTS_BY, required = false) String sortBy,
            @RequestParam(name = "sortOrder",defaultValue = AppContains.SORT_DIR, required = false) String sortOrder) {
        ProductResponse productResponse = productService.searchProductByKeyword(keyword,pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(productResponse, HttpStatus.FOUND);
    }

    @PutMapping("/admin/products/{productId}")
    public ResponseEntity<ProductDTO> updateProduct(@Valid @RequestBody ProductDTO productDTO,
                                                    @PathVariable Long productId) {
        ProductDTO updateProductDTO = productService.updateProduct(productId, productDTO);
        return new ResponseEntity<>(updateProductDTO, HttpStatus.OK);
    }

    @DeleteMapping("/admin/products/{productId}")
    public ResponseEntity<ProductDTO> deleteProduct(@PathVariable Long productId) {
        ProductDTO deleteProductDTO = productService.deleteProduct(productId);
        return new ResponseEntity<>(deleteProductDTO, HttpStatus.OK);
    }

    @PutMapping("/products/{productId}/image")
    public ResponseEntity<ProductDTO> updateProductImage(@PathVariable Long productId,
                                                         @RequestParam("image")MultipartFile image) throws IOException {
        ProductDTO updateProduct =  productService.updateProductImage(productId,image);
        return new ResponseEntity<>(updateProduct, HttpStatus.OK);
    }
}
