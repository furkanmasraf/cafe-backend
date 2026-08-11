package com.cafeapp.backend.controller;

import com.cafeapp.backend.model.OrderEntity;
import com.cafeapp.backend.model.OrderItem;
import com.cafeapp.backend.model.TableEntity;
import com.cafeapp.backend.repository.MenuItemRepository;
import com.cafeapp.backend.repository.OrderRepository;
import com.cafeapp.backend.repository.TableRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/analytics")
@CrossOrigin(origins = "*")
public class AnalyticsController {

    private final OrderRepository orderRepository;
    private final TableRepository tableRepository;
    private final MenuItemRepository menuItemRepository;

    public AnalyticsController(OrderRepository orderRepository, TableRepository tableRepository, MenuItemRepository menuItemRepository) {
        this.orderRepository = orderRepository;
        this.tableRepository = tableRepository;
        this.menuItemRepository = menuItemRepository;
    }

    @GetMapping("/summary")
    public ResponseEntity<Map<String, Object>> getAnalyticsSummary() {
        List<OrderEntity> allOrders = orderRepository.findAll();
        List<TableEntity> allTables = tableRepository.findAll();

        double totalCiro = 0.0;
        int completedOrdersCount = 0;
        Map<String, Integer> productSales = new HashMap<>();

        for (OrderEntity order : allOrders) {
            if (order.isPaid()) {
                totalCiro += order.getTotalPrice();
                completedOrdersCount++;
            }
            if (order.getItems() != null) {
                for (OrderItem item : order.getItems()) {
                    String name = item.getMenuItemName() != null ? item.getMenuItemName() : "Bilinmeyen Ürün";
                    productSales.put(name, productSales.getOrDefault(name, 0) + item.getQuantity());
                }
            }
        }

        long activeTablesCount = allTables.stream()
                .filter(t -> !"BOŞ".equalsIgnoreCase(t.getDurum()))
                .count();

        // Convert productSales map to a sorted list of top selling items
        List<Map<String, Object>> topProducts = new ArrayList<>();
        productSales.entrySet().stream()
                .sorted((e1, e2) -> Integer.compare(e2.getValue(), e1.getValue()))
                .limit(5)
                .forEach(e -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("name", e.getKey());
                    map.put("salesCount", e.getValue());
                    topProducts.add(map);
                });

        Map<String, Object> response = new HashMap<>();
        response.put("totalCiro", totalCiro);
        response.put("completedOrdersCount", completedOrdersCount);
        response.put("totalOrdersCount", allOrders.size());
        response.put("totalTablesCount", allTables.size());
        response.put("activeTablesCount", activeTablesCount);
        response.put("topProducts", topProducts);

        return ResponseEntity.ok(response);
    }
}
