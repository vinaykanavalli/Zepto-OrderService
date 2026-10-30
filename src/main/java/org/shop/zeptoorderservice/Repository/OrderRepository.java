package org.shop.zeptoorderservice.Repository;

import org.shop.zeptoorderservice.Entity.OrderEntity;
import org.springframework.data.repository.CrudRepository;



public interface OrderRepository extends CrudRepository<OrderEntity, Integer>
{

}