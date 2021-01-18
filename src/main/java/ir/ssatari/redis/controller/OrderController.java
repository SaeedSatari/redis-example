package ir.ssatari.redis.controller;

import ir.ssatari.redis.model.Customer;
import ir.ssatari.redis.model.Order;
import ir.ssatari.redis.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping(value = "/")
    public Order getOrder() {
        int orderNumber = 42;
        return orderService.getOrder(orderNumber);
    }

    @GetMapping(value = "/another")
    public Customer getAnother() {
        int customerNumber = 42;
        String firstName = "Saeed";
        String lastName = "Sattari";
        return orderService.getCustomer(customerNumber, firstName, lastName);
    }
}
