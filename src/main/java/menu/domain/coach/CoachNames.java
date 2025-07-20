package menu.domain.coach;

import java.util.List;

public class CoachNames {

    private final List<CoachName> coachNames;

    private CoachNames(List<CoachName> coachNames) {
        validate(coachNames);
        this.coachNames = coachNames;
    }

    public static CoachNames of(List<CoachName> coachNames) {
        return new CoachNames(coachNames);
    }

    public List<CoachName> getCoachNames() {
        return List.copyOf(coachNames);
    }

    private void validate(List<CoachName> coachNames) {
        validateCount(coachNames);
        validateNoDuplicateName(coachNames);
    }

    private void validateCount(List<CoachName> coachNames) {
        if (isInvalidCoachCount(coachNames)) {
            throw new IllegalArgumentException("코치는 2명 이상 5명 이하만 가능합니다.");
        }
    }

    private boolean isInvalidCoachCount(List<CoachName> coachNames) {
        return coachNames.size() < 2 || coachNames.size() > 5;
    }

    private void validateNoDuplicateName(List<CoachName> coachNames) {
        if (hasDuplicate(coachNames)) {
            throw new IllegalArgumentException("중복된 코치 이름이 존재하면 안됩니다.");
        }
    }

    private boolean hasDuplicate(List<CoachName> coachNames) {
        return coachNames.size() != coachNames.stream()
                .distinct()
                .count();
    }
}
