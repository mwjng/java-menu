package menu.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import menu.domain.category.Categories;
import menu.domain.coach.Coach;
import menu.domain.coach.CoachName;
import menu.domain.lunch.ForbiddenMenus;
import menu.domain.lunch.Menu;
import menu.domain.lunch.Recommender;
import menu.dto.request.CoachRequest;
import menu.dto.request.CoachesRequest;
import menu.dto.response.CoachResponse;
import menu.dto.response.RecommendationResponse;

public class RecommendationService {

    private final Recommender recommender;

    public RecommendationService(Recommender recommender) {
        this.recommender = recommender;
    }

    public RecommendationResponse recommend(CoachesRequest coachesRequest) {
        Categories categories = Categories.generateRandomly();
        List<Coach> coaches = mapToRecommendedCoaches(coachesRequest.getCoachRequests(), categories);

        List<CoachResponse> coachResponses = coaches.stream()
                .map(CoachResponse::from)
                .collect(Collectors.toList());

        return new RecommendationResponse(categories.getCategories(), coachResponses);
    }

    private List<Coach> mapToRecommendedCoaches(List<CoachRequest> coachRequests, Categories categories) {
        Map<CoachName, ForbiddenMenus> forbiddenMaps = new LinkedHashMap<>();

        for (CoachRequest coachRequest : coachRequests) {
            String name = coachRequest.getName();
            List<Menu> menus = coachRequest.getForbiddenMenus();

            CoachName coachName = CoachName.of(name);
            ForbiddenMenus forbiddenMenus = ForbiddenMenus.of(menus);

            forbiddenMaps.put(coachName, forbiddenMenus);
        }

        return recommender.recommendAll(forbiddenMaps, categories);
    }
}
