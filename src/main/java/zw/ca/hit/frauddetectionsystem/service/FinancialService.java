package zw.ca.hit.frauddetectionsystem.service;

import org.springframework.web.multipart.MultipartFile;
import zw.ca.hit.frauddetectionsystem.domain.FinancialStatement;

import java.util.List;

public interface FinancialService {
    void save(MultipartFile file);
    List<FinancialStatement> getAllStatements();
}
