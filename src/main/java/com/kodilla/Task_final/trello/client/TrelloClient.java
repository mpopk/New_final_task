package com.kodilla.Task_final.trello.client;

import com.kodilla.Task_final.dto.TrelloBoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Optional.ofNullable;

@Component
public class TrelloClient {

    @Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint;

    @Value("${trello.app.key}")
    private String trelloAppKey;

    @Value("${trello.app.token}")
    private String trelloToken;

    @Value("${trello.app.username}")
    private String trelloUsername;


    @Autowired
    private RestTemplate restTemplate;

    public List<TrelloBoardDto> getTrelloBoards() {


        TrelloBoardDto[] boardsResponse = new TrelloBoardDto[0];

            boardsResponse = restTemplate.getForObject
                    (buildTrelloURI(), TrelloBoardDto[].class);

        if(boardsResponse !=null){
                return Arrays.asList(boardsResponse);
            }
            return new ArrayList<>();
    }

    private URI buildTrelloURI() {
        if(trelloApiEndpoint==null||trelloToken==null||trelloAppKey==null||trelloUsername==null){
            return null;
        }
        else {
            return UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint)
                    .path("/members/"+trelloUsername+"/boards")
                    .queryParam("key", trelloAppKey)
                    .queryParam("token", trelloToken)
                    .queryParam("fields", "name,id")
                    .build().encode().toUri();
        }
    }


}
