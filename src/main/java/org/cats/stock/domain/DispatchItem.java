package org.cats.stock.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.cats.core.BaseModel;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Data
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
}
