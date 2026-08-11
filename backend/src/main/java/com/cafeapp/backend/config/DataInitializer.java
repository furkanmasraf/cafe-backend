package com.cafeapp.backend.config;

import com.cafeapp.backend.model.MenuItem;
import com.cafeapp.backend.model.TableEntity;
import com.cafeapp.backend.repository.MenuItemRepository;
import com.cafeapp.backend.repository.TableRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final MenuItemRepository menuItemRepository;
    private final TableRepository tableRepository;

    public DataInitializer(MenuItemRepository menuItemRepository, TableRepository tableRepository) {
        this.menuItemRepository = menuItemRepository;
        this.tableRepository = tableRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (tableRepository.count() == 0) {
            for (int i = 1; i <= 10; i++) {
                tableRepository.save(new TableEntity(i, "BOŞ", 4));
            }
        }

        if (menuItemRepository.count() == 0) {
            List<MenuItem> defaultItems = Arrays.asList(
                new MenuItem(
                    "Chef Özel Burger", 
                    280.0, 
                    "180gr özel dana köftesi, karamelize soğan, cheddar peyniri, özel sos ve baharatlı patates kızartması ile.", 
                    "Burgerler", 
                    "https://images.unsplash.com/photo-1568901346375-23c9450c58cd?auto=format&fit=crop&w=600&q=80", 
                    "Dana eti, Marul, Domates, Karamelize Soğan, Cheddar, Özel Sos", 
                    true, 
                    "MUTFAK"
                ),
                new MenuItem(
                    "Tavuklu Sezar Burger", 
                    240.0, 
                    "Çıtır kaplamalı tavuk göğsü, sezar sos, marul, parmesan dilimleri.", 
                    "Burgerler", 
                    "https://images.unsplash.com/photo-1625813506062-0aeb1d7a094b?auto=format&fit=crop&w=600&q=80", 
                    "Tavuk Göğsü, Sezar Sos, Marul, Parmesan", 
                    true, 
                    "MUTFAK"
                ),
                new MenuItem(
                    "Fettuccine Alfredo", 
                    260.0, 
                    "Taze kremalı sos, sote tavuk parçaları, kültür mantarı ve taze fesleğen ile.", 
                    "Makarnalar", 
                    "https://images.unsplash.com/photo-1645112411341-6c4fd023714a?auto=format&fit=crop&w=600&q=80", 
                    "Fettuccine makarna, krema, tavuk, mantar, fesleğen", 
                    true, 
                    "MUTFAK"
                ),
                new MenuItem(
                    "Americano (Sıcak/Soğuk)", 
                    85.0, 
                    "Taze çekilmiş espresso çekirdeklerinden hazırlanan yoğun aromalı filtre kahve.", 
                    "İçecekler", 
                    "https://images.unsplash.com/photo-1514432324607-a09d9b4aefdd?auto=format&fit=crop&w=600&q=80", 
                    "Espresso, Su", 
                    true, 
                    "BAR"
                ),
                new MenuItem(
                    "Flat White", 
                    105.0, 
                    "Çift shot espresso ve kadifemsi buharlaştırılmış süt dokusu.", 
                    "İçecekler", 
                    "https://images.unsplash.com/photo-1577968897966-3d4325b36b61?auto=format&fit=crop&w=600&q=80", 
                    "Espresso, Tam Yağlı Süt", 
                    true, 
                    "BAR"
                ),
                new MenuItem(
                    "Ev Yapımı Çilekli Limonata", 
                    95.0, 
                    "Taze sıkılmış limon suyu, çilek püresi ve taze nane yaprakları ile.", 
                    "İçecekler", 
                    "https://images.unsplash.com/photo-1513558161293-cdaf765ed2fd?auto=format&fit=crop&w=600&q=80", 
                    "Limon, Çilek Püresi, Nane, Buz", 
                    true, 
                    "BAR"
                ),
                new MenuItem(
                    "San Sebastian Cheesecake", 
                    145.0, 
                    "Akışkan kremamsı kıvamı ve karamelize üst dokusu ile yanında sıcak Belçika çikolatası.", 
                    "Tatlılar", 
                    "https://images.unsplash.com/photo-1533134242443-d4fd215305ad?auto=format&fit=crop&w=600&q=80", 
                    "Krem peynir, yumurta, krema, Belçika çikolatası", 
                    true, 
                    "PASTA"
                ),
                new MenuItem(
                    "Tiramisu Originale", 
                    135.0, 
                    "Espresso ile ıslatılmış kedi dili bisküviler ve kat kat leziz mascarpone kreması.", 
                    "Tatlılar", 
                    "https://images.unsplash.com/photo-1571877227200-a0d98ea607e9?auto=format&fit=crop&w=600&q=80", 
                    "Mascarpone, Kedi Dili, Espresso, Kakao", 
                    true, 
                    "PASTA"
                )
            );
            menuItemRepository.saveAll(defaultItems);
        }
    }
}
