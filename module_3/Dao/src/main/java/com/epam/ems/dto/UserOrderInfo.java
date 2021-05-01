package com.epam.ems.dto;

import com.epam.ems.audit.AuditListener;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@EntityListeners(AuditListener.class)
@Entity
@Table(name = "epam.users_certificates")
public class UserOrderInfo extends RepresentationModel {
    @Id
    private int id;

    @Column(name = "user_id")
    @NonNull
    private int userId;
    @Column(name = "certificate_id")
    @NonNull
    private int certificateId;
    @Column(name = "certificate_price")
    @NonNull
    private int certificatePrice;
    @Column(name = "buy_date")
    @NonNull
    private String date;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UserOrderInfo orderInfo = (UserOrderInfo) o;
        return userId == orderInfo.userId && certificateId == orderInfo.certificateId && certificatePrice == orderInfo.certificatePrice;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userId, certificateId, certificatePrice);
    }
}
