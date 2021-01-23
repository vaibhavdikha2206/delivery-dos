package io.delivery.dos.repositories.vendors;

import org.springframework.data.jpa.repository.JpaRepository;

import io.delivery.dos.models.vendor.Category;

public interface CategoriesRepository extends JpaRepository<Category,String> {

}