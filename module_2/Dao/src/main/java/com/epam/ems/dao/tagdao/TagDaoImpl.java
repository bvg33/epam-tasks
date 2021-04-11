package com.epam.ems.dao.tagdao;

import com.epam.ems.dao.CRDDao;
import com.epam.ems.dao.mapper.TagRowMapper;
import com.epam.ems.dto.Tag;
import com.epam.ems.exceptions.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

import static com.epam.ems.dto.SortParameters.*;

@Repository
public class TagDaoImpl implements CRDDao<Tag> {

    private final JdbcTemplate template;
    private static final String GET_BY_ID_QUERY = "select * from epam.tag where id=?";
    private static final String GET_ALL_QUERY = "select * from epam.tag";
    private static final String INSERT_QUERY = "insert into epam.tag (tag_name) values (?)";
    private static final String DELETE_QUERY = "delete from epam.tag where id=?";
    private static final String GET_BY_NAME_QUERY = "select * from epam.tag where tag_name=?";
    private static final String GET_ALL_SORTED_BY_NAME = "select * from epam.tag t order by t.tag_name ";
    private static final String GET_BY_NAME_PART = "select * from epam.tag where tag_name like ";

    @Autowired
    public TagDaoImpl(@Qualifier(value = "mySql") DataSource dataSource, TagRowMapper rowMapper) {
        template = new JdbcTemplate(dataSource);
        this.tagRowMapper = rowMapper;
    }

    private TagRowMapper tagRowMapper;

    @Override
    public Tag getById(int id) throws DaoException {
        try {
            return template.queryForObject(GET_BY_ID_QUERY, new Object[]{id}, tagRowMapper);
        } catch (DataAccessException exception) {
            throw new DaoException("there is no tag with current id=" + id);
        }
    }

    @Override
    public List<Tag> getAll() {
        return template.query(GET_ALL_QUERY, tagRowMapper);
    }


    @Override
    public void insert(Tag item) {
        template.update(INSERT_QUERY, item.getTagName());
    }

    @Override
    public void removeById(int id) {
        template.update(DELETE_QUERY, new Object[]{id});
    }

    @Override
    public List<Tag> getByTagName(String name) {
        return template.query(GET_BY_NAME_QUERY, new Object[]{name}, tagRowMapper);
    }

    @Override
    public List<Tag> getEntitiesSortedByParameter(String sortType, String param) {
        if (param == SORT_BY_NAME) {
            return template.query(GET_ALL_SORTED_BY_NAME + sortType, tagRowMapper);
        }
        return new ArrayList<>();
    }

    @Override
    public List<Tag> getByNamePart(String parameter) {
        String newParameter = "%" + parameter + "%";
        return template.query(GET_BY_NAME_PART + "\'" + newParameter + "\'", tagRowMapper);
    }
}
