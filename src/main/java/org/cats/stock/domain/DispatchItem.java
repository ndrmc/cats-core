package org.cats.stock.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.cats.core.BaseModel;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class DispatchItem extends BaseModel {

	private Long commodityId;
	private Long dispatchId;
	private Float quantity; 
	private Long uomId;
	private Long projectId;
	private Long batchId;
	private Long stockMoveId;
	private String description; 

	@ManyToOne
	@JoinColumn(name = "dispatchId", referencedColumnName = "id", insertable = false, updatable = false)
	@JsonIgnore
	private Dispatch dispatch;

	public Long getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(Long commodityId) {
		this.commodityId = commodityId;
	}
	
	public Long getDispatchId() {
		return dispatchId;
	}

	public void setDispatchId(Long dispatchId) {
		this.dispatchId = dispatchId;
	}

	public Float getQuantity() {
		return quantity;
	}

	public void setQuantity(Float quantity) {
		this.quantity = quantity;
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

	public Long getStockMoveId() {
		return stockMoveId;
	}

	public void setStockMoveId(Long stockMoveId) {
		this.stockMoveId = stockMoveId;
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
