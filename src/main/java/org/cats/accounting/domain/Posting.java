package org.cats.accounting.domain;

import org.cats.core.BaseModel;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

@Entity
public class Posting extends BaseModel {

    @NotNull
    @NotEmpty
    private UUID postingCode;
    private String documentType;
    private Integer documentId;
    private Date createdDate;
    private String createdBy;
    private Boolean posted;

    public UUID getPostingCode() {
        return postingCode;
    }

    public void setPostingCode(UUID postingCode) {
        this.postingCode = postingCode;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public Integer getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Integer documentId) {
        this.documentId = documentId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Boolean getPosted() {
        return posted;
    }

    public void setPosted(Boolean posted) {
        this.posted = posted;
    }
}
