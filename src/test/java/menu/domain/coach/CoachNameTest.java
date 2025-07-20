package menu.domain.coach;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CoachNameTest {

    @ParameterizedTest
    @ValueSource(strings = {"ab", "abc", "abcd"})
    void 정적_팩토리_메서드_of로_이름을_생성한다(String input) {
        // when
        CoachName coachName = CoachName.of(input);

        // then
        assertThat(coachName).isNotNull();
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "abcde"})
    void 이름이_2글자_미만이거나_4글자를_초과하면_예외가_발생한다(String input) {
        // when & then
        assertThatThrownBy(() -> CoachName.of(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 최소 2글자, 최대 4글자로 입력해야 합니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "   ", "   "})
    void 이름에_공백만_있으면_예외가_발생한다(String input) {
        // when & then
        assertThatThrownBy(() -> CoachName.of(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름에 공백만 있으면 안됩니다.");
    }
}