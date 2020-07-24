package com.rmr101.campus.mail;

public interface UserMail {
    public String getSenderAddress();
    public String getReceiverAddress();
    public String getSubject();
    public String getText();
}
