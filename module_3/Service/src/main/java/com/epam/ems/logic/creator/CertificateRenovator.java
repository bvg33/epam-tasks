package com.epam.ems.logic.creator;

import com.epam.ems.dto.Certificate;
import com.epam.ems.dto.Tag;
import com.epam.ems.logic.handler.DateHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CertificateRenovator implements Renovator<Certificate> {

    @Autowired
    private DateHandler dateHandler;

    @Override
    public Certificate updateObject(Certificate newEntity,Certificate oldEntity) {
        int id = oldEntity.getId();
        String name = newEntity.getName();
        if(name==null){
            name=oldEntity.getName();
        }
        String description = newEntity.getDescription();
        if(description==null){
            description=oldEntity.getDescription();
        }
        int price = newEntity.getPrice();
        if(price==0){
            price=oldEntity.getPrice();
        }
        int duration = newEntity.getDuration();
        if(duration==0){
            duration=oldEntity.getDuration();
        }
        List<Tag> tags = newEntity.getTags();
        if(tags==null){
            tags=oldEntity.getTags();
        }
        String currentDate = dateHandler.getCurrentDate();
        return new Certificate(id, name, description, price, duration, oldEntity.getCreateDate(),
                currentDate, tags);
    }
}