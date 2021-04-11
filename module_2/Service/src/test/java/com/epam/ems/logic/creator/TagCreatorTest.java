package com.epam.ems.logic.creator;

import com.epam.ems.dto.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static com.epam.ems.dto.TagFields.TAG_NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TagCreatorTest {

    private final TagCreator creator = new TagCreator();

    @Test
    public void testCreateObject() {
        MultiValueMap<String, String> fields = new LinkedMultiValueMap<>();
        fields.add(TAG_NAME, "asd");
        Tag actual = creator.createObject(fields);
        Tag expected = new Tag("asd");
        assertEquals(expected, actual);
    }


}
