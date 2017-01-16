package org.cats.accounting.domain;

import org.cats.core.BaseModel;
import org.cats.stock.enums.DocumentType;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by alexander on 1/11/17.
 */
@Entity
public class Posting extends BaseModel {

    private UUID postingCode;


    @Convert(converter = DocumentType.Converter.class)
    private DocumentType documentType;

    @Convert(converter = PostingType.Converter.class)
    private PostingType postingType;

    private Long documentId;

    @OneToOne(optional = true)
    @JoinColumn(name = "reversed_posting_id")
    Posting reversedPosting;

    Date postingDate;


    @OneToMany(mappedBy = "posting")
    List<PostingItem> postingItems;


    @PrePersist
    protected void populateFieldsBeforeSave() {
        postingCode = UUID.randomUUID();

        if ( null != postingDate) {
            postingDate = new Date();
        }
    }

    public Date getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(Date postingDate) {
        this.postingDate = postingDate;
    }

    public Posting getReversedPosting() {
        return reversedPosting;
    }

    public void setReversedPosting(Posting reversedPosting) {
        this.reversedPosting = reversedPosting;
    }

    public List<PostingItem> getPostingItems() {
        return postingItems;
    }

    public void setPostingItems(List<PostingItem> postingItems) {
        this.postingItems = postingItems;
    }


    public PostingType getPostingType() {
        return postingType;
    }

    public void setPostingType(PostingType postingType) {
        this.postingType = postingType;
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

}
