package zw.ca.hit.frauddetectionsystem.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
@Entity
@Data
public class FinancialStatement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long sales;
    private BigDecimal amount;
    private String name;
}
