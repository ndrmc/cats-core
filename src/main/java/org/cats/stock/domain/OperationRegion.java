package org.cats.stock.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.cats.core.BaseModel;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class OperationRegion extends BaseModel {

	@Column(name="operation_id", insertable=false, updatable = false)
    private Long operationId;
	private Long regionId;


	@ManyToOne
	@JoinColumn(name="operation_id")
	@JsonIgnore
	private Operation operation;


	public Long getRegionId() {
		return regionId;
	}
	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}
	public Operation getOperation() {
		return operation;
	}
	public void setOperation(Operation operation) {
		this.operation = operation;
	}
	public Long getOperationId() {
		return operationId;
	}


}
