package menu.domain.lunch;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;

class LunchTest {

    @Test
    void 추천_메뉴에_금지된_메뉴가_포함되지_않으면_정상적으로_Lunch를_생성한다() {
        // given
        RecommendedMenus recommendedMenus = RecommendedMenus.of(
                List.of(Menu.BAGUETTE, Menu.QUICHE, Menu.BANH_MI, Menu.BIBIMBAP, Menu.DOENJANG_JJIGAE)
        );
        ForbiddenMenus forbiddenMenus = ForbiddenMenus.of(
                List.of(Menu.BOKKEUMMYEON, Menu.BULGOGI)
        );

        // when
        Lunch lunch = Lunch.of(recommendedMenus, forbiddenMenus);

        // then
        assertThat(lunch.getRecommendedMenus()).isEqualTo(recommendedMenus);
    }

    @Test
    void 추천_메뉴에_금지된_메뉴가_포함되면_예외가_발생한다() {
        // given
        RecommendedMenus recommendedMenus = RecommendedMenus.of(
                List.of(Menu.BAGUETTE, Menu.QUICHE, Menu.BANH_MI, Menu.BIBIMBAP, Menu.DOENJANG_JJIGAE)
        );
        ForbiddenMenus forbiddenMenus = ForbiddenMenus.of(
                List.of(Menu.BAGUETTE, Menu.BULGOGI)
        );

        // when & then
        assertThatThrownBy(() -> Lunch.of(recommendedMenus, forbiddenMenus))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("금지된 메뉴는 추천할 수 없습니다.");
    }
}