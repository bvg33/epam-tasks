package com.epam.ems.dto;

import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;

@Entity
@Table(name = "epam.users_certificates")
public class UserOrderInfo extends RepresentationModel {
    @Id
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "certificate_id")
    private int certificateId;
    @Column(name = "certificate_price")
    private int certificatePrice;
    @Column(name = "buy_date")
    private String date;

    public UserOrderInfo(int userId, int certificateId, int certificatePrice, String date) {
        this.userId = userId;
        this.certificateId = certificateId;
        this.certificatePrice = certificatePrice;
        this.date = date;
    }

    public UserOrderInfo() {
    }

    public int getUserId() {
        return userId;
    }

    public int getCertificateId() {
        return certificateId;
    }

    public int getCertificatePrice() {
        return certificatePrice;
    }

    public String getDate() {
        return date;
    }

    public int getId(){
        return id;
    }
}
