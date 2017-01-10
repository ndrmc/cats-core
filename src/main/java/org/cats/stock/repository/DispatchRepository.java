package org.cats.stock.repository;

import java.util.List;

import org.cats.stock.domain.Dispatch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DispatchRepository extends JpaRepository<Dispatch, Long> {

	Dispatch findByGin(String gin);
	List<Dispatch> findByOperationId(Integer operationId);
	List<Dispatch> findByFdpId(Integer fdpId);
	List<Dispatch> findByFdpIdIn(List<Integer> fdpIds);
	List<Dispatch> findByRequisitionNo(String requisitionNo);

}
