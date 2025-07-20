package menu.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import menu.domain.coach.CoachName;
import menu.domain.coach.CoachNames;
import menu.domain.lunch.ForbiddenMenus;
import menu.domain.lunch.Menu;
import menu.dto.request.CoachRequest;
import menu.dto.request.CoachesRequest;
import menu.dto.response.RecommendationResponse;
import menu.service.RecommendationService;
import menu.view.InputView;
import menu.view.OutputView;

public class RecommendationController {

    private final InputView inputView;
    private final OutputView outputView;
    private final RecommendationService recommendationService;

    public RecommendationController(InputView inputView, OutputView outputView,
                                    RecommendationService recommendationService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.recommendationService = recommendationService;
    }

    public void run() {
        outputView.printStartMessage();

        CoachesRequest coachesRequest = createCoachesRequest();
        RecommendationResponse recommendationResponse = recommendationService.recommend(coachesRequest);

        outputView.printResult(recommendationResponse);
    }

    private CoachesRequest createCoachesRequest() {
        CoachNames coachNames = getCoachNamesFromInput();

        List<CoachRequest> coachRequests = new ArrayList<>();
        for (CoachName coachName : coachNames.getCoachNames()) {
            String name = coachName.getValue();
            List<Menu> menus = getForbiddenMenusFromInput(name).getMenus();

            coachRequests.add(new CoachRequest(name, menus));
        }

        return new CoachesRequest(coachRequests);
    }

    private CoachNames getCoachNamesFromInput() {
        while (true) {
            outputView.requestCoachNames();
            try {
                List<String> inputCoachNames = inputView.readCoachNames();
                List<CoachName> coachNames = inputCoachNames.stream()
                        .map(CoachName::of)
                        .collect(Collectors.toList());

                return CoachNames.of(coachNames);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e);
            }
        }
    }

    private ForbiddenMenus getForbiddenMenusFromInput(String name) {
        while (true) {
            outputView.requestForbiddenMenus(name);
            try {
                List<String> inputForbiddenMenus = inputView.readForbiddenMenus();
                List<Menu> forbiddenMenus = inputForbiddenMenus.stream()
                        .map(Menu::from)
                        .collect(Collectors.toList());

                return ForbiddenMenus.of(forbiddenMenus);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e);
            }
        }
    }
}
