package bigdater.dater.service;

import bigdater.dater.dto.request.SignUpUserRequest;
import bigdater.dater.entity.user.User;
import bigdater.dater.entity.user.UserRepository;
import bigdater.dater.entity.user.survey.Survey;
import bigdater.dater.entity.user.survey.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final SurveyRepository surveyRepository;

    @Transactional
    public ResponseEntity save(SignUpUserRequest request) {
        User savedUser = userRepository.save(User.builder()
                //.email(request.getEmail())
                .password(request.getPassword())
                .build());
        surveySave(request, savedUser);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @Transactional
    private void surveySave(SignUpUserRequest request, User user) {
        surveyRepository.save(Survey.builder()
                .nickname(request.getNickname())
                .sex(request.getSex())
                .isInsider(request.getIsInsider())
                .freeTime(request.getFreeTime())
                .companion(request.getCompanion())
                .food(request.getFood())
                .age(request.getAge())
                .user(user)
                .build());
    }
}
