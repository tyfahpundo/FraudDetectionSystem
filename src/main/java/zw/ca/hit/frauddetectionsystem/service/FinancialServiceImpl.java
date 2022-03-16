package zw.ca.hit.frauddetectionsystem.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import zw.ca.hit.frauddetectionsystem.domain.FinancialStatement;
import zw.ca.hit.frauddetectionsystem.helper.ExcelHelper;
import zw.ca.hit.frauddetectionsystem.repository.FinancialStatementRepository;

import java.io.IOException;
import java.util.List;

@Service
public class FinancialServiceImpl implements FinancialService{
    FinancialStatementRepository repository;
    @Override
    public void save(MultipartFile file) {
        try {
            List<FinancialStatement> statements = ExcelHelper.excelToFinancialStatement(file.getInputStream());
            repository.saveAll(statements);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

    @Override
    public List<FinancialStatement> getAllStatements() {
        return repository.findAll();
    }
}
