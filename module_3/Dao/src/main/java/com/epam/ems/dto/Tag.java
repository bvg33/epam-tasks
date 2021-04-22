package com.epam.ems.dto;


import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.Objects;

@Entity
@Table(name = "epam.tag")
public class Tag extends RepresentationModel<Tag> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private int id;

    @Column(name = "tag_name")
    @Pattern(regexp = "^.{0,45}$")
    private String tagName;

    public Tag() {
    }

    public Tag(int id, String name) {
        this.id = id;
        this.tagName = name;
    }

    public Tag(String name) {
        this.tagName = name;
    }

    public int getId() {
        return id;
    }

    public String getTagName() {
        return tagName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return Objects.equals(tagName, tag.tagName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tagName);
    }
}
