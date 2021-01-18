package ir.ssatari.redis.service;

import ir.ssatari.redis.aop.TwoLayerRedisCacheable;
import ir.ssatari.redis.model.Customer;
import ir.ssatari.redis.model.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @TwoLayerRedisCacheable(firstLayerTtl = 1L, secondLayerTtl = 5L, key = "'orders_'.concat(#id).concat(#customer)")
    public Order getOrder(int id) {
        //in reality this call is really expensive and error-prone - trust me!
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Order(id, Math.round(Math.random() * 100000));
    }

    @TwoLayerRedisCacheable(firstLayerTtl = 2L, secondLayerTtl = 10L, key = "'customer_'.concat(#id).concat(#customer)")
    public Customer getCustomer(int id, String firstName, String lastName) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Customer(id, firstName, lastName);
    }
}