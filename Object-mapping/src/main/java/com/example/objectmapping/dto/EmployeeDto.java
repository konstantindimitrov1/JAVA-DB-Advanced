package com.example.objectmapping.dto;

import java.math.BigDecimal;

public class EmployeeDto extends BasicEmployeeDto {

    private BigDecimal income;

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }
}
