package org.cats.stock.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.cats.core.BaseModel;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class DispatchItem extends BaseModel {

	private Integer commodityId; 
	private Integer dispatchId; 
	private Float quantity; 
	private Integer uomId;
	private Integer projectId;  
	private Integer batchId;   
	private Integer stockMoveId;
	private String description; 

	@ManyToOne
	@JoinColumn(name = "dispatchId", referencedColumnName = "id", insertable = false, updatable = false)
	@JsonIgnore
	private Dispatch dispatch;

	public Integer getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(Integer commodityId) {
		this.commodityId = commodityId;
	}

	public Integer getDispatchId() {
		return dispatchId;
	}

	public void setDispatchId(Integer dispatchId) {
		this.dispatchId = dispatchId;
	}

	public Float getQuantity() {
		return quantity;
	}

	public void setQuantity(Float quantity) {
		this.quantity = quantity;
	}

	public Integer getUomId() {
		return uomId;
	}

	public void setUomId(Integer uomId) {
		this.uomId = uomId;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getBatchId() {
		return batchId;
	}

	public void setBatchId(Integer batchId) {
		this.batchId = batchId;
	}

	public Integer getStockMoveId() {
		return stockMoveId;
	}

	public void setStockMoveId(Integer stockMoveId) {
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
