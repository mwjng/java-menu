package menu.domain.category;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.EnumSet;
import org.junit.jupiter.api.Test;

class CategoryTest {

    @Test
    void pickRandomCategory는_Category_값_중_하나를_반환한다() {
        // given
        EnumSet<Category> categories = EnumSet.allOf(Category.class);

        // when
        Category category = Category.pickRandomCategory();

        // then
        assertThat(categories).contains(category);
    }
}