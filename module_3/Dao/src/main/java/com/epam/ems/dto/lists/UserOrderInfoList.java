package com.epam.ems.dto.lists;

import com.epam.ems.dto.UserOrderInfo;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

public class UserOrderInfoList extends RepresentationModel {
    private List<UserOrderInfo> userOrderInfo;

    public UserOrderInfoList(List<UserOrderInfo> userOrderInfo) {
        this.userOrderInfo = userOrderInfo;
    }

    public List<UserOrderInfo> getUserOrderInfo() {
        return userOrderInfo;
    }
}
