package bigdater.dater.entity.user.survey;

import bigdater.dater.entity.user.User;
import lombok.*;

import javax.persistence.*;

@Builder
@Entity
@Table(name = "survey")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "survey_id")
    private Long id;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "sex")
    private Boolean sex;

    @Column(name = "is_insider")
    private Boolean isInsider;

    @Column(name = "free_time")
    private String freeTime;

    @Column(name = "companion")
    private String companion;

    @Column(name = "food")
    private String food;

    @Column(name = "age")
    private String age;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "survey")
    private User user;
}
