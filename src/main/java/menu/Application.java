package menu;

import menu.controller.RecommendationController;
import menu.domain.lunch.Recommender;
import menu.service.RecommendationService;
import menu.view.InputView;
import menu.view.OutputView;

public class Application {
    public static void main(String[] args) {
        Recommender recommender = new Recommender();
        RecommendationService recommendationService = new RecommendationService(recommender);

        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        RecommendationController recommendationController = new RecommendationController(
                inputView, outputView, recommendationService);
        recommendationController.run();
    }
}
