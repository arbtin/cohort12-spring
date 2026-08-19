package mil.army.moda.springexample.exampleDto;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponse createProduct(ProductRequest request) {
        Product product = new Product(request.name(), request.description(), request.price(), request.stock_number(), request.isAvailable());
        return ProductResponse.fron(productRepository.save(product));
    }

    public Optional<ProductResponse> getProductById(Long id) {
        return productRepository.findById(id).map(ProductResponse::fron);
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream()
                .map(ProductResponse::fron).toList();
    }

    public Optional<ProductResponse> replaceProduct(Long id, ProductRequest request) {
        return productRepository.findById(id).map(existing -> {
            existing.setName(request.name());
            existing.setDescription(request.description());
            existing.setPrice(request.price());
            existing.setStockNumber(request.stock_number());
            return ProductResponse.fron(productRepository.save(existing));
        });
    }

    public Optional<ProductResponse> updateProduct(Long id, ProductRequest request) {
        return productRepository.findById(id).map(existing -> {
            if (request.name() != null) existing.setName(request.name());
            if (request.description() != null) existing.setDescription(request.description());
            if (request.price() != null) existing.setPrice(request.price());
            if (request.stock_number() != null) existing.setStockNumber(request.stock_number());
            return ProductResponse.fron(productRepository.save(existing));
        });
    }

    public boolean deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            return false;
        }
        productRepository.deleteById(id);
        return true;
    }
}
