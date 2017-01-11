package org.cats.accounting.repository;

import org.cats.accounting.domain.Posting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostingRepository extends JpaRepository<Posting, Long> {}
