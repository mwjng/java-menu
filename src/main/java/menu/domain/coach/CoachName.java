package menu.domain.coach;

import java.util.Objects;

public class CoachName {

    private final String value;

    private CoachName(String value) {
        validate(value);
        this.value = value;
    }

    public static CoachName of(String value) {
        return new CoachName(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CoachName other = (CoachName) o;
        return Objects.equals(value, other.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    public String getValue() {
        return this.value;
    }

    private void validate(String value) {
        validateNotBlank(value);
        validateLength(value);
    }

    private void validateNotBlank(String value) {
        if (value.isBlank()) {
            throw new IllegalArgumentException("이름에 공백만 있으면 안됩니다.");
        }
    }

    private void validateLength(String value) {
        if (isInvalidNameLength(value)) {
            throw new IllegalArgumentException("이름은 최소 2글자, 최대 4글자로 입력해야 합니다.");
        }
    }

    private boolean isInvalidNameLength(String value) {
        return value.length() < 2 || value.length() > 4;
    }
}
