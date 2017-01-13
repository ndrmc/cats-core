package org.cats.accounting.domain;

import org.cats.core.BaseModel;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class Journal extends BaseModel {

    @NotNull
    private String name;
    private String description;
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
