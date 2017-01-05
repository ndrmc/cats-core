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
	private Integer operationId;
	private Integer periodMonth;
	private Integer periodRound;
	private String fdpId;
	private String transportOrderId;
	private String driver;
	private String plateNo;
	private Date createdDate;
	private Date  dispatchedDate;
	private Integer createdBy  ;
	private Integer dispatchedBy;
	private String  remark;
	private Date lastUpdated;

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

	public Integer getOperationId() {
		return operationId;
	}

	public void setOperationId(Integer operationId) {
		this.operationId = operationId;
	}

	public Integer getPeriodMonth() {
		return periodMonth;
	}

	public void setPeriodMonth(Integer periodMonth) {
		this.periodMonth = periodMonth;
	}

	public Integer getPeriodRound() {
		return periodRound;
	}

	public void setPeriodRound(Integer periodRound) {
		this.periodRound = periodRound;
	}
	
	public String getFdpId() {
		return fdpId;
	}

	public void setFdpId(String fdpId) {
		this.fdpId = fdpId;
	}

	public String getTransportOrderId() {
		return transportOrderId;
	}

	public void setTransportOrderId(String transportOrderId) {
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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getDispatchedDate() {
		return dispatchedDate;
	}

	public void setDispatchedDate(Date dispatchedDate) {
		this.dispatchedDate = dispatchedDate;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getDispatchedBy() {
		return dispatchedBy;
	}

	public void setDispatchedBy(Integer dispatchedBy) {
		this.dispatchedBy = dispatchedBy;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
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
