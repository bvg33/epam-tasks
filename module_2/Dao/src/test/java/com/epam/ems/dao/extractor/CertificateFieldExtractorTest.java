package com.epam.ems.dao.extractor;

import com.epam.ems.dto.Certificate;
import com.epam.ems.dto.CertificateFields;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.epam.ems.dto.CertificateFields.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CertificateFieldExtractorTest {

    private final CertificateFieldExtractor extractor = new CertificateFieldExtractor();

    @Test
    public void testExtract() {
        Certificate certificate = new Certificate(1, "name1", "desc1", 4, 5, new String(), new String(), new ArrayList<>());
        Map<String, String> actual = extractor.extract(certificate);
        Map<String, String> expected = new HashMap<>();
        expected.put(ID, "1");
        expected.put(NAME, "name1");
        expected.put(DESCRIPTION, "desc1"); //todo const
        expected.put(PRICE, "4");
        expected.put(DURATION, "5");
        expected.put(CREATE_DATE, new String());
        expected.put(LAST_UPDATE_DATE, new String());
        assertEquals(expected, actual);
    }
}
