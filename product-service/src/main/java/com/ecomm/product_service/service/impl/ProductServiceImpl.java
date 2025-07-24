package com.ecomm.product_service.service.impl;

import com.ecomm.product_service.Exceptions.ProductNotFoundException;
import com.ecomm.product_service.dto.InventoryDto;
import com.ecomm.product_service.dto.ProductDto;
import com.ecomm.product_service.dto.paginated.PaginatedProductDto;
import com.ecomm.product_service.dto.paginated.PaginatedProductDtoInterface;
import com.ecomm.product_service.dto.paginated.PaginatedProductsResponse;
import com.ecomm.product_service.dto.requests.ProductSaveRequest;
import com.ecomm.product_service.dto.response.ProductSaveResponse;
import com.ecomm.product_service.entity.Inventory;
import com.ecomm.product_service.entity.Product;
import com.ecomm.product_service.repository.CategoryRepo;
import com.ecomm.product_service.repository.InventoryRepo;
import com.ecomm.product_service.repository.ProductRepo;
import com.ecomm.product_service.service.ProductService;
import com.ecomm.product_service.util.Mappers.Mapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductRepo productRepo;
    private InventoryRepo inventoryRepo;
    private CategoryRepo categoryRepo;
    private Mapper mapper;

    @Override
    @Transactional
    public ProductSaveResponse saveProduct(ProductSaveRequest saveDto) { // Works
        InventoryDto inventorydto = saveDto.getInventoryDto();
        ProductDto productDto = saveDto.getProductDto();

        Inventory inventory = inventoryRepo.save(mapper.mapInventoryDtoToInventory(inventorydto));
        if (inventoryRepo.existsById(inventory.getInventoryId())){
            productDto.setInventoryId(inventory.getInventoryId());
            Product product = productRepo.save(mapper.mapProductDtoToProduct(productDto));
            return new ProductSaveResponse(
                    mapper.mapProductToProductDto(product),
                    mapper.mapInventoryToInventoryDto(inventory)
            );
        }
        return new ProductSaveResponse(null, null);
    }

    @Override
    public ProductDto getProductById(Long id) {
        Optional<Product> optionalProduct = productRepo.findById(id);
        return mapper.mapProductToProductDto(optionalProduct.orElseGet(Product::new));
    }

    @Override
    public ProductDto updateProduct(Long id, ProductDto productDto) throws ProductNotFoundException{
        Product existingProduct = productRepo.findById(id).orElseThrow(() ->
                new ProductNotFoundException("Product with ID " + id + " not found")
        );
        existingProduct.setPName(productDto.getPName());
        existingProduct.setCategory(categoryRepo.getReferenceById(productDto.getCategory()));
        existingProduct.setUpdatedTime(productDto.getUpdatedTime());
        existingProduct.setPrice(productDto.getPrice());
        existingProduct.setDescription(productDto.getDescription());

        Product updatedProduct = productRepo.save(existingProduct);
        return mapper.mapProductToProductDto(updatedProduct);
    }

    @Override
    public PaginatedProductsResponse getAllProducts(int page, int size) {
        List<PaginatedProductDtoInterface> productList = productRepo.getAllProducts(PageRequest.of(page-1, size));
        List<PaginatedProductDto> paginatedDtos = new ArrayList<>();
        for(PaginatedProductDtoInterface p: productList){
            PaginatedProductDto pto = new PaginatedProductDto(
                    p.getProductId(),
                    p.getpName(),
                    p.getPrice(),
                    p.getDescription(),
                    p.getCategory(),
                    p.getInventoryId(),
                    p.getCreatedTime(),
                    p.getUpdatedTime(),
                    p.getQuantity()
            );
            paginatedDtos.add(pto);
        }
        return new PaginatedProductsResponse(
                paginatedDtos,
                productRepo.countAllProducts()
        );
    }

    @Override
    public String deleteProductById(Long id) {
        if (productRepo.existsById(id)){
            String pName = productRepo.getReferenceById(id).getPName();
            productRepo.deleteById(id);
            return pName+"("+id+") deleted successfully.";
        }
        throw new RuntimeException("No product found for "+id+".");
    }

    @Override
    public List<ProductSaveResponse> getProductsByIds(List<Long> pIds) throws ProductNotFoundException {
        try{
            List<Product> products = productRepo.findAllByProductIdIn(pIds);
            List<ProductDto> productDtos = mapper.mapProductListToProductDtoList(products);

            List<Long> inventoryIds = new ArrayList<>();
            for (Product p: products){
                inventoryIds.add(p.getInventoryId().getInventoryId());
            }
            List<Inventory> inventories = inventoryRepo.findAllByInventoryIdIn(inventoryIds);
            List<InventoryDto> inventoryDtoList = mapper.mapInventoryListToInventoryDtoList(inventories);


//            List<ProductSaveResponse> responseList = new ArrayList<>();
//            for (int i = 0; i < productDtos.size(); i++){
//                ProductDto dto = productDtos.get(i);
//                Long inventoryId = dto.getInventoryId();
//                InventoryDto iDto = new InventoryDto();
//                for (int x = 0; x < inventoryDtoList.size(); x++){
//                    if (inventoryId.equals(inventoryDtoList.get(i).getInventoryId())){
//                        iDto = inventoryDtoList.get(i);
//                        break;
//                    }
//                }
//                ProductSaveResponse response = new ProductSaveResponse(dto,iDto);
//                responseList.add(response);
//            }

            List<ProductSaveResponse> responseList = productDtos.stream()
                    .map(dto -> {
                        Long inventoryId = dto.getInventoryId();
                        InventoryDto iDto = inventoryDtoList.stream()
                                .filter(inventory -> inventoryId.equals(inventory.getInventoryId()))
                                .findFirst()
                                .orElse(null);
                        return new ProductSaveResponse(dto, iDto);
                    })
                    .collect(Collectors.toList());

            return responseList;

        }catch (Exception e){
            throw new ProductNotFoundException("The list of Ids contains a non existing product/products");
        }
    }
}