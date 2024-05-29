package br.com.fiap.fastfood.adapter.inbound.controller;

import br.com.fiap.fastfood.adapter.inbound.controller.request.CreateProductRequest;
import br.com.fiap.fastfood.adapter.inbound.controller.request.UpdateProductRequest;
import br.com.fiap.fastfood.adapter.inbound.controller.response.ProductResponse;
import br.com.fiap.fastfood.adapter.inbound.controller.shared.ControllerHelper;
import br.com.fiap.fastfood.domain.domain.Product;
import br.com.fiap.fastfood.domain.ports.inbound.product.CreateProductUseCasePort;
import br.com.fiap.fastfood.domain.ports.inbound.product.DeleteProductUseCasePort;
import br.com.fiap.fastfood.domain.ports.inbound.product.GetProductsByCategoryUseCasePort;
import br.com.fiap.fastfood.domain.ports.inbound.product.UpdateProductUseCasePort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final CreateProductUseCasePort createProductUseCase;
    private final UpdateProductUseCasePort updateProductUseCase;
    private final DeleteProductUseCasePort deleteProductUseCase;
    private final GetProductsByCategoryUseCasePort getProductsByCategoryUseCase;

    public ProductController(CreateProductUseCasePort createProductUseCase, UpdateProductUseCasePort updateProductUseCase, DeleteProductUseCasePort deleteProductUseCase, GetProductsByCategoryUseCasePort getProductsByCategoryUseCase) {
        this.createProductUseCase = createProductUseCase;
        this.updateProductUseCase = updateProductUseCase;
        this.deleteProductUseCase = deleteProductUseCase;
        this.getProductsByCategoryUseCase = getProductsByCategoryUseCase;
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody CreateProductRequest request) {
        var resultProduct = request.toProduct();

        if (resultProduct.isSuccess()) {
            Product product = resultProduct.getValue().get();
            var resultCreateProduct = createProductUseCase.execute(product);

            if (resultCreateProduct.isSuccess()) {
                Product createdProduct = resultCreateProduct.getValue().get();
                var location = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(createdProduct.getId())
                        .toUri();

                return ResponseEntity.created(location).build();
            } else {
                return ControllerHelper.handleError(resultCreateProduct.getError().get());
            }
        } else {
            return ControllerHelper.handleError(resultProduct.getError().get());
        }
    }

    @PutMapping
    public ResponseEntity<?> updateProduct(@RequestBody UpdateProductRequest request) {
        var resultProduct = request.toProduct();

        if (resultProduct.isSuccess()) {
            Product product = resultProduct.getValue().get();
            var resultUpdateProduct = updateProductUseCase.execute(request.id(), product);

            if (resultUpdateProduct.isSuccess()) {
                return ResponseEntity.noContent().build();
            } else {
                return ControllerHelper.handleError(resultUpdateProduct.getError().get());
            }
        } else {
            return ControllerHelper.handleError(resultProduct.getError().get());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        var deletedProduct = deleteProductUseCase.execute(id);

        if (deletedProduct) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<?> getProductsByCategory(@RequestParam String category) {
        var resultProducts = getProductsByCategoryUseCase.execute(category);

        if (resultProducts.isSuccess()) {
            List<Product> products = resultProducts.getValue().get();
            if (products.isEmpty()) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.ok(products.stream().map(ProductResponse::fromDomain).collect(Collectors.toList()));
            }
        } else {
            return ControllerHelper.handleError(resultProducts.getError().get());
        }
    }


}
