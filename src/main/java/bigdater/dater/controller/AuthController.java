package bigdater.dater.controller;

import bigdater.dater.dto.request.SignUpUserRequest;
import bigdater.dater.dto.request.VerifyRequest;
import bigdater.dater.dto.response.CodeResponse;
import bigdater.dater.dto.response.VerifyResponse;
import bigdater.dater.entity.user.survey.Survey;
import bigdater.dater.service.EmailService;
import bigdater.dater.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final EmailService emailService;

    @PostMapping("/mail")
    public CodeResponse sendMail(@Email @RequestParam String email) throws Exception {
        return emailService.sendSimpleMessage(email);
    }

    @PostMapping("/verify")
    public VerifyResponse verifyCode(@Valid @RequestBody VerifyRequest request) {
        return emailService.verifyCode(request.getVerifyCode(), request.getUserCode());
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignUpUserRequest request) {
        return userService.signup(request);
    }
}
