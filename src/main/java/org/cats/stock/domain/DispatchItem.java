package org.cats.stock.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.cats.core.BaseModel;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;

@Entity
public class DispatchItem extends BaseModel {

	private Long commodityId;
	private Long commodityCategoryId;

	private BigDecimal quantity;

	private Long projectId;
	private Long batchId;
	private Long dispatchId;

	private String description;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="dispatchId", referencedColumnName = "id", insertable = false, updatable = false)
	private Dispatch dispatch;

	public Long getDispatchId() {
		return dispatchId;
	}

	public void setDispatchId(Long dispatchId) {
		this.dispatchId = dispatchId;
	}

	public Long getCommodityCategoryId() {
		return commodityCategoryId;
	}

	public void setCommodityCategoryId(Long commodityCategoryId) {
		this.commodityCategoryId = commodityCategoryId;
	}

	public Long getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(Long commodityId) {
		this.commodityId = commodityId;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Dispatch getDispatch() {
		return dispatch;
	}

	public void setDispatch(Dispatch dispatch) {
		this.dispatch = dispatch;
	}

	
	

}
