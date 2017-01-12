package org.cats.accounting.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.cats.core.BaseModel;
import org.cats.stock.enums.PostingItemType;
import org.springframework.web.servlet.tags.form.PasswordInputTag;

import javax.persistence.*;
import java.util.UUID;

/**
 * Created by alexander on 1/11/17.
 */

@Entity
public class PostingItem extends BaseModel {


    UUID postingItemCode;
    Long accountId;
    Long journalId;
    Long donorId;

    Long hubId;
    Long warehouseId;

    Integer storeNo;
    Integer stack;

    Long projectCodeId;
    Long batchId;
    Long programId;
    Long commodityId;
    Long commodityCategoryId;
    Float quantity;

    Long operationId;

    Long regionId;
    Long zoneId;
    Long woredaId;
    Long fdpId;

    @Convert(converter = PostingItemType.Converter.class)
    PostingItemType postingItemType;

    @ManyToOne
    @JsonIgnore
    Posting posting;

    @PrePersist
    protected void onCreate() {

        postingItemCode = UUID.randomUUID();
    }


    public Posting getPosting() {
        return posting;
    }

    public void setPosting(Posting posting) {
        this.posting = posting;
    }

    public UUID getPostingItemCode() {
        return postingItemCode;
    }

    public void setPostingItemCode(UUID postingItemCode) {
        this.postingItemCode = postingItemCode;
    }


    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getJournalId() {
        return journalId;
    }

    public void setJournalId(Long journalId) {
        this.journalId = journalId;
    }

    public Long getDonorId() {
        return donorId;
    }

    public void setDonorId(Long donorId) {
        this.donorId = donorId;
    }

    public Long getHubId() {
        return hubId;
    }

    public void setHubId(Long hubId) {
        this.hubId = hubId;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Integer getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(Integer storeNo) {
        this.storeNo = storeNo;
    }

    public Integer getStack() {
        return stack;
    }

    public void setStack(Integer stack) {
        this.stack = stack;
    }

    public Long getProjectCodeId() {
        return projectCodeId;
    }

    public void setProjectCodeId(Long projectCodeId) {
        this.projectCodeId = projectCodeId;
    }

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public Long getProgramId() {
        return programId;
    }

    public void setProgramId(Long programId) {
        this.programId = programId;
    }

    public Long getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Long commodityId) {
        this.commodityId = commodityId;
    }

    public Long getCommodityCategoryId() {
        return commodityCategoryId;
    }

    public void setCommodityCategoryId(Long commodityCategoryId) {
        this.commodityCategoryId = commodityCategoryId;
    }

    public Float getQuantity() {
        return quantity;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }

    public Long getOperationId() {
        return operationId;
    }

    public void setOperationId(Long operationId) {
        this.operationId = operationId;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public Long getZoneId() {
        return zoneId;
    }

    public void setZoneId(Long zoneId) {
        this.zoneId = zoneId;
    }

    public Long getWoredaId() {
        return woredaId;
    }

    public void setWoredaId(Long woredaId) {
        this.woredaId = woredaId;
    }

    public Long getFdpId() {
        return fdpId;
    }

    public void setFdpId(Long fdpId) {
        this.fdpId = fdpId;
    }

    public PostingItemType getPostingItemType() {
        return postingItemType;
    }

    public void setPostingItemType(PostingItemType postingItemType) {
        this.postingItemType = postingItemType;
    }
}
