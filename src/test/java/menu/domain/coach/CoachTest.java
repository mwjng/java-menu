package menu.domain.coach;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import menu.domain.lunch.ForbiddenMenus;
import menu.domain.lunch.Menu;
import menu.domain.lunch.RecommendedMenus;
import org.junit.jupiter.api.Test;

class CoachTest {

    @Test
    void 정적_팩토리_메서드_of로_Coach를_생성한다() {
        // given
        CoachName name = CoachName.of("abcd");
        RecommendedMenus recommendedMenus = RecommendedMenus.of(List.of(
                Menu.BIBIMBAP, Menu.GIMBAP, Menu.SUSHI, Menu.PIZZA, Menu.RAMEN));
        ForbiddenMenus forbiddenMenus = ForbiddenMenus.of(List.of());

        // when
        Coach coach = Coach.of(name, recommendedMenus, forbiddenMenus);

        // then
        assertThat(coach).isNotNull();
    }
}