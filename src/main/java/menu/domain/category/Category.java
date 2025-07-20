package menu.domain.category;

import camp.nextstep.edu.missionutils.Randoms;

public enum Category {

    JAPANESE("일식"),
    KOREAN("한식"),
    CHINESE("중식"),
    ASIAN("아시안"),
    WESTERN("양식");

    private final String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

    public static Category pickRandomCategory() {
        Category[] categories = Category.values();
        int randomIndex = Randoms.pickNumberInRange(1, categories.length);
        return categories[randomIndex - 1];
    }

    public String getDisplayName() {
        return this.displayName;
    }
}
