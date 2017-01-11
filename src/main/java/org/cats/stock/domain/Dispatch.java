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
	private Long periodMonth;
	private Long periodRound;
	private Long fdpId;
	private Long transportOrderId;
	private String driver;
	private String plateNo;
	private Date  dispatchedDate;
	private Long createdBy  ;
	private Long dispatchedBy;
	private String  remark;

	@OneToMany(mappedBy = "dispatch")
	private List<DispatchItem> dispatchItems;

	@ManyToOne
	@JoinColumn(name = "operationId", referencedColumnName = "id", insertable = false, updatable = false)
	@JsonIgnore
	private Operation operation;

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

	public Long getPeriodMonth() {
		return periodMonth;
	}

	public void setPeriodMonth(Long periodMonth) {
		this.periodMonth = periodMonth;
	}

	public Long getPeriodRound() {
		return periodRound;
	}

	public void setPeriodRound(Long periodRound) {
		this.periodRound = periodRound;
	}	

	public Long getFdpId() {
		return fdpId;
	}

	public void setFdpId(Long fdpId) {
		this.fdpId = fdpId;
	}

	public Long getTransportOrderId() {
		return transportOrderId;
	}

	public void setTransportOrderId(Long transportOrderId) {
		this.transportOrderId = transportOrderId;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getPlateNo() {
		return plateNo;
	}

	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
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
