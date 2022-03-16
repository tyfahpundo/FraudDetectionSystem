package zw.ca.hit.frauddetectionsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.ca.hit.frauddetectionsystem.domain.FinancialStatement;

public interface FinancialStatementRepository extends JpaRepository<FinancialStatement, Long> {
}
