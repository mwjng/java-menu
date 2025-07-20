package menu.domain.lunch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import menu.domain.category.Categories;
import menu.domain.category.Category;
import menu.domain.coach.Coach;
import menu.domain.coach.CoachName;

public class Recommender {

    public List<Coach> recommendAll(Map<CoachName, ForbiddenMenus> forbiddenMap, Categories categories) {
        Map<CoachName, List<Menu>> suggestions = new HashMap<>();

        for (Category category : categories.getCategories()) {
            for (Map.Entry<CoachName, ForbiddenMenus> entry : forbiddenMap.entrySet()) {
                ForbiddenMenus forbiddenMenus = entry.getValue();
                appendMenuRecommendation(category, entry.getKey(), forbiddenMenus, suggestions);
            }
        }
        return assembleCoaches(forbiddenMap, suggestions);
    }

    private void appendMenuRecommendation(Category category, CoachName name, ForbiddenMenus forbiddenMenus,
                                          Map<CoachName, List<Menu>> suggestions) {
        List<Menu> selectedMenus = suggestions.computeIfAbsent(name, k -> new ArrayList<>());
        Menu pickedMenu = Menu.pickValidMenu(category, forbiddenMenus, selectedMenus);

        selectedMenus.add(pickedMenu);
    }

    private List<Coach> assembleCoaches(Map<CoachName, ForbiddenMenus> forbiddenMap,
                                        Map<CoachName, List<Menu>> suggestions) {
        List<Coach> coaches = new ArrayList<>();
        for (Map.Entry<CoachName, List<Menu>> entry : suggestions.entrySet()) {
            CoachName coachName = entry.getKey();
            List<Menu> selectedMenus = entry.getValue();

            ForbiddenMenus forbiddenMenus = forbiddenMap.get(coachName);
            RecommendedMenus recommendedMenus = RecommendedMenus.of(selectedMenus);

            Coach coach = Coach.of(coachName, recommendedMenus, forbiddenMenus);
            coaches.add(coach);
        }

        return coaches;
    }
}
