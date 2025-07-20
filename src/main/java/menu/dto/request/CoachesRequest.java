package menu.dto.request;

import java.util.List;

public class CoachesRequest {

    private final List<CoachRequest> coachRequests;

    public CoachesRequest(List<CoachRequest> coachRequests) {
        this.coachRequests = coachRequests;
    }

    public List<CoachRequest> getCoachRequests() {
        return this.coachRequests;
    }
}
