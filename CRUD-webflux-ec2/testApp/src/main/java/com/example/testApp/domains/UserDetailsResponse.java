package com.example.testApp.domains;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.netty.util.internal.StringUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsResponse {

    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("preferredFirstName")
    private String preferredFirstName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("preferredLastName")
    private String preferredLastName;

    public String[] getNames() {
        String first = StringUtils.isEmpty(preferredFirstName) ? firstName : preferredFirstName;
        String last = StringUtils.isEmpty(preferredLastName) ? lastName : preferredLastName;
        return new String[] {first, last};
    }
}
