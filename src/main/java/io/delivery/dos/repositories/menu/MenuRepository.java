package io.delivery.dos.repositories.menu;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.delivery.dos.models.menu.ItemCategories;
import io.delivery.dos.models.menu.Menu;

public interface MenuRepository extends JpaRepository<Menu, Integer> {
	
	List<Menu> findByVendorid(int vendorid);
	Menu findByVendoridAndItemid(int vendorid,int itemid);
	
}