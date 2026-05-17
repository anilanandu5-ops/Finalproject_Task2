package pl.edu.vistula.firstrestapispring.product.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.vistula.firstrestapispring.product.api.request.ProductRequest;
import pl.edu.vistula.firstrestapispring.product.api.request.UpdateProductRequest;
import pl.edu.vistula.firstrestapispring.product.api.response.ProductResponse;
import pl.edu.vistula.firstrestapispring.product.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // POST /api/v1/products — create new product
    @PostMapping
    public ResponseEntity<ProductResponse> create(
            @RequestBody ProductRequest productRequest) {
        ProductResponse productResponse = productService.create(productRequest);
        return new ResponseEntity<>(productResponse, HttpStatus.CREATED);
    }

    // GET /api/v1/products/{id} — get one product by id
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> find(@PathVariable Long id) {
        ProductResponse productResponse = productService.find(id);
        return ResponseEntity.status(HttpStatus.OK).body(productResponse);
    }

    // GET /api/v1/products — get all products
    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll() {
        List<ProductResponse> productResponses = productService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(productResponses);
    }

    // PUT /api/v1/products/{id} — update existing product
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(
            @PathVariable Long id,
            @RequestBody UpdateProductRequest updateProductRequest) {
        ProductResponse productResponse = productService.update(id, updateProductRequest);
        return ResponseEntity.status(HttpStatus.OK).body(productResponse);
    }

    // DELETE /api/v1/products/{id} — delete product
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}