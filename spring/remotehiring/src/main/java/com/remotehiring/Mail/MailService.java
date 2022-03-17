package com.remotehiring.Mail;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.remotehiring.Bookings.Bookings;
import com.remotehiring.Bookings.BookingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import com.remotehiring.Users.Users;

@Service
public class MailService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    @Qualifier("emailConfigBean")
    private Configuration emailConfig;

    @Autowired
    private BookingsRepository repo;

    public void signupMail(Users user) throws MessagingException, IOException, TemplateException {


        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
        mimeMessageHelper.setTo(user.getEmail());
        String content = "You have signed up successfully to Book A Lot. You can now Start booking your parking slots at ease.";
        mimeMessageHelper.setText(content, true);
        mimeMessageHelper.setSubject("Welcome " + user.getFullname() + " to Book A Lot");
        mimeMessageHelper.setFrom("shantanukmr2000@gmail.com", "Book A Lot");
        emailSender.send(message);

    }

    public void paymentMail(Integer bookingid) throws MessagingException, IOException, TemplateException {

        Bookings booking = repo.findById(bookingid).get();
        MimeMessage email = emailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(email, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
        mimeMessageHelper.setTo(booking.getEmail());
        String content = "<div>\n" +
                "<h1>Payment Succesful! - Book-A-Lot</h1>\n"  +
                "<p>Your payment of Rs. " + booking.getCost() +  " for parking slot " + booking.getSlotid() +  " has been successful!.</p>\n" +
                "<p>Hope you enjoyed our services.</p>\n" +
                "<p>Please send your valuable feedback as a reply to this email.</p>\n" +
                "<p>Thanks for visiting Book-A-Lot</p>\n" +
                "</div>";
        mimeMessageHelper.setText(content, true);
        mimeMessageHelper.setSubject("Payment confirmation - Book-A-Lot");
        mimeMessageHelper.setFrom("shantanukmr2000@gmail.com", "Book A Lot");
        emailSender.send(email);

    }
}