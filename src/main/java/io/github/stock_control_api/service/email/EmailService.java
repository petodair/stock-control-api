package io.github.stock_control_api.service.email;


import jakarta.mail.MessagingException;

public interface EmailService {
    void sendSimpleEmail(String to, String subject, String body);
    void sendMimeMessage(String to, String subject, String body) throws MessagingException;
}
