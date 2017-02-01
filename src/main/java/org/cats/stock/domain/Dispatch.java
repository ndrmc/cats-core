package org.cats.stock.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.cats.core.BaseModel;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Dispatch extends BaseModel {

	@NotNull
	private String gin;
	private String requisitionNo;
	private Long operationId;
	private Long fdpId;
	private String driver;
	private Date  dispatchedDate;
	private Long createdBy  ;
	private Long dispatchedBy;
	private String  remark;
	private Long hubId;
	private Long warehouseId;
	private Long transporterId;
	private String weightBridgeTicketNumber;
	private String trailerPlateNumber;
	private String truckPlateNumber;

	private Boolean draft = false;


	@OneToMany(mappedBy = "dispatch")
	private List<DispatchItem> dispatchItems;

	@ManyToOne
	@JoinColumn(name = "operationId", referencedColumnName = "id", insertable = false, updatable = false)
	@JsonIgnore
	private Operation operation;

	public Boolean isDraft() {
		return draft;
	}

	public void setDraft(Boolean draft) {
		this.draft = draft;
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

	public Long getTransporterId() {
		return transporterId;
	}

	public void setTransporterId(Long transporterId) {
		this.transporterId = transporterId;
	}

	public String getWeightBridgeTicketNumber() {
		return weightBridgeTicketNumber;
	}

	public void setWeightBridgeTicketNumber(String weightBridgeTicketNumber) {
		this.weightBridgeTicketNumber = weightBridgeTicketNumber;
	}

	public String getTrailerPlateNumber() {
		return trailerPlateNumber;
	}

	public void setTrailerPlateNumber(String trailerPlateNumber) {
		this.trailerPlateNumber = trailerPlateNumber;
	}

	public String getTruckPlateNumber() {
		return truckPlateNumber;
	}

	public void setTruckPlateNumber(String truckPlateNumber) {
		this.truckPlateNumber = truckPlateNumber;
	}

	public String getGin() {
		return gin;
	}

	public void setGin(String gin) {
		this.gin = gin;
	}

	public String getRequisitionNo() {
		return requisitionNo;
	}

	public void setRequisitionNo(String requisitionNo) {
		this.requisitionNo = requisitionNo;
	}

	public Long getOperationId() {
		return operationId;
	}

	public void setOperationId(Long operationId) {
		this.operationId = operationId;
	}

	public Long getFdpId() {
		return fdpId;
	}

	public void setFdpId(Long fdpId) {
		this.fdpId = fdpId;
	}


	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public Date getDispatchedDate() {
		return dispatchedDate;
	}

	public void setDispatchedDate(Date dispatchedDate) {
		this.dispatchedDate = dispatchedDate;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Long getDispatchedBy() {
		return dispatchedBy;
	}

	public void setDispatchedBy(Long dispatchedBy) {
		this.dispatchedBy = dispatchedBy;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<DispatchItem> getDispatchItems() {
		return dispatchItems;
	}

	public void setDispatchItems(List<DispatchItem> dispatchItems) {
		this.dispatchItems = dispatchItems;
	}

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}


}
