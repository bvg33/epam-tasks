package com.epam.ems.hateoas;

import com.epam.ems.controllers.TagsController;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TagControllerHateoas implements Hateoas {
    @Override
    public void createHateoas(RepresentationModel entity) {
        entity.add(linkTo(methodOn(TagsController.class).TagById(1)).withSelfRel());
        entity.add(linkTo(methodOn(TagsController.class).AllTags(1, 5)).withSelfRel());
        entity.add(linkTo(TagsController.class).slash("new").withSelfRel());
        entity.add(linkTo(TagsController.class).slash(1).withSelfRel());
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("getByTagName", "tag");
        entity.add(linkTo(methodOn(TagsController.class).sortByParam(1, 1, map)).withSelfRel());
    }
}
