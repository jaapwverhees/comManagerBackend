package competitie.manager.models.stage;

import competitie.manager.models.predictions.TimeTrialStagePrediction;
import competitie.manager.models.score.Score;
import competitie.manager.models.team.Team;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TimeTrialStage extends Stage {

    @OneToMany
    private List<Team> teams;

    @OneToMany
    private List<Score> scores;

    @OneToMany
    private List<TimeTrialStagePrediction> predictions;

}
