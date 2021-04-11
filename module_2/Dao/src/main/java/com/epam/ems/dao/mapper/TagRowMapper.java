package com.epam.ems.dao.mapper;

import com.epam.ems.dto.Tag;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.epam.ems.dto.TagFields.TAG_ID;
import static com.epam.ems.dto.TagFields.TAG_NAME;

@Component
public class TagRowMapper implements RowMapper<Tag> {

    @Override
    public Tag mapRow(ResultSet rs, int rowNum) throws SQLException {
        String name = rs.getString(TAG_NAME);
        int id = rs.getInt(TAG_ID);
        return new Tag(id, name);
    }
}
