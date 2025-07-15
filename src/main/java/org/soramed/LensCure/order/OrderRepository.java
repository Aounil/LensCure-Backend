package org.soramed.LensCure.order;

import org.soramed.LensCure.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {


    List<Order> findOrderByUserId(int userID);
}