package bigdater.dater.service;

import bigdater.dater.dto.response.VerifyResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Random;

@Service
@Slf4j
public class EmailService {
    JavaMailSender emailSender;

    public static final String code = createKey();

    private MimeMessage createMessage(String to) throws Exception {
        log.info("보내는 대상 : " + to);
        log.info("인증 번호 : " + code);
        MimeMessage msg = emailSender.createMimeMessage();

        msg.addRecipients(Message.RecipientType.TO, to);
        msg.setSubject("회원가입 인증 번호 : " + code);

        String msgg = "";
        msgg += code.toString();
        msg.setText(msgg, "utf-8");
        msg.setFrom(new InternetAddress("sting5517@gmail.com", "dbstjr ths"));

        return msg;
    }

    public static String createKey() {
        StringBuffer sb = new StringBuffer();
        Random rd = new Random();

        for (int i = 0; i < 8; i++) {
            int idx = rd.nextInt(3);

            switch (idx) {
                case 0:
                    sb.append((char) ((int) (rd.nextInt(26)) + 97));
                    break;
                case 1:
                    sb.append((char) ((int) (rd.nextInt(26)) + 65));
                    break;
                case 2:
                    sb.append((rd.nextInt(10)));
                    break;
            }
        }
        return sb.toString();
    }

    public String sendSimpleMessage(String to) throws Exception {

        MimeMessage message = createMessage(to);
        try {
            emailSender.send(message);
        } catch (MailException e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
        return code;
    }

    public VerifyResponse verifyCode(String code) {
        return VerifyResponse.builder()
                .isValid(EmailService.code.equals(code))
                .build();
    }

}
