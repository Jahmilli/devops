package com.example.testApp.components;

import com.example.testApp.domains.UserDetailsResponse;
import com.example.testApp.nonfunctional.exceptions.EmployeeException;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.inject.Inject;

@Component
public class DetailsComponent {

    private final WebClient webClient;

    @Inject
    public DetailsComponent(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<String[]> getUserDetails(String id) {

        return queryForDetails("123")
                .map(res -> {
                    System.out.println("response is " + res.toString());
                    return res[0].getNames();
                });
    }

    private Mono<UserDetailsResponse[]> queryForDetails(String id) {

        return webClient.get()
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .flatMap(clientResponse -> {
                    System.out.println("Client Response is " + clientResponse.toString());
                    return clientResponse.bodyToMono(UserDetailsResponse[].class);
                })
                .map(res -> {
                    System.out.println("RES IS " + res.toString());
                    return res;
                })
                .onErrorMap(err -> {
                    System.out.println("An error occurred " + err.toString());
                    return new EmployeeException(HttpStatus.INTERNAL_SERVER_ERROR.value(), err.getMessage());
                });
    }
}
