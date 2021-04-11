package com.epam.ems.dao.extractor;

import com.epam.ems.dto.Certificate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static com.epam.ems.dto.CertificateFields.*;

@Component
public class CertificateFieldExtractor implements FieldExtractor<Certificate> {

    @Override
    public Map<String, String> extract(Certificate item) {
        String createDate = item.getCreateDate();
        String description = item.getDescription();
        String durationString = intFieldToString(item.getDuration());
        String lastUpdateDate = item.getLastUpdateDate();
        String name = item.getName();
        String priceString = intFieldToString(item.getPrice());
        String idString = intFieldToString(item.getId());
        Map<String, String> map = new HashMap<>();
        map.put(CREATE_DATE, createDate);
        map.put(DESCRIPTION, description);
        map.put(DURATION, durationString);
        map.put(LAST_UPDATE_DATE, lastUpdateDate);
        map.put(NAME, name);
        map.put(PRICE, priceString);
        map.put(ID, idString);
        return map;
    }

    private String intFieldToString(int field) {
        return String.valueOf(field); //todo remove 2nd return
    }
}
