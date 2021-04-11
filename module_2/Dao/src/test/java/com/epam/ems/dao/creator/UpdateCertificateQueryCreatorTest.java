package com.epam.ems.dao.creator;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static com.epam.ems.dto.CertificateFields.ID;
import static com.epam.ems.dto.CertificateFields.NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdateCertificateQueryCreatorTest {

    private final UpdateCertificateQueryCreator creator = new UpdateCertificateQueryCreator();

    @Test
    public void testCreateQuery() {
        Map<String, String> fields = new HashMap<>();
        fields.put(ID, "1");
        fields.put(NAME, "name");
        String actual = creator.createQuery(fields);
        String expected = "update epam.gift_certificate set name='name' where id=1";
        assertEquals(expected, actual);
    }
}
