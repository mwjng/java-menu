package menu.domain.coach;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;

class CoachNamesTest {

    @Test
    void 정적_팩토리_메서드_of로_coachNames를_생성한다() {
        // given
        CoachName coach1 = CoachName.of("ab");
        CoachName coach2 = CoachName.of("abc");
        CoachName coach3 = CoachName.of("abcd");
        List<CoachName> names = List.of(coach1, coach2, coach3);

        // when
        CoachNames coachNames = CoachNames.of(names);

        // then
        assertThat(coachNames.getCoachNames()).hasSize(3);
    }

    @Test
    void 코치가_2명_미만일_경우_예외가_발생한다() {
        // given
        List<CoachName> coachNames = List.of(CoachName.of("ab"));

        // when & then
        assertThatThrownBy(() -> CoachNames.of(coachNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("코치는 2명 이상 5명 이하만 가능합니다.");
    }

    @Test
    void 코치가_5명을_초과할_경우_예외가_발생한다() {
        // given
        CoachName coach1 = CoachName.of("ab");
        CoachName coach2 = CoachName.of("abc");
        CoachName coach3 = CoachName.of("abcd");
        CoachName coach4 = CoachName.of("bcde");
        CoachName coach5 = CoachName.of("cdef");
        CoachName coach6 = CoachName.of("defh");
        List<CoachName> coachNames = List.of(coach1, coach2, coach3, coach4, coach5, coach6);

        // when & then
        assertThatThrownBy(() -> CoachNames.of(coachNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("코치는 2명 이상 5명 이하만 가능합니다.");
    }

    @Test
    void 중복된_코치_이름이_있으면_예외가_발생한다() {
        // given
        CoachName coach1 = CoachName.of("ab");
        CoachName coach2 = CoachName.of("abc");
        CoachName coach3 = CoachName.of("ab");
        List<CoachName> names = List.of(coach1, coach2, coach3);

        // when & then
        assertThatThrownBy(() -> CoachNames.of(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 코치 이름이 존재하면 안됩니다.");
    }
}