package com.kodilla.Task_final.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CompanyData {
    @Value("Firma Michała")
    private String companyName;

    @Value("Zarobić jak najwięcej")
    private String companyGoal;

    @Value("firma_michala@pl.pl")
    private String companyEmail;

    @Value("568 888 777")
    private String companyPhone;


    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyGoal() {
        return companyGoal;
    }

    public void setCompanyGoal(String companyGoal) {
        this.companyGoal = companyGoal;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }
}
