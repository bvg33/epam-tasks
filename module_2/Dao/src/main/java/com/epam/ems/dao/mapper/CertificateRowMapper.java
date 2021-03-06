package com.epam.ems.dao.mapper;

import com.epam.ems.dto.Certificate;
import com.epam.ems.dto.Tag;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.epam.ems.dto.CertificateFields.*;
import static com.epam.ems.dto.TagFields.*;

@Component
public class CertificateRowMapper implements ResultSetExtractor<List<Certificate>> {

    @Override
    public List<Certificate> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<Certificate> certificates = new ArrayList<>();
        rs.next();
        while (!rs.isAfterLast()) {
            int id = rs.getInt(ID);
            String name = rs.getString(NAME);
            String description = rs.getString(DESCRIPTION);
            String createDate = rs.getString(CREATE_DATE);
            String lastUpdateDate = rs.getString(LAST_UPDATE_DATE);
            int price = rs.getInt(PRICE);
            int duration = rs.getInt(DURATION);
            List<Tag> tags = new ArrayList<>();
            while (!rs.isAfterLast() && rs.getInt(ID) == id) {
                int tagId = rs.getInt(TAG_ID);
                String tagName = rs.getString(TAG_NAME);
                tags.add(new Tag(tagId, tagName));
                rs.next();
            }
            certificates.add(new Certificate(id, name, description, price, duration, createDate, lastUpdateDate, tags));
        }
        return certificates;
    }
}
