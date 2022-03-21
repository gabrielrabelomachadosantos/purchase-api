package com.gabrielsantos.purchaseapi.controller;

import com.gabrielsantos.purchaseapi.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@Api(value = "Purchase-API")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductController {

    private final ProductService productService;

    @ResponseBody
    @GetMapping(path = "/getAvailableProducts")
    @ApiOperation(value = "Get all available products and it's id.")
    public ResponseEntity<String> getAvailableProducts() {
        return productService.getAvailableProducts();
    }

}
