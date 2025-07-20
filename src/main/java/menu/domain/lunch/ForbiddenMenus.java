package menu.domain.lunch;

import java.util.List;

public class ForbiddenMenus {

    private final List<Menu> menus;

    private ForbiddenMenus(List<Menu> menus) {
        validate(menus);
        this.menus = menus;
    }

    public static ForbiddenMenus of(List<Menu> menus) {
        return new ForbiddenMenus(menus);
    }

    public boolean contains(Menu menu) {
        return menus.contains(menu);
    }

    public List<Menu> getMenus() {
        return this.menus;
    }

    private void validate(List<Menu> menus) {
        validateCount(menus);
        validateNoDuplicate(menus);
    }

    private void validateCount(List<Menu> menus) {
        if (menus.size() > 2) {
            throw new IllegalArgumentException("먹지 못하는 메뉴는 0개 이상 2개 이하만 가능합니다.");
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
