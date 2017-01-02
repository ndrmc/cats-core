package org.cats.accounting.domain;

import org.cats.core.BaseModel;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Entity
public class AccountTransaction extends BaseModel {

    private UUID transactionCode;
    private UUID postingCode;
    private Integer donorId;
    private String transactionType;
    private Integer hubId;
    private Integer warehouseId;
    private Integer storeId;
    private Integer stack;
    private Integer projectCodeId;
    private Integer batchId;
    private Integer programId;
    private Integer commodityId;
    private Integer commodityCategoryId;
    private BigDecimal quantity;
    private Integer uomId;
    private Date transactionDate;
    private Integer operationId;
    private Integer regionId;
    private Integer zoneId;
    private Integer woredaId;
    private Integer fdpId;

    private Posting posting;
    private Account account;
    private Journal journal;

    public UUID getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(UUID transactionCode) {
        this.transactionCode = transactionCode;
    }

    public UUID getPostingCode() {
        return postingCode;
    }

    public void setPostingCode(UUID postingCode) {
        this.postingCode = postingCode;
    }

    public Integer getDonorId() {
        return donorId;
    }

    public void setDonorId(Integer donorId) {
        this.donorId = donorId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Integer getHubId() {
        return hubId;
    }

    public void setHubId(Integer hubId) {
        this.hubId = hubId;
    }

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getStack() {
        return stack;
    }

    public void setStack(Integer stack) {
        this.stack = stack;
    }

    public Integer getProjectCodeId() {
        return projectCodeId;
    }

    public void setProjectCodeId(Integer projectCodeId) {
        this.projectCodeId = projectCodeId;
    }

    public Integer getBatchId() {
        return batchId;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }

    public Integer getProgramId() {
        return programId;
    }

    public void setProgramId(Integer programId) {
        this.programId = programId;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public Integer getCommodityCategoryId() {
        return commodityCategoryId;
    }

    public void setCommodityCategoryId(Integer commodityCategoryId) {
        this.commodityCategoryId = commodityCategoryId;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public Integer getUomId() {
        return uomId;
    }

    public void setUomId(Integer uomId) {
        this.uomId = uomId;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Integer getOperationId() {
        return operationId;
    }

    public void setOperationId(Integer operationId) {
        this.operationId = operationId;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public Integer getZoneId() {
        return zoneId;
    }

    public void setZoneId(Integer zoneId) {
        this.zoneId = zoneId;
    }

    public Integer getWoredaId() {
        return woredaId;
    }

    public void setWoredaId(Integer woredaId) {
        this.woredaId = woredaId;
    }

    public Integer getFdpId() {
        return fdpId;
    }

    public void setFdpId(Integer fdpId) {
        this.fdpId = fdpId;
    }

    public Posting getPosting() {
        return posting;
    }

    public void setPosting(Posting posting) {
        this.posting = posting;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Journal getJournal() {
        return journal;
    }

    public void setJournal(Journal journal) {
        this.journal = journal;
    }
}
