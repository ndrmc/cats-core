package org.cats.accounting.repository;

import org.cats.accounting.domain.Account;
import org.cats.accounting.domain.Journal;
import org.cats.core.BaseModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JournalRepository extends JpaRepository<Journal, Long> {
    public Journal findByCode(String code);
}
