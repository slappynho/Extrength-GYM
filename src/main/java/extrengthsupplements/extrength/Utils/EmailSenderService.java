package extrengthsupplements.extrength.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class EmailSenderService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String toEmail,
                          String subject,
                          String body){
        SimpleMailMessage message= new SimpleMailMessage();
        message.setFrom("EXTRENGTH@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        mailSender.send(message);

        System.out.println("Mail sent successfully");
    }

    public void sendMailWithAttachment(String toEmail,
                                      String subject,
                                      String body,
                                      String attachment) throws MessagingException {

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);


        helper.setFrom("Extrength@gmail.com");
        helper.setTo(toEmail);
        helper.setText(body);
        helper.setSubject(subject);


        FileSystemResource fileSystemResource =
                new FileSystemResource(new File(attachment));
        helper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);
        mailSender.send(mimeMessage);

        System.out.println("email whit pdf send successfully");


    }
}
