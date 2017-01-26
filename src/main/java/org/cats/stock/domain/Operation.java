package org.cats.stock.domain;

import org.cats.core.BaseModel;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Operation extends BaseModel {

	private Long programId;
	private Long planId;
	private String name;
	private String description;
	private String year;
	private Long round;
	private Long rationId;
	private Integer operationMonth;
	private Date expectedStart;
	private Date plannedEnd;
	private Date actualStart;
	private Date actualEnd;
	private String status;

	@OneToMany(mappedBy = "operation" , cascade = CascadeType.ALL , orphanRemoval = true)
	private List<OperationRegion> operationRegions;

	public Long getProgramId() {
		return programId;
	}
	public void setProgramId(Long programId) {
		this.programId = programId;
	}
	public Long getPlanId() {
		return planId;
	}
	public void setPlanId(Long planId) {
		this.planId = planId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public Long getRound() {
		return round;
	}
	public void setRound(Long round) {
		this.round = round;
	}
	public Long getRationId() {
		return rationId;
	}
	public void setRationId(Long rationId) {
		this.rationId = rationId;
	}
	public Integer getOperationMonth() {
		return operationMonth;
	}
	public void setOperationMonth(Integer operationMonth) {
		this.operationMonth = operationMonth;
	}
	public Date getExpectedStart() {
		return expectedStart;
	}
	public void setExpectedStart(Date expectedStart) {
		this.expectedStart = expectedStart;
	}
	public Date getPlannedEnd() {
		return plannedEnd;
	}
	public void setPlannedEnd(Date plannedEnd) {
		this.plannedEnd = plannedEnd;
	}
	public Date getActualStart() {
		return actualStart;
	}
	public void setActualStart(Date actualStart) {
		this.actualStart = actualStart;
	}
	public Date getActualEnd() {
		return actualEnd;
	}
	public void setActualEnd(Date actualEnd) {
		this.actualEnd = actualEnd;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<OperationRegion> getOperationRegions() {
		return operationRegions;
	}
	public void setOperationRegions(List<OperationRegion> operationRegions) {
		this.operationRegions = operationRegions;
	}





}
