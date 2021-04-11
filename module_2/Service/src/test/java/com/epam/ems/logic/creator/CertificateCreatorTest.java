package com.epam.ems.logic.creator;

import com.epam.ems.dto.Certificate;
import com.epam.ems.dto.CertificateFields;
import com.epam.ems.dto.Tag;
import com.epam.ems.logic.handler.DateHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.Arrays;

import static com.epam.ems.dto.CertificateFields.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration
public class CertificateCreatorTest {

    @Mock
    private DateHandler dateHandler = Mockito.mock(DateHandler.class);

    @InjectMocks
    private CertificateCreator creator;

    @Test
    public void testCreateObject_fullObject() {
        when(dateHandler.getCurrentDate()).thenReturn("2021-04-03 00:00:00");
        MultiValueMap<String, String> fields = new LinkedMultiValueMap<>();
        fields.add(CertificateFields.NAME, "asd");
        fields.add(DESCRIPTION, "dsf");
        fields.add(PRICE, "5");
        fields.add(DURATION, "5");
        fields.add(TAG, "spa");
        Certificate actual = creator.createObject(fields);
        Certificate expected = new Certificate("asd", "dsf", 5, 5,
                "2021-04-03 00:00:00", "2021-04-03 00:00:00", Arrays.asList(new Tag("spa")));
        assertEquals(expected, actual);
    }

    @Test
    public void testCreateObject_PartObject() {
        when(dateHandler.getCurrentDate()).thenReturn("2021-04-03 00:00:00");
        MultiValueMap<String, String> fields = new LinkedMultiValueMap<>();
        fields.add("id", "1");
        fields.add("name", "asd");
        fields.add("description", "dsf");
        Certificate actual = creator.createObject(fields);
        Certificate expected = new Certificate(1, "asd", "dsf", 0, 0, null, "2021-04-03 00:00:00", new ArrayList<>());
        assertEquals(expected, actual);
    }
}
