package menu.dto.request;

import java.util.List;
import menu.domain.lunch.Menu;

public class CoachRequest {

    private final String name;
    private final List<Menu> forbiddenMenus;

    public CoachRequest(String name, List<Menu> forbiddenMenus) {
        this.name = name;
        this.forbiddenMenus = forbiddenMenus;
    }

    public String getName() {
        return this.name;
    }

    public List<Menu> getForbiddenMenus() {
        return this.forbiddenMenus;
    }
}
