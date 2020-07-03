package com.reven.onlinestore.common.repository;

import com.reven.onlinestore.common.model.CustomerActivity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerActivityRepository extends JpaRepository<CustomerActivity, Long> {
}
