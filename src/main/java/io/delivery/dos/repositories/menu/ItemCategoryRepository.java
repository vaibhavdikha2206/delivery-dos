package io.delivery.dos.repositories.menu;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.delivery.dos.models.menu.ItemCategories;

public interface ItemCategoryRepository extends JpaRepository<ItemCategories, String> {
	
	@Query(value="select Distinct(itemcategory) from Menu where vendorid = :id",nativeQuery = true)
	List<ItemCategories> findDistinctItemcategoryByVendorid(@Param("id")int id);
}