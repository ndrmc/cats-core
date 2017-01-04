package org.cats.stock.receipt.domain;

import org.cats.core.BaseModel;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
public class Receipt extends BaseModel {
    @NotNull
    private String grnNo;
    private Date receivedDate;
    private Integer hubId;
    private Integer storeLocationId;
    private Integer supplierId;
    private Integer transporterId;
    private String plateNo;
    private String trailerPlateNo;
    private String weightBridgeTicketNo;
    private Float weightBeforeUnloading;
    private Float weighAfterUnloading;
    private String storekeeperName;
    private String waybillNo;
    private String purchaseRequestNo;
    private String purchaseOrderNo;
    private String invoiceNo;
    private Integer commoditySourceId;
    private Integer status;

    @OneToMany(mappedBy = "receipt")
    private List<ReceiptLine> receiptLines;

    Receipt(){}


    public String getGrnNo() {
        return grnNo;
    }

    public void setGrnNo(String grnNo) {
        this.grnNo = grnNo;
    }

    public Date getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
    }

    public Integer getHubId() {
        return hubId;
    }

    public void setHubId(Integer hubId) {
        this.hubId = hubId;
    }

    public Integer getStoreLocationId() {
        return storeLocationId;
    }

    public void setStoreLocationId(Integer storeLocationId) {
        this.storeLocationId = storeLocationId;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public Integer getTransporterId() {
        return transporterId;
    }

    public void setTransporterId(Integer transporterId) {
        this.transporterId = transporterId;
    }

    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }

    public String getTrailerPlateNo() {
        return trailerPlateNo;
    }

    public void setTrailerPlateNo(String trailerPlateNo) {
        this.trailerPlateNo = trailerPlateNo;
    }

    public String getWeightBridgeTicketNo() {
        return weightBridgeTicketNo;
    }

    public void setWeightBridgeTicketNo(String weightBridgeTicketNo) {
        this.weightBridgeTicketNo = weightBridgeTicketNo;
    }

    public Float getWeightBeforeUnloading() {
        return weightBeforeUnloading;
    }

    public void setWeightBeforeUnloading(Float weightBeforeUnloading) {
        this.weightBeforeUnloading = weightBeforeUnloading;
    }

    public Float getWeighAfterUnloading() {
        return weighAfterUnloading;
    }

    public void setWeighAfterUnloading(Float weighAfterUnloading) {
        this.weighAfterUnloading = weighAfterUnloading;
    }

    public String getStorekeeperName() {
        return storekeeperName;
    }

    public void setStorekeeperName(String storekeeperName) {
        this.storekeeperName = storekeeperName;
    }

    public String getWaybillNo() {
        return waybillNo;
    }

    public void setWaybillNo(String waybillNo) {
        this.waybillNo = waybillNo;
    }

    public String getPurchaseRequestNo() {
        return purchaseRequestNo;
    }

    public void setPurchaseRequestNo(String purchaseRequestNo) {
        this.purchaseRequestNo = purchaseRequestNo;
    }

    public String getPurchaseOrderNo() {
        return purchaseOrderNo;
    }

    public void setPurchaseOrderNo(String purchaseOrderNo) {
        this.purchaseOrderNo = purchaseOrderNo;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public Integer getCommoditySourceId() {
        return commoditySourceId;
    }

    public void setCommoditySourceId(Integer commoditySourceId) {
        this.commoditySourceId = commoditySourceId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<ReceiptLine> getReceiptLines() {
        return receiptLines;
    }

    public void setReceiptLines(List<ReceiptLine> receiptLines) {
        this.receiptLines = receiptLines;
    }
}
