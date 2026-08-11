package com.cafeapp.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tables")
public class TableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int masaNo;
    private String durum = "BOŞ"; // "BOŞ", "DOLU", "HESAP_ISTENDI", "REZERVE"
    private int capacity = 4;

    public TableEntity() {
    }

    public TableEntity(int masaNo, String durum, int capacity) {
        this.masaNo = masaNo;
        this.durum = durum;
        this.capacity = capacity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMasaNo() {
        return masaNo;
    }

    public void setMasaNo(int masaNo) {
        this.masaNo = masaNo;
    }

    public String getDurum() {
        return durum;
    }

    public void setDurum(String durum) {
        this.durum = durum;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
