package com.epam.ems.dao.certificatedao;

import com.epam.ems.dao.CRDDao;
import com.epam.ems.dao.CRUDDao;
import com.epam.ems.dao.creator.QueryCreator;
import com.epam.ems.dao.extractor.FieldExtractor;
import com.epam.ems.dao.mapper.CertificateRowMapper;
import com.epam.ems.dto.Certificate;
import com.epam.ems.dto.Tag;
//import com.epam.ems.exceptions.DaoException;
import com.epam.ems.exceptions.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.epam.ems.dto.SortParameters.SORT_BY_DATE;
import static com.epam.ems.dto.SortParameters.SORT_BY_NAME;
import static com.epam.ems.dto.CertificateFields.*;

@Repository
public class CertificateDaoImpl implements CRUDDao<Certificate> {
    private final JdbcTemplate template;
    private static final String GET_ALL_CERTIFICATES_QUERY = "select * from epam.gift_certificate g inner join epam.certificate_tag ct on " +
            "g.id=ct.certificate_id inner join epam.tag t on ct.tag_id=t.id order by g.id";
    private static final String GET_CERTIFICATES_BY_ID = "select * from epam.gift_certificate g inner join epam.certificate_tag ct on" +
            "            g.id=ct.certificate_id inner join epam.tag t on ct.tag_id=t.id where g.id=?";
    private static final String GET_CERTIFICATES_BY_TAG_NAME = "select * from epam.gift_certificate g inner join epam.certificate_tag ct on g.id=ct.certificate_id inner join epam.tag t on ct.tag_id=t.id where g.id in" +
            " (select certificate_id from epam.tag t inner join epam.certificate_tag ct on t.id=ct.tag_id where t.tag_name=?) order by g.id";
    private static final String DELETE_QUERY = "delete from epam.gift_certificate where id=?";

    private static final String DELETE_TAGS_QUERY = "delete from epam.certificate_tag where certificate_id=?";

    private static final String INSERT_QUERY = "insert into epam.gift_certificate (name,description,price,duration,create_date,last_update_date)" +
            " values (?,?,?,?,?,?)";

    private static final String ADD_TAGS_QUERY = "insert into epam.certificate_tag (certificate_id,tag_id) values(?,?)";

    private static final String GET_ALL_CERTIFICATES_SORTED_BY_NAME = "select * from epam.gift_certificate g inner join epam.certificate_tag ct on " +
            "g.id=ct.certificate_id inner join epam.tag t on ct.tag_id=t.id order by g.name ";

    private static final String GET_ALL_CERTIFICATES_SORTED_BY_DATE = "select * from epam.gift_certificate g inner join epam.certificate_tag ct on " +
            "g.id=ct.certificate_id inner join epam.tag t on ct.tag_id=t.id order by g.create_date ";

    private static final String GET_CERTIFICATES_BY_NAME_PART = "select * from epam.gift_certificate g inner join epam.certificate_tag ct on " +
            "g.id=ct.certificate_id inner join epam.tag t on ct.tag_id=t.id where g.name like ";

    @Autowired
    public CertificateDaoImpl(@Qualifier("mySql") DataSource dataSource, CertificateRowMapper certificateRowMapper) {
        template = new JdbcTemplate(dataSource);
        this.certificateRowMapper = certificateRowMapper;
    }

    private CertificateRowMapper certificateRowMapper;

    @Autowired
    private CRDDao<Tag> tagDao;

    @Autowired
    private FieldExtractor<Certificate> extractor;

    @Autowired
    private QueryCreator queryCreator;

    @Override
    public Certificate getById(int id) throws DaoException {
        try {
            return template.query(GET_CERTIFICATES_BY_ID, new Object[]{id}, certificateRowMapper).get(0);
        }catch (Exception e) {
            throw new DaoException("there is no certificate with id=" + id);
        }
    }

    @Override
    public List<Certificate> getAll() {
        return template.query(GET_ALL_CERTIFICATES_QUERY, certificateRowMapper);
    }

    @Override
    @Transactional
    public void insert(Certificate item) {
        Map<String, String> fields = extractor.extract(item);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, fields.get(NAME));
            ps.setString(2, fields.get(DESCRIPTION));
            ps.setString(3, fields.get(PRICE));
            ps.setString(4, fields.get(DURATION));
            ps.setString(5, fields.get(CREATE_DATE));
            ps.setString(6, fields.get(LAST_UPDATE_DATE));
            return ps;
        }, keyHolder);
        updateTags(item, keyHolder);
    }

    private void updateTags(Certificate item, KeyHolder keyHolder) {
        List<Integer> tagIds = getTagsIds(item);
        for (Integer id : tagIds) {
            template.update(ADD_TAGS_QUERY, new Object[]{keyHolder.getKey().intValue(), id});
        }
    }

    private List<Integer> getTagsIds(Certificate item) {
        List<Tag> tags = item.getTags();
        List<Integer> tagIds = new ArrayList();
        tags.stream().forEach(tag -> {
            String tagName = tag.getTagName();
            Tag tagWithId = tagDao.getByTagName(tagName).get(0);
            tagIds.add(tagWithId.getId());
        });
        return tagIds;
    }


    @Override
    public void removeById(int id) {
        template.update(DELETE_TAGS_QUERY, new Object[]{id});
        template.update(DELETE_QUERY, new Object[]{id});
    }

    @Override
    public List<Certificate> getByTagName(String name) {
        return template.query(GET_CERTIFICATES_BY_TAG_NAME, new Object[]{name}, certificateRowMapper);
    }

    @Override
    public List<Certificate> getEntitiesSortedByParameter(String sortType, String parameter) {
        if (parameter == SORT_BY_NAME) {
            return template.query(GET_ALL_CERTIFICATES_SORTED_BY_NAME + sortType, certificateRowMapper);
        } else if (parameter == SORT_BY_DATE) {
            return template.query(GET_ALL_CERTIFICATES_SORTED_BY_DATE + sortType, certificateRowMapper);
        }
        return new ArrayList<>();
    }

    @Override
    public List<Certificate> getByNamePart(String parameter) {
        String newParameter = "%" + parameter + "%";
        return template.query(GET_CERTIFICATES_BY_NAME_PART + '\'' + newParameter + '\'', certificateRowMapper);
    }

    @Override
    public void update(Certificate item) {
        Map<String, String> fields = extractor.extract(item);
        String updateQuery = queryCreator.createQuery(fields);
        template.update(updateQuery);
    }
}
