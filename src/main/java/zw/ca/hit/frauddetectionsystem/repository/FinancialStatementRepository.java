package zw.ca.hit.frauddetectionsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.ca.hit.frauddetectionsystem.domain.FinancialStatement;

public interface FinancialStatementRepository implements JpaRepository<FinancialStatement,Long> {
}
