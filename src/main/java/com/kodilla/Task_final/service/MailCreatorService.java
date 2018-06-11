package com.kodilla.Task_final.service;

import com.kodilla.Task_final.config.AdminConfig;
import com.kodilla.Task_final.config.CompanyData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailCreatorService {


    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private CompanyData companyData;

    public String buildTrelloCardEmail(String message){
        Context context = new Context();
        context.setVariable("message",message);
        context.setVariable("task_url","http://localhost:8888/");
        context.setVariable("button","Visit website");
        context.setVariable("admin_name",adminConfig.getAdminName());
        context.setVariable("goodbye","Do zobaczenia "+adminConfig.getAdminName());
        context.setVariable("company_name",companyData.getCompanyName());
        context.setVariable("company_email",companyData.getCompanyEmail());
        context.setVariable("company_phone",companyData.getCompanyPhone());
        return templateEngine.process("mail/created-trello-card-mail",context);
    }

}
