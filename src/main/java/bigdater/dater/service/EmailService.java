package bigdater.dater.service;

import bigdater.dater.dto.response.CodeResponse;
import bigdater.dater.dto.response.VerifyResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final JavaMailSender emailSender;

    private MimeMessage createMessage(String to, String code) throws Exception {

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

        for (int i = 0; i < 6; i++) {
            int idx = rd.nextInt(2);

            switch (idx) {
                case 0:
                    sb.append((char) ((int) (rd.nextInt(26)) + 65));
                    break;
                case 1:
                    sb.append((rd.nextInt(10)));
                    break;
            }
        }
        return sb.toString();
    }

    public CodeResponse sendSimpleMessage(String to) throws Exception {

        final String code = createKey();
        MimeMessage message = createMessage(to, code);
        try {
            emailSender.send(message);
            return CodeResponse.builder()
                    .code(code)
                    .build();
        } catch (MailException e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
    }

    public VerifyResponse verifyCode(String verifyCode, String userCode) {

        log.info("코드: " + verifyCode);
        return VerifyResponse.builder()
                .isValid(verifyCode.equals(userCode))
                .build();
    }

}
