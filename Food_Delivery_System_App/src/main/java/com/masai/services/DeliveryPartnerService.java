package com.masai.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.masai.model.DeliveryPartner;
import com.masai.repo.DeliveryPartnerRepository;
import com.masai.exception.NotFoundException;

@Service
public class DeliveryPartnerService {

    @Autowired
    private DeliveryPartnerRepository deliveryPartnerRepository;

    public DeliveryPartner addDeliveryPartner(DeliveryPartner deliveryPartner) {
        return deliveryPartnerRepository.save(deliveryPartner);
    }

    public DeliveryPartner getDeliveryPartnerById(Long id) {
        return deliveryPartnerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Delivery Partner not found with ID: " + id));
    }
}
