package org.cats.stock.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.cats.core.BaseModel;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class ReceiptLine extends BaseModel {
    private Long commodityId;
    private Float amount;
    private Long uomId;
    private Long projectId;
    private Long batchId;

    @ManyToOne
    @JsonIgnore
    private Receipt receipt;

    public Long getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Long commodityId) {
        this.commodityId = commodityId;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Long getUomId() {
        return uomId;
    }

    public void setUomId(Long uomId) {
        this.uomId = uomId;
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

    public Receipt getReceipt() {
        return receipt;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }
}
