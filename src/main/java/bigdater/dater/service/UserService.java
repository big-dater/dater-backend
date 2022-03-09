package bigdater.dater.service;

import bigdater.dater.dto.request.SignUpUserRequest;
import bigdater.dater.entity.user.User;
import bigdater.dater.entity.user.UserRepository;
import bigdater.dater.entity.user.survey.Survey;
import bigdater.dater.entity.user.survey.SurveyRepository;
import bigdater.dater.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final SurveyRepository surveyRepository;

    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    private static final int ACCESS = 0;
    private static final int REFRESH = 1;

    @Transactional
    public ResponseEntity<?> signup(SignUpUserRequest request) {
        User savedUser = userRepository.save(User.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .roles(Collections.singletonList("ROLE_USER"))
                .build());
        saveUserPw(savedUser, request.getPassword());
        saveSurvey(request, savedUser);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Transactional
    private void saveSurvey(SignUpUserRequest request, User user) {
        Survey survey = surveyRepository.save(Survey.builder()
                .nickname(request.getNickname())
                .sex(request.getSex())
                .isInsider(request.getIsInsider())
                .freeTime(request.getFreeTime())
                .companion(request.getCompanion())
                .food(request.getFood())
                .age(request.getAge())
                .build());
        user.setSurvey(survey);
    }

    private void saveUserPw(User user, String userPw) {
        user.setPassword(passwordEncoder.encode(userPw));
        userRepository.save(user);
    }
}
