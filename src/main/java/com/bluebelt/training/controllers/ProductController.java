package com.bluebelt.training.controllers;

import com.bluebelt.training.entities.Product;
import com.bluebelt.training.payload.ResponseObject;
import com.bluebelt.training.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/products")
@Tag(name = "product")
public class ProductController {

    @Autowired
    private ProductService productService;

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

    @GetMapping("")
    public ResponseEntity<?> getAll() {

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject().builder().success(true).data(productService.getAll()).build());

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject().builder().success(true).data(productService.getById(id)).build());

    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody Product product) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject().builder().success(true).data(productService.create(product)).build());

    }

//    @PatchMapping("/{id}/delete")
//    public ResponseEntity<?> delete(@PathVariable Integer id) {
//
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(new ResponseObject().builder().success(true).data(productService.delete(id)));
//
//    }

}
