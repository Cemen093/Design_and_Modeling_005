package com.example.design_and_modeling_005;

import com.sun.mail.util.MailSSLSocketFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Properties;

public class NewMessageController {

    @FXML
    public TextField to;
    @FXML
    public TextField subject;
    @FXML
    public TextArea text;
    @FXML
    public Button sendButton;

    private String user;
    private String pass;

    public void initData(String user, String pass) {
        this.user = user;
        this.pass = pass;
    }

    @FXML
    void initialize(){
        sendButton.setOnAction(actionEvent -> {

            try {
                MailSSLSocketFactory sf = new MailSSLSocketFactory();
                sf.setTrustAllHosts(true);


                Properties props = (Properties) System.getProperties().clone();
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.setProperty("mail.smtp.port", "587");
                props.put("mail.smtp.auth", true);
                props.put("mail.smtp.ssl.enable", false);
                props.put("mail.smtp.starttls.enable", true);

                Session ss = Session.getInstance(props, new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user, pass);
                    }
                });

                try {
                    Message msg = new MimeMessage(ss);
                    msg.setFrom(new InternetAddress(user));
                    msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to.getText()));
                    msg.setSubject(subject.getText());
                    msg.setText(text.getText());
                    Transport trans = ss.getTransport("smtp");
                    Transport.send(msg);

                    ((Stage) sendButton.getScene().getWindow()).close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (GeneralSecurityException e) {
                e.printStackTrace();
            }
        });
    }
}