package pl.sda.iss.DAO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

@Data
public class JsonPassTimesReader {

    private String message;
    @JsonProperty("request")
    private JsonNode request;
    @JsonProperty("response")
    private JsonNode response;


}
