package com.example.CRM.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "plans")
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "plan_name", nullable = false, unique = true)
    private String planName;

    @Column(name = "plan_price", nullable = false)
    private Double planPrice;

    @Column(name = "message_quota", nullable = false)
    private Integer messageQuota;

    // --- Getters and Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public Double getPlanPrice() {
        return planPrice;
    }

    public void setPlanPrice(Double planPrice) {
        this.planPrice = planPrice;
    }

    public Integer getMessageQuota() {
        return messageQuota;
    }

    public void setMessageQuota(Integer messageQuota) {
        this.messageQuota = messageQuota;
    }
}