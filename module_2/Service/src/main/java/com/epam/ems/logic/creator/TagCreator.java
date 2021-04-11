package com.epam.ems.logic.creator;

import com.epam.ems.dto.Tag;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import static com.epam.ems.dto.TagFields.TAG_NAME;


@Component
public class TagCreator implements Creator<Tag> {

    @Override
    public Tag createObject(MultiValueMap<String, String> allRequestParams) {
        String name = allRequestParams.get("tag_name").get(0);
        return new Tag(name);
    }
}
