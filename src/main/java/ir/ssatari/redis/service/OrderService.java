package ir.ssatari.redis.service;


import ir.ssatari.redis.aop.TwoLayerRedisCacheable;
import ir.ssatari.redis.model.AnotherDTO;
import ir.ssatari.redis.model.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @TwoLayerRedisCacheable(firstLayerTtl = 1L, secondLayerTtl = 5L, key = "'orders_'.concat(#id).concat(#another)")
    public Order getOrder(int id, String other, String another) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Order(id, Math.round(Math.random() * 100000));
    }

    @TwoLayerRedisCacheable(firstLayerTtl = 2L, secondLayerTtl = 10L, key = "'another_'.concat(#id).concat(#another)")
    public AnotherDTO getAnother(int id, String other, String another) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new AnotherDTO(id, other, another);
    }
}
