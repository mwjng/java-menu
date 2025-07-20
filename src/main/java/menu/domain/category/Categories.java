package menu.domain.category;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Categories {

    private static final int PICK_CATEGORY_COUNT = 5;
    private static final int MAX_CATEGORY_OCCURRENCE = 2;

    private final List<Category> categories;

    private Categories(List<Category> categories) {
        validateDuplicateLimit(categories);
        this.categories = categories;
    }

    public static Categories of(List<Category> categories) {
        return new Categories(categories);
    }

    public static Categories generateRandomly() {
        Map<Category, Integer> categoryCount = new EnumMap<>(Category.class);
        List<Category> generatedCategories = new ArrayList<>();

        while (generatedCategories.size() < PICK_CATEGORY_COUNT) {
            Category category = Category.pickRandomCategory();
            int count = categoryCount.getOrDefault(category, 0);

            if (count >= MAX_CATEGORY_OCCURRENCE) {
                continue;
            }

            generatedCategories.add(category);
            categoryCount.put(category, count + 1);
        }

        return of(generatedCategories);
    }

    public List<Category> getCategories() {
        return List.copyOf(this.categories);
    }

    private void validateDuplicateLimit(List<Category> categories) {
        Map<Category, Long> countByCategory = categories.stream()
                .collect(groupingBy(category -> category, counting()));

        countByCategory.values().stream()
                .filter(count -> count > MAX_CATEGORY_OCCURRENCE)
                .findFirst()
                .ifPresent(count -> {
                    throw new IllegalArgumentException("한 주에 같은 카테고리는 최대 2회까지만 선택할 수 있습니다.");
                });
    }
}
