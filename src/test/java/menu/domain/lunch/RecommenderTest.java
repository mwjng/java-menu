package menu.domain.lunch;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import menu.domain.category.Categories;
import menu.domain.coach.Coach;
import menu.domain.coach.CoachName;
import org.junit.jupiter.api.Test;

class RecommenderTest {

    private final Recommender recommender = new Recommender();

    @Test
    void 각_코치에게_요일별로_메뉴를_추천한다() {
        // given
        Map<CoachName, ForbiddenMenus> forbiddenMap = Map.of(
                CoachName.of("토미"), ForbiddenMenus.of(List.of(Menu.BIBIMBAP, Menu.BAGUETTE)),
                CoachName.of("제임스"), ForbiddenMenus.of(List.of(Menu.GIMBAP, Menu.KIMCHI_JJIGAE))
        );
        Categories categories = Categories.generateRandomly();

        // when
        List<Coach> coaches = recommender.recommendAll(forbiddenMap, categories);

        // then
        assertThat(coaches).hasSize(2);
    }
}
