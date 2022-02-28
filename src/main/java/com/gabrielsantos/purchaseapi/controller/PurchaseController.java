package com.gabrielsantos.purchaseapi.controller;

import com.gabrielsantos.purchaseapi.dto.PurchaseDTO;
import com.gabrielsantos.purchaseapi.service.PurchaseService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/purchase")
@Api(value = "Purchase-API")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PurchaseController {

    private final PurchaseService purchaseService;

    @ResponseBody
    @PostMapping(path = "/savePurchase")
    public ResponseEntity<PurchaseDTO> savePurchase(@RequestBody PurchaseDTO purchaseDTO) {
        return purchaseService.savePurchase(purchaseDTO);
    }

}
