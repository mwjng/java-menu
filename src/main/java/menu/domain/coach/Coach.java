package menu.domain.coach;

import menu.domain.lunch.ForbiddenMenus;
import menu.domain.lunch.Lunch;
import menu.domain.lunch.RecommendedMenus;

public class Coach {

    private final CoachName coachName;
    private final Lunch lunch;

    private Coach(CoachName coachName, Lunch lunch) {
        this.coachName = coachName;
        this.lunch = lunch;
    }

    public static Coach of(CoachName coachName, RecommendedMenus recommendedMenus,
                           ForbiddenMenus forbiddenMenus) {
        Lunch lunch = Lunch.of(recommendedMenus, forbiddenMenus);
        return new Coach(coachName, lunch);
    }

    public RecommendedMenus menus() {
        return lunch.getRecommendedMenus();
    }

    public String name() {
        return coachName.getValue();
    }
}
