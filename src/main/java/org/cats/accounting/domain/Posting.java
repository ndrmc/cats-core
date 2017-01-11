package org.cats.accounting.domain;

import org.cats.core.BaseModel;
import org.cats.stock.enums.DocumentType;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

/**
 * Created by alexander on 1/11/17.
 */
@Entity
public class Posting extends BaseModel {

    private UUID postingCode;

    private Long documentId;

    @Enumerated(value = EnumType.STRING)
    private DocumentType documentType;

    private Boolean posted;


    @OneToMany(mappedBy = "posting")
    List<PostingItem> postingItems;

    public List<PostingItem> getPostingItems() {
        return postingItems;
    }

    public void setPostingItems(List<PostingItem> postingItems) {
        this.postingItems = postingItems;
    }

    @PrePersist
    protected void onCreate() {

        postingCode = UUID.randomUUID();
    }


    public UUID getPostingCode() {
        return postingCode;
    }

    public void setPostingCode(UUID postingCode) {
        this.postingCode = postingCode;
    }

    public Long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public Boolean getPosted() {
        return posted;
    }

    public void setPosted(Boolean posted) {
        this.posted = posted;
    }
}
