package org.cats.accounting.repository;

import org.cats.accounting.domain.PostingItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostingItemRepository extends JpaRepository<PostingItem, Long> {}
