package org.cats.stock.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private Long hubId;
    private Long storeLocationId;
    private Long supplierId;
    private Long transporterId;
    private String plateNo;
    private String trailerPlateNo;
    private String weightBridgeTicketNo;
    private Float weightBeforeUnloading;
    private Float weightAfterUnloading;
    private String storekeeperName;
    private String waybillNo;
    private String purchaseRequestNo;
    private String purchaseOrderNo;
    private String invoiceNo;
    private Long commoditySourceId;
    private Long status;
    private Long warehouseId;

    @OneToMany(mappedBy = "receipt")
    @JsonManagedReference
    private List<ReceiptLine> receiptLines;

    public Receipt(){}

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }


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

    public Long getHubId() {
        return hubId;
    }

    public void setHubId(Long hubId) {
        this.hubId = hubId;
    }

    public Long getStoreLocationId() {
        return storeLocationId;
    }

    public void setStoreLocationId(Long storeLocationId) {
        this.storeLocationId = storeLocationId;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Long getTransporterId() {
        return transporterId;
    }

    public void setTransporterId(Long transporterId) {
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

    public Float getWeightAfterUnloading() {
        return weightAfterUnloading;
    }

    public void setWeightAfterUnloading(Float weightAfterUnloading) {
        this.weightAfterUnloading = weightAfterUnloading;
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

    public Long getCommoditySourceId() {
        return commoditySourceId;
    }

    public void setCommoditySourceId(Long commoditySourceId) {
        this.commoditySourceId = commoditySourceId;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public List<ReceiptLine> getReceiptLines() {
        return receiptLines;
    }

    public void setReceiptLines(List<ReceiptLine> receiptLines) {
        this.receiptLines = receiptLines;
    }
}
