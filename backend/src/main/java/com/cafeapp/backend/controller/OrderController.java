package com.cafeapp.backend.controller;

import com.cafeapp.backend.model.OrderEntity;
import com.cafeapp.backend.repository.OrderRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin("*")
public class OrderController {

    private final OrderRepository orderRepo;

    public OrderController(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

    @PostMapping
    public OrderEntity createOrder(@RequestBody OrderEntity order) {
        return orderRepo.save(order);
    }

    @GetMapping
    public List<OrderEntity> getAllOrders() {
        return orderRepo.findAll();
    }

    @PutMapping("/{id}/pay")
    public OrderEntity payOrder(@PathVariable Long id) {
        OrderEntity order = orderRepo.findById(id).orElseThrow();
        order.setPaid(true);
        return orderRepo.save(order);
    }
}
