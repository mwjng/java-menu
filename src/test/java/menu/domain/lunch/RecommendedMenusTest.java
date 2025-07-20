package menu.domain.lunch;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import org.junit.jupiter.api.Test;

class RecommendedMenusTest {

    @Test
    void 메뉴가_5개면_정상적으로_생성된다() {
        // given
        List<Menu> menus = List.of(
                Menu.BIBIMBAP, Menu.GIMBAP, Menu.SUSHI, Menu.PIZZA, Menu.RAMEN);

        // when
        RecommendedMenus recommendedMenus = RecommendedMenus.of(menus);

        // then
        assertNotNull(recommendedMenus);
    }

    @Test
    void 메뉴가_5개가_아니면_예외가_발생한다() {
        // given
        List<Menu> menus = List.of(
                Menu.BIBIMBAP, Menu.GIMBAP, Menu.SUSHI);

        // when & then
        assertThatThrownBy(() -> RecommendedMenus.of(menus))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("추천 메뉴는 5개여야 합니다.");
    }

    @Test
    void 중복된_메뉴가_존재하면_예외가_발생한다() {
        // given
        List<Menu> menus = List.of(
                Menu.BIBIMBAP, Menu.BIBIMBAP, Menu.SUSHI, Menu.PIZZA, Menu.RAMEN);

        // when & then
        assertThatThrownBy(() -> RecommendedMenus.of(menus))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 메뉴가 존재하면 안됩니다.");
    }
}