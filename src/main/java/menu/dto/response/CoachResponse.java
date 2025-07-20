package menu.dto.response;

import java.util.List;
import menu.domain.coach.Coach;
import menu.domain.lunch.Menu;
import menu.domain.lunch.RecommendedMenus;

public class CoachResponse {

    private final String coachName;
    private final List<Menu> suggestedMenus;

    private CoachResponse(String coachName, List<Menu> suggestedMenus) {
        this.coachName = coachName;
        this.suggestedMenus = suggestedMenus;
    }

    public static CoachResponse from(Coach coach) {
        String coachName = coach.name();
        RecommendedMenus coachMenus = coach.menus();

        return new CoachResponse(coachName, coachMenus.getMenus());
    }

    public String getCoachName() {
        return this.coachName;
    }

    public List<Menu> getSuggestedMenus() {
        return this.suggestedMenus;
    }
}
