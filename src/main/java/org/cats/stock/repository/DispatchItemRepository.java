package org.cats.stock.repository;

import org.cats.stock.domain.DispatchItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DispatchItemRepository extends JpaRepository<DispatchItem, Integer> {

}
