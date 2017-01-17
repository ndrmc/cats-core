package org.cats.accounting.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.cats.core.BaseModel;
import org.cats.stock.domain.ReceiptLine;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
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
    Long storeId;
    Long stackId;

    Long projectId;
    Long batchId;
    Long programId;
    Long operationId;

    Long commodityId;
    Long commodityCategoryId;

    BigDecimal quantity;

    Long regionId;
    Long zoneId;
    Long woredaId;

    Long fdpId;


    @ManyToOne
    @JsonIgnore
    Posting posting;

    @PrePersist
    protected void populateFieldsPrePersist() {

        postingItemCode = UUID.randomUUID();
    }


    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getStackId() {
        return stackId;
    }

    public void setStackId(Long stackId) {
        this.stackId = stackId;
    }



    public static PostingItem newPostingItem(){
        return new PostingItem();
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


    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
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

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
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

}
