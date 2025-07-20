package menu.domain.lunch;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import menu.domain.category.Category;
import org.junit.jupiter.api.Test;

class MenuTest {

    @Test
    void displayName으로_Menu를_정상적으로_찾는다() {
        // given
        String displayName = "김치찌개";

        // when
        Menu menu = Menu.from(displayName);

        // then
        assertThat(menu.getDisplayName()).isEqualTo(displayName);
    }

    @Test
    void 존재하지_않는_메뉴이름으로_조회하면_예외가_발생한다() {
        // given
        String displayName = "존재하지 않는 메뉴";

        // when & then
        assertThatThrownBy(() -> Menu.from(displayName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하지 않는 메뉴입니다.");
    }

    @Test
    void 카테고리에서_금지메뉴와_중복메뉴를_제외하고_무작위로_메뉴를_선택한다() {
        // given
        Category category = Category.ASIAN;
        ForbiddenMenus forbiddenMenus = ForbiddenMenus.of(List.of(Menu.BANH_MI));
        List<Menu> selectedMenus = List.of(Menu.KHAO_PHAT, Menu.PINEAPPLE_FRIED_RICE);

        // when
        Menu pickedMenu = Menu.pickValidMenu(category, forbiddenMenus, selectedMenus);

        // then
        assertAll(
                () -> assertThat(pickedMenu).isNotNull(),
                () -> assertThat(pickedMenu.belongsTo(category)).isTrue(),
                () -> assertThat(pickedMenu).isNotIn(forbiddenMenus.getMenus()),
                () -> assertThat(pickedMenu).isNotIn(selectedMenus)
        );
    }
}