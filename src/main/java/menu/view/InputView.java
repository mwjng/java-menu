package menu.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputView {

    private static final String DELIMITER = ",";

    public List<String> readCoachNames() {
        String input = Console.readLine();
        return parseDelimitedValues(input);
    }

    public List<String> readForbiddenMenus() {
        String input = Console.readLine();
        return parseDelimitedValues(input);
    }

    private List<String> parseDelimitedValues(String input) {
        return Arrays.stream(input.split(DELIMITER))
                .map(String::trim)
                .collect(Collectors.toList());
    }
}
