package com.cafeapp.backend.controller;

import com.cafeapp.backend.model.OrderEntity;
import com.cafeapp.backend.model.OrderItem;
import com.cafeapp.backend.model.TableEntity;
import com.cafeapp.backend.repository.OrderRepository;
import com.cafeapp.backend.repository.TableRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "*")
public class OrderController {

    private final OrderRepository orderRepository;
    private final TableRepository tableRepository;

    public OrderController(OrderRepository orderRepository, TableRepository tableRepository) {
        this.orderRepository = orderRepository;
        this.tableRepository = tableRepository;
    }

    @GetMapping
    public List<OrderEntity> getAllOrders() {
        return orderRepository.findAll();
    }

    @GetMapping("/active")
    public List<OrderEntity> getActiveOrders() {
        return orderRepository.findByStatusNot("COMPLETED");
    }

    @GetMapping("/table/{tableNumber}")
    public List<OrderEntity> getOrdersByTable(@PathVariable int tableNumber) {
        return orderRepository.findByTableNumber(tableNumber);
    }

    @PostMapping
    public ResponseEntity<OrderEntity> createOrder(@RequestBody OrderEntity newOrder) {
        if (newOrder.getItems() == null || newOrder.getItems().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        // Calculate total price
        double computedTotal = 0.0;
        for (OrderItem item : newOrder.getItems()) {
            computedTotal += item.getUnitPrice() * item.getQuantity();
        }
        newOrder.setTotalPrice(computedTotal);
        newOrder.setCreatedAt(LocalDateTime.now());
        newOrder.setStatus("PENDING");
        newOrder.setPaid(false);
        newOrder.setPaymentMethod("UNPAID");

        OrderEntity savedOrder = orderRepository.save(newOrder);

        // Update table status to DOLU if table exists
        Optional<TableEntity> tableOpt = tableRepository.findByMasaNo(newOrder.getTableNumber());
        if (tableOpt.isPresent()) {
            TableEntity table = tableOpt.get();
            table.setDurum("DOLU");
            tableRepository.save(table);
        }

        return ResponseEntity.ok(savedOrder);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<OrderEntity> updateOrderStatus(@PathVariable Long id, @RequestBody Map<String, String> payload) {
        String newStatus = payload.get("status");
        if (newStatus == null) {
            return ResponseEntity.badRequest().build();
        }

        return orderRepository.findById(id).map(order -> {
            order.setStatus(newStatus);
            if ("COMPLETED".equalsIgnoreCase(newStatus)) {
                order.setPaid(true);
            }
            return ResponseEntity.ok(orderRepository.save(order));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/table/{tableNumber}/pay")
    public ResponseEntity<Map<String, Object>> payTableOrders(
            @PathVariable int tableNumber,
            @RequestBody Map<String, String> payload) {
        
        String method = payload.getOrDefault("paymentMethod", "CARD");
        List<OrderEntity> unpaidOrders = orderRepository.findByTableNumberAndPaid(tableNumber, false);

        double totalAmountPaid = 0.0;
        for (OrderEntity order : unpaidOrders) {
            order.setPaid(true);
            order.setPaymentMethod(method);
            order.setStatus("COMPLETED");
            orderRepository.save(order);
            totalAmountPaid += order.getTotalPrice();
        }

        // Reset table status to BOŞ
        Optional<TableEntity> tableOpt = tableRepository.findByMasaNo(tableNumber);
        if (tableOpt.isPresent()) {
            TableEntity table = tableOpt.get();
            table.setDurum("BOŞ");
            tableRepository.save(table);
        }

        return ResponseEntity.ok(Map.of(
            "message", "Masa " + tableNumber + " ödemesi başarıyla tamamlandı.",
            "totalPaid", totalAmountPaid,
            "ordersCount", unpaidOrders.size()
        ));
    }
}
