package food;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Feedback {
    private String feedback;
    private int rate;

    public Feedback(@JsonProperty("rate") int rate, @JsonProperty("feedback") String feedback) {
        this.feedback = feedback;
        this.rate = rate;
    }

    @JsonProperty("rate")
    public int getRate() {
        return rate;
    }

    @JsonProperty("feedback")
    public String getFeedback() {
        return feedback;
    }
}
