package com.bluebelt.training.controllers;

import com.bluebelt.training.entities.Product;
import com.bluebelt.training.payload.ResponseObject;
import com.bluebelt.training.pojos.ProductRequest;
import com.bluebelt.training.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.bluebelt.training.utils.AppConstrants.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/products")
@Tag(name = "product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @Operation(description = "Xem danh sách sản phẩm", responses = {
            @ApiResponse(content = @Content(array = @ArraySchema(schema = @Schema(implementation = Product.class))),
                    responseCode = "200")
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description = "Thành công"),
            @ApiResponse(responseCode  = "401", description = "Chưa xác thực"),
            @ApiResponse(responseCode  = "403", description = "Truy cập bị cấm"),
            @ApiResponse(responseCode  = "404", description = "Không tìm thấy")
    })

    @GetMapping
    public ResponseEntity<?> getAll(
            @RequestParam(value = "page", defaultValue = DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "per_page", defaultValue = DEFAULT_PER_PAGE) int per_page,
            @RequestParam(value = "sort", defaultValue = DEFAULT_SORT) String sort) {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject().builder().success(Boolean.TRUE)
                .data(productService.getAll(page, per_page, sort)).build());
    }

    @Operation(description = "Lấy một sản phẩm theo id", responses = {
            @ApiResponse(content = @Content(array = @ArraySchema(schema = @Schema(implementation = Product.class))),
                    responseCode = "200")
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description = "Thành công"),
            @ApiResponse(responseCode  = "401", description = "Chưa xác thực"),
            @ApiResponse(responseCode  = "403", description = "Truy cập bị cấm"),
            @ApiResponse(responseCode  = "404", description = "Không tìm thấy")
    })

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject().builder().success(Boolean.TRUE).data(productService.getById(id)).build());
    }

    @Operation(description = "Tạo mới một sản phẩm", responses = {
            @ApiResponse(content = @Content(array = @ArraySchema(schema = @Schema(implementation = Product.class))),
                    responseCode = "200")
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description = "Thành công"),
            @ApiResponse(responseCode  = "401", description = "Chưa xác thực"),
            @ApiResponse(responseCode  = "403", description = "Truy cập bị cấm"),
            @ApiResponse(responseCode  = "404", description = "Không tìm thấy")
    })

    @PostMapping("")
    public ResponseEntity<?> add(@RequestBody ProductRequest productRequest) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject().builder().success(Boolean.TRUE).data(productService.add(productRequest)).build());

    }

    @Operation(description = "Cập nhật một sản phẩm", responses = {
            @ApiResponse(content = @Content(array = @ArraySchema(schema = @Schema(implementation = Product.class))),
                    responseCode = "200")
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description = "Thành công"),
            @ApiResponse(responseCode  = "401", description = "Chưa xác thực"),
            @ApiResponse(responseCode  = "403", description = "Truy cập bị cấm"),
            @ApiResponse(responseCode  = "404", description = "Không tìm thấy")
    })

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody ProductRequest productRequest) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject().builder().success(Boolean.TRUE).data(productService.update(id, productRequest)).build());
    }



}
