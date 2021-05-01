package com.epam.ems.dto;

import com.epam.ems.audit.AuditListener;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@EntityListeners(AuditListener.class)
@Entity
@Table(name = "epam.users")
public class User extends RepresentationModel<User> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    @Min(1)
    private int id;
    @Pattern(regexp = "^.{0,45}$")
    @NonNull
    private String nickname;
    @Min(0)
    @NonNull
    private int money;
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "epam.users_certificates",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "certificate_id")}
    )
    @NonNull
    private List<Certificate> certificates;
    @Column(name = "overage_order_price")
    @Min(0)
    @NonNull
    private int overageOrderPrice;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return money == user.money && overageOrderPrice == user.overageOrderPrice && Objects.equals(nickname, user.nickname) && Objects.equals(certificates, user.certificates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nickname, money, certificates, overageOrderPrice);
    }
}
