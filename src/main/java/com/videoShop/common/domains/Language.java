package com.videoShop.common.domains;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Language implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 3608636622572288459L;

    @JsonProperty
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @JsonProperty
    @NotEmpty
    @Size(max = 50)
    private String name;

    /**
     * default constructor
     */
    public Language() {
        // default
    }

    /**
     * 
     * @param name
     *            Group name
     */
    public Language(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Language other = (Language) obj;
        return Objects.equals(getId(), other.getId()) && Objects.equals(getName(), other.getName());
    }
}
