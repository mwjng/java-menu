package menu.domain.category;

import static java.util.stream.Collectors.groupingBy;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

class CategoriesTest {

    @Test
    void generateRandomly는_5개의_카테고리를_반환한다() {
        // when
        Categories categories = Categories.generateRandomly();

        // then
        assertThat(categories.getCategories()).hasSize(5);
    }

    @Test
    void generateRandomly는_같은_카테고리가_3개_이상_중복되지_않는다() {
        // when
        Categories categories = Categories.generateRandomly();

        // then
        Map<Category, Long> countByCategory = categories.getCategories().stream()
                .collect(groupingBy(category -> category, Collectors.counting()));

        assertThat(countByCategory.values()).allMatch(count -> count < 3);
    }

    @Test
    void 같은_카테고리가_3개_이상_중복되면_예외가_발생한다() {
        // given
        List<Category> categories = List.of(
                Category.CHINESE, Category.KOREAN, Category.CHINESE, Category.CHINESE, Category.WESTERN);

        // when & then
        assertThatThrownBy(() -> Categories.of(categories))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("한 주에 같은 카테고리는 최대 2회까지만 선택할 수 있습니다.");
    }
}