package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.masai.model.DeliveryPartner;
import com.masai.services.DeliveryPartnerService;

@RestController
@RequestMapping("/delivery-partner")
public class DeliveryPartnerController {

    @Autowired
    private DeliveryPartnerService deliveryPartnerService;

    @PostMapping("/add")
    public ResponseEntity<DeliveryPartner> addDeliveryPartner(@RequestBody DeliveryPartner deliveryPartner) {
        DeliveryPartner addedPartner = deliveryPartnerService.addDeliveryPartner(deliveryPartner);
        return ResponseEntity.ok(addedPartner);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryPartner> getDeliveryPartnerById(@PathVariable Long id) {
        DeliveryPartner deliveryPartner = deliveryPartnerService.getDeliveryPartnerById(id);
        return ResponseEntity.ok(deliveryPartner);
    }
}
