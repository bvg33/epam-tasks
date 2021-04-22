package com.epam.ems.dto;

import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Table(name = "epam.users")
public class User extends RepresentationModel<User> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    @Min(1)
    private int id;
    @Pattern(regexp = "^.{0,45}$")
    private String nickname;
    @Min(0)
    private int money;
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "epam.users_certificates",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "certificate_id") }
    )
    private List<Certificate> certificates;
    @Column(name ="overage_order_price")
    @Min(0)
    private int overageOrderPrice;

    public User(int id, String name, int money,List<Certificate> certificates,int overageOrderPrice) {
        this(name, money,certificates,overageOrderPrice);
        this.id = id;
    }

    public User() {
    }

    public User(String name, int money, List<Certificate> certificates, int overageOrderPrice) {
        this.nickname = name;
        this.money = money;
        this.certificates=certificates;
        this.overageOrderPrice=overageOrderPrice;
    }

    public int getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public int getMoney() {
        return money;
    }

    public List<Certificate> getCertificates() {
        return certificates;
    }

    public int getOverageOrderPrice() {
        return overageOrderPrice;
    }
}
