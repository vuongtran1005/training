package com.bluebelt.training.controllers;

import com.bluebelt.training.entities.Collection;
import com.bluebelt.training.payload.ResponseObject;
import com.bluebelt.training.services.CollectionService;
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

@Slf4j
@RestController
@RequestMapping("/api/v1/collections")
@Tag(name = "collection")
@RequiredArgsConstructor
public class CollectionController {

    private final CollectionService collectionService;

    @Operation(description = "Xem danh sách danh mục", responses = {
            @ApiResponse(content = @Content(array = @ArraySchema(schema = @Schema(implementation = Collection.class))),
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
                .body(new ResponseObject().builder().success(Boolean.TRUE).data(collectionService.getAll()).build());

    }

    @Operation(description = "Lấy một danh mục theo id", responses = {
            @ApiResponse(content = @Content(array = @ArraySchema(schema = @Schema(implementation = Collection.class))),
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
                .body(new ResponseObject().builder().success(true).data(collectionService.getById(id)).build());

    }

    @Operation(description = "Tạo mới một danh mục", responses = {
            @ApiResponse(content = @Content(array = @ArraySchema(schema = @Schema(implementation = Collection.class))),
                    responseCode = "200")
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description = "Thành công"),
            @ApiResponse(responseCode  = "401", description = "Chưa xác thực"),
            @ApiResponse(responseCode  = "403", description = "Truy cập bị cấm"),
            @ApiResponse(responseCode  = "404", description = "Không tìm thấy")
    })

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody Collection collection) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject().builder().success(true).data(collectionService.create(collection)).build());

    }

}
