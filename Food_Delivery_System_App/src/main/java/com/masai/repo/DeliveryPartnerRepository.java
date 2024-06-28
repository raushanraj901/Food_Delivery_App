package com.masai.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.DeliveryPartner;

public interface DeliveryPartnerRepository extends JpaRepository<DeliveryPartner, Long> {
    
}
