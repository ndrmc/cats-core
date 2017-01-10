package org.cats.stock.domain;

import org.cats.core.BaseModel;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class Operation extends BaseModel{
	  
	private Integer programId;
	private Integer planId;
	private String name;
	private String description;
	private String year;
	private Integer round;
	private Integer rationId;
	private Integer operationMonth;
	private Date expectedStart;
	private Date plannedEnd;
	private Date actualStart;
	private Date actualEnd;
	private String status;
	
	public Integer getProgramId() {
		return programId;
	}
	public void setProgramId(Integer programId) {
		this.programId = programId;
	}
	public Integer getPlanId() {
		return planId;
	}
	public void setPlanId(Integer planId) {
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
	public Integer getRound() {
		return round;
	}
	public void setRound(Integer round) {
		this.round = round;
	}
	public Integer getRationId() {
		return rationId;
	}
	public void setRationId(Integer rationId) {
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
	
	
	
	

}
