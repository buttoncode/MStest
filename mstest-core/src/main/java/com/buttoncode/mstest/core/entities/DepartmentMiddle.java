package com.buttoncode.mstest.core.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "departments")
@DiscriminatorValue("middle")
public class DepartmentMiddle extends Department implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "section_id")
    private SectionTop sectionTop;

    public SectionTop getSectionTop() {
        return sectionTop;
    }
    public void setSectionTop(SectionTop sectionTop) {
        this.sectionTop = sectionTop;
    }

}
