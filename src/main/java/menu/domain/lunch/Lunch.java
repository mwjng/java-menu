package menu.domain.lunch;

import java.util.List;

public class Lunch {

    private final RecommendedMenus recommendedMenus;

    private Lunch(RecommendedMenus recommendedMenus, ForbiddenMenus forbiddenMenus) {
        validateNoForbiddenMenuSuggested(recommendedMenus, forbiddenMenus);
        this.recommendedMenus = recommendedMenus;
    }

    public static Lunch of(RecommendedMenus recommendedMenus, ForbiddenMenus forbiddenMenus) {
        return new Lunch(recommendedMenus, forbiddenMenus);
    }

    public RecommendedMenus getRecommendedMenus() {
        return this.recommendedMenus;
    }

    private void validateNoForbiddenMenuSuggested(RecommendedMenus recommendedMenus,
                                                  ForbiddenMenus forbiddenMenus) {
        List<Menu> menus = recommendedMenus.getMenus();
        menus.stream()
                .filter(forbiddenMenus::contains)
                .findFirst()
                .ifPresent(menu -> {
                    throw new IllegalArgumentException("금지된 메뉴는 추천할 수 없습니다.");
                });
    }
}
