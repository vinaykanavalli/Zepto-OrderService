package org.shop.zeptoorderservice.PaymentRepository;

import org.shop.zeptoorderservice.Entity.PaymentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface PaymentRepository extends CrudRepository<PaymentEntity, Long>
{

}