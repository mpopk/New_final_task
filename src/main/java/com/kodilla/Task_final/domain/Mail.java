package com.kodilla.Task_final.domain;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NonNull;


public class Mail {
    @NonNull
    private MailMessageType messageType;

    @NonNull
    private String mailTo;

    @NonNull
    private String subject;

    @NonNull
    private String message;

    private String toCc;



    public MailMessageType getMessageType() {
        return messageType;
    }

    public String getMailTo() {
        return mailTo;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }

    public String getToCc() {
        return toCc;
    }
}
