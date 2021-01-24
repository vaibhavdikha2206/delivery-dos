package io.delivery.dos.repositories.order;

import org.springframework.data.jpa.repository.JpaRepository;

import io.delivery.dos.models.order.Orders;


public interface  OrderRepository extends JpaRepository<Orders, Integer> {

}
