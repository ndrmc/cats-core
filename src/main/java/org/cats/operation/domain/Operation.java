package org.cats.operation.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.cats.core.BaseModel;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "operations")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Operation extends BaseModel {
	private Long programId;
	private Long hrdId;
	private Long fscdAnnualPlanId;
	private String name;
	private String descripiton;
	private String year;
	private Integer round;
	private Integer month;
	private Date expectedStart;
	private Date expectedEnd;
	private Date actualStart;
	private Date actualEnd;
	private String status;
	private Long rationId;
}
