package menu.domain.lunch;

import java.util.List;

public class RecommendedMenus {

    private static final int REQUIRED_SIZE = 5;

    private final List<Menu> menus;

    private RecommendedMenus(List<Menu> menus) {
        validate(menus);
        this.menus = menus;
    }

    public static RecommendedMenus of(List<Menu> menus) {
        return new RecommendedMenus(menus);
    }

    public List<Menu> getMenus() {
        return List.copyOf(menus);
    }

    private void validate(List<Menu> menus) {
        validateCount(menus);
        validateNoDuplicate(menus);
    }

    private void validateCount(List<Menu> menus) {
        if (menus.size() != REQUIRED_SIZE) {
            throw new IllegalArgumentException("추천 메뉴는 5개여야 합니다.");
        }
    }

    private void validateNoDuplicate(List<Menu> menus) {
        if (hasDuplicate(menus)) {
            throw new IllegalArgumentException("중복된 메뉴가 존재하면 안됩니다.");
        }
    }

    private boolean hasDuplicate(List<Menu> menus) {
        return menus.size() != menus.stream()
                .distinct()
                .count();
    }
}
