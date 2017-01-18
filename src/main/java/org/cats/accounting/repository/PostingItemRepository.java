package org.cats.accounting.repository;

import org.cats.accounting.domain.PostingItem;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public interface PostingItemRepository extends JpaRepository<PostingItem, Long> {
}
