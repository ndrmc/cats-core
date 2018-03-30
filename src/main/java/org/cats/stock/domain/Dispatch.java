package org.cats.stock.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.cats.core.BaseModel;
import org.cats.operation.domain.Operation;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
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

}
