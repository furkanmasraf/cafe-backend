package com.cafeapp.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("tableNumber")
    private int masaNo;

    @JsonProperty("status")
    private String durum; // "Boş", "Dolu", "Rezerve"

    public String getMasaNo() {
        return "";
    }

    public void setMasaNo(String masaNo) {
    }

    public String getDurum() {
        return "";
    }

    public void setDurum(String durum) {
    }
}
