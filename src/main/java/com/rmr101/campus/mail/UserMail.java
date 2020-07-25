package com.rmr101.campus.mail;

import com.rmr101.campus.config.AwsMailConfig;

public abstract class UserMail {
    private String senderAddress = AwsMailConfig.FROM;
    private String receiverAddress = AwsMailConfig.TO;

    public String getSenderAddress(){
        return senderAddress;
    }

    public String getReceiverAddress(){
        return receiverAddress;
    }
    public abstract String getSubject();
    public abstract String getText();
}
