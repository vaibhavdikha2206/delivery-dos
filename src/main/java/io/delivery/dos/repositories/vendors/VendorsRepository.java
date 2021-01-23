package io.delivery.dos.repositories.vendors;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.delivery.dos.models.vendor.Vendors;

public interface VendorsRepository extends JpaRepository<Vendors,String> {

	Page<Vendors> findByCategory(String category,Pageable pageable);
}