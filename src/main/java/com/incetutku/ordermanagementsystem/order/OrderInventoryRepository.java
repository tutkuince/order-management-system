package com.incetutku.ordermanagementsystem.order;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

interface OrderInventoryRepository extends CrudRepository<OrderInventory, Long> {

    @Query(nativeQuery = true, value = "SELECT SUM(oi.total_qty_price) FROM order_inventory oi WHERE oi.order_id = ?1")
    long orderIdAmount(long orderId);
}
