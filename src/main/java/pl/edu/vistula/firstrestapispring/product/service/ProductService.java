package pl.edu.vistula.firstrestapispring.product.service;

import org.springframework.stereotype.Service;
import pl.edu.vistula.firstrestapispring.product.api.request.ProductRequest;
import pl.edu.vistula.firstrestapispring.product.api.request.UpdateProductRequest;
import pl.edu.vistula.firstrestapispring.product.api.response.ProductResponse;
import pl.edu.vistula.firstrestapispring.product.domain.Product;
import pl.edu.vistula.firstrestapispring.product.repository.ProductRepository;
import pl.edu.vistula.firstrestapispring.product.support.ProductMapper;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository,
                          ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    // Creates a new product and saves to database
    public ProductResponse create(ProductRequest productRequest) {
        Product product = productRepository.save(
                productMapper.toProduct(productRequest)
        );
        return productMapper.toProductResponse(product);
    }

    // Finds one product by id
    public ProductResponse find(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(RuntimeException::new);
        return productMapper.toProductResponse(product);
    }

    // Returns all products
    public List<ProductResponse> findAll() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toProductResponse)
                .toList();
    }

    // Updates an existing product by id
    public ProductResponse update(Long id, UpdateProductRequest updateProductRequest) {
        Product product = productRepository.findById(id)
                .orElseThrow(RuntimeException::new);
        product.setName(updateProductRequest.getName());
        productRepository.save(product);
        return productMapper.toProductResponse(product);
    }

    // Deletes a product by id
    public void delete(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(RuntimeException::new);
        productRepository.deleteById(product.getId());
    }
}