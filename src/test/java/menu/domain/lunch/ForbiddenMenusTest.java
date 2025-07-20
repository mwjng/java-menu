package menu.domain.lunch;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import org.junit.jupiter.api.Test;

class ForbiddenMenusTest {

    @Test
    void 메뉴가_0개일_경우_정상적으로_생성된다() {
        // when
        ForbiddenMenus forbiddenMenus = ForbiddenMenus.of(List.of());

        // then
        assertNotNull(forbiddenMenus);
    }

    @Test
    void 메뉴가_2개_이하일_경우_정상적으로_생성된다() {
        // given
        List<Menu> menus = List.of(Menu.BAGUETTE, Menu.GOCHU_JAPCHAE);

        // when
        ForbiddenMenus forbiddenMenus = ForbiddenMenus.of(menus);

        // then
        assertThat(forbiddenMenus.getMenus()).hasSize(2);
    }

    @Test
    void 메뉴가_3개_이상일_경우_예외가_발생한다() {
        // given
        List<Menu> menus = List.of(Menu.BAGUETTE, Menu.GOCHU_JAPCHAE, Menu.KIMCHI_JJIGAE);

        // when & then
        assertThatThrownBy(() -> ForbiddenMenus.of(menus))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("먹지 못하는 메뉴는 0개 이상 2개 이하만 가능합니다.");
    }

    @Test
    void 메뉴에_중복이_있으면_예외가_발생한다() {
        // given
        List<Menu> menus = List.of(Menu.BAGUETTE, Menu.BAGUETTE);

        // when & then
        assertThatThrownBy(() -> ForbiddenMenus.of(menus))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 메뉴가 존재하면 안됩니다.");
    }

    @Test
    void contains는_특정_메뉴가_먹지_못하는_메뉴에_포함되면_true_포함하지_않으면_false를_반환한다() {
        // given
        List<Menu> menus = List.of(Menu.BAGUETTE, Menu.GOCHU_JAPCHAE);
        ForbiddenMenus forbiddenMenus = ForbiddenMenus.of(menus);

        // when & then
        assertAll(
                () -> assertThat(forbiddenMenus.contains(Menu.BAGUETTE)).isTrue(),
                () -> assertThat(forbiddenMenus.contains(Menu.BULGOGI)).isFalse()
        );
    }
}