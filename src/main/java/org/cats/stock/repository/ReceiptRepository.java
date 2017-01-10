package org.cats.stock.repository;

import org.cats.stock.domain.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {

    List<Receipt> findByHubId(Integer hubId );

    List<Receipt> findByStoreLocationId(Integer storeLocationId );

}
