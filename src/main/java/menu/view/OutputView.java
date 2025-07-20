package menu.view;

import java.util.List;
import menu.domain.category.Category;
import menu.domain.lunch.Menu;
import menu.dto.response.CoachResponse;
import menu.dto.response.RecommendationResponse;

public class OutputView {

    public void printStartMessage() {
        System.out.println("점심 메뉴 추천을 시작합니다.");
    }

    public void requestCoachNames() {
        System.out.println("\n코치의 이름을 입력해 주세요. (, 로 구분)");
    }

    public void requestForbiddenMenus(String name) {
        System.out.println("\n" + name + "(이)가 못 먹는 메뉴를 입력해 주세요.");
    }

    public void printResult(RecommendationResponse recommendationResponse) {
        System.out.println("\n메뉴 추천 결과입니다.");
        System.out.println("[ 구분 | 월요일 | 화요일 | 수요일 | 목요일 | 금요일 ]");

        printCategories(recommendationResponse);

        List<CoachResponse> coaches = recommendationResponse.getCoaches();
        for (CoachResponse coachResponse : coaches) {
            printCoachName(coachResponse);
            printMenus(coachResponse);
        }

        System.out.println("\n추천을 완료했습니다.");
    }

    public void printErrorMessage(Exception e) {
        System.out.println("[ERROR] " + e.getMessage());
    }

    private void printCategories(RecommendationResponse recommendationResponse) {
        System.out.print("[ 카테고리 | ");

        List<Category> categories = recommendationResponse.getCategories();
        String[] categoryNames = categories.stream()
                .map(Category::getDisplayName)
                .toArray(String[]::new);

        String joinedCategories = String.join(" | ", categoryNames);
        System.out.println(joinedCategories + " ]");
    }

    private void printCoachName(CoachResponse coachResponse) {
        System.out.print("[ " + coachResponse.getCoachName() + " | ");
    }

    private void printMenus(CoachResponse coachResponse) {
        List<Menu> suggestedMenus = coachResponse.getSuggestedMenus();
        String[] menuNames = suggestedMenus.stream()
                .map(Menu::getDisplayName)
                .toArray(String[]::new);

        String joinedMenus = String.join(" | ", menuNames);
        System.out.println(joinedMenus + " ]");
    }
}
