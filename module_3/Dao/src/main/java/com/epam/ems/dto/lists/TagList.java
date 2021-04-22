package com.epam.ems.dto.lists;

import com.epam.ems.dto.Tag;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

public class TagList extends RepresentationModel<TagList> {
    private List<Tag> tags;

    public TagList(List<Tag> tags) {
        this.tags = tags;
    }

    public TagList() {
    }

    public List<Tag> getTags() {
        return tags;
    }
}
