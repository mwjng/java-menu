package menu.dto.response;

import java.util.List;
import menu.domain.category.Category;

public class RecommendationResponse {

    private final List<Category> categories;
    private final List<CoachResponse> coaches;

    public RecommendationResponse(List<Category> categories, List<CoachResponse> coaches) {
        this.categories = categories;
        this.coaches = coaches;
    }

    public List<Category> getCategories() {
        return this.categories;
    }

    public List<CoachResponse> getCoaches() {
        return this.coaches;
    }
}
