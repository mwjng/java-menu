package menu.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import menu.domain.lunch.Menu;
import menu.domain.lunch.Recommender;
import menu.dto.request.CoachRequest;
import menu.dto.request.CoachesRequest;
import menu.dto.response.RecommendationResponse;
import org.junit.jupiter.api.Test;

class RecommendationServiceTest {

    private final Recommender recommender = new Recommender();
    private final RecommendationService recommendationService = new RecommendationService(recommender);

    @Test
    void 코치_요청_정보를_기반으로_추천_결과를_반환한다() {
        // given
        CoachesRequest request = new CoachesRequest(
                List.of(new CoachRequest("토미", List.of(Menu.UDON, Menu.SUSHI)),
                        new CoachRequest("제임스", List.of(Menu.GNOCCHI, Menu.SPRING_ROLL)),
                        new CoachRequest("포코", List.of(Menu.MAPO_TOFU, Menu.GOCHU_JAPCHAE)))
        );

        // when
        RecommendationResponse response = recommendationService.recommend(request);

        // then
        assertThat(response.getCategories()).hasSize(5);
        assertThat(response.getCoaches()).hasSize(3)
                .extracting("coachName")
                .containsExactlyInAnyOrder("토미", "제임스", "포코");
    }
}