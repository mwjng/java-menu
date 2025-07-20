package menu.domain.lunch;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import menu.domain.category.Category;

public enum Menu {

    GYUDON("규동", Category.JAPANESE),
    UDON("우동", Category.JAPANESE),
    MISO_SOUP("미소시루", Category.JAPANESE),
    SUSHI("스시", Category.JAPANESE),
    KATSUDON("가츠동", Category.JAPANESE),
    ONIGIRI("오니기리", Category.JAPANESE),
    HAYASHI_RICE("하이라이스", Category.JAPANESE),
    RAMEN("라멘", Category.JAPANESE),
    OKONOMIYAKI("오코노미야끼", Category.JAPANESE),

    GIMBAP("김밥", Category.KOREAN),
    KIMCHI_JJIGAE("김치찌개", Category.KOREAN),
    SSAMBAP("쌈밥", Category.KOREAN),
    DOENJANG_JJIGAE("된장찌개", Category.KOREAN),
    BIBIMBAP("비빔밥", Category.KOREAN),
    KALGUKSU("칼국수", Category.KOREAN),
    BULGOGI("불고기", Category.KOREAN),
    TTEOKBOKKI("떡볶이", Category.KOREAN),
    JEYUK_BOKKEUM("제육볶음", Category.KOREAN),

    KANPUNGGI("깐풍기", Category.CHINESE),
    BOKKEUMMYEON("볶음면", Category.CHINESE),
    DONGPAYUK("동파육", Category.CHINESE),
    JJAJANGMYEON("짜장면", Category.CHINESE),
    JJAMPPONG("짬뽕", Category.CHINESE),
    MAPO_TOFU("마파두부", Category.CHINESE),
    TANGSUYUK("탕수육", Category.CHINESE),
    TOMATO_EGG_STIRFRY("토마토 달걀볶음", Category.CHINESE),
    GOCHU_JAPCHAE("고추잡채", Category.CHINESE),

    PAD_THAI("팟타이", Category.ASIAN),
    KHAO_PHAT("카오 팟", Category.ASIAN),
    NASI_GORENG("나시고렝", Category.ASIAN),
    PINEAPPLE_FRIED_RICE("파인애플 볶음밥", Category.ASIAN),
    RICE_NOODLE_SOUP("쌀국수", Category.ASIAN),
    TOM_YUM_GOONG("똠얌꿍", Category.ASIAN),
    BANH_MI("반미", Category.ASIAN),
    SPRING_ROLL("월남쌈", Category.ASIAN),
    BUN_CHA("분짜", Category.ASIAN),

    LASAGNA("라자냐", Category.WESTERN),
    GRATIN("그라탱", Category.WESTERN),
    GNOCCHI("뇨끼", Category.WESTERN),
    QUICHE("끼슈", Category.WESTERN),
    FRENCH_TOAST("프렌치 토스트", Category.WESTERN),
    BAGUETTE("바게트", Category.WESTERN),
    SPAGHETTI("스파게티", Category.WESTERN),
    PIZZA("피자", Category.WESTERN),
    PANINI("파니니", Category.WESTERN);

    private final String displayName;
    private final Category category;

    Menu(String displayName, Category category) {
        this.displayName = displayName;
        this.category = category;
    }

    public static Menu from(String displayName) {
        return Arrays.stream(values())
                .filter(menu -> menu.matches(displayName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 메뉴입니다."));
    }

    public static Menu pickValidMenu(Category category, ForbiddenMenus forbiddenMenus, List<Menu> selectedMenus) {
        List<String> menus = getAvailableMenuDisplayNames(category, forbiddenMenus, selectedMenus);
        if (menus.isEmpty()) {
            throw new IllegalArgumentException("선택 가능한 메뉴가 없습니다.");
        }

        return Menu.from(Randoms.shuffle(menus).get(0));
    }

    private static List<String> getAvailableMenuDisplayNames(Category category, ForbiddenMenus forbiddenMenus,
                                                             List<Menu> selectedMenus) {
        return Arrays.stream(values())
                .filter(m -> m.belongsTo(category))
                .filter(m -> !forbiddenMenus.contains(m))
                .filter(m -> !selectedMenus.contains(m))
                .map(Menu::getDisplayName)
                .collect(Collectors.toList());
    }

    public boolean matches(String displayName) {
        return this.displayName.equals(displayName);
    }

    public boolean belongsTo(Category category) {
        return this.category.equals(category);
    }

    public String getDisplayName() {
        return this.displayName;
    }
}
