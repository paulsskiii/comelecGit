package com.learning.smartwallet.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data   
public class ExpenseResponseDTO {

    private String description;
    private BigDecimal amount;
    private String category;

}
