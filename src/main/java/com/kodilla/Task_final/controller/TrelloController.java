package com.kodilla.Task_final.controller;


import com.kodilla.Task_final.dto.TrelloBoardDto;
import com.kodilla.Task_final.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/v1/trello")
public class TrelloController {


    @Autowired
    private TrelloClient trelloClient;

    @RequestMapping(method = RequestMethod.GET, value = "/getTrelloBoards/{param}")
    public void getTrelloBoards() {
 String param = null;
        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();
        if(param=="id"){
            List<TrelloBoardDto> collect = trelloBoards.stream().filter(t->!t.getId().isEmpty()).collect(Collectors.toList());
            collect.forEach(trelloBoardDto -> System.out.println(trelloBoardDto));
        }else if(param=="name"){
            List<TrelloBoardDto> collect = trelloBoards.stream().filter(t->!t.getName().isEmpty()).collect(Collectors.toList());
            collect.forEach(trelloBoardDto -> System.out.println(trelloBoardDto));
        } else if(param=="kodilla"){
            List<TrelloBoardDto> collect = trelloBoards.stream().filter(trelloBoardDto -> trelloBoardDto.getName().contains("kodilla")).collect(Collectors.toList());
            collect.forEach(trelloBoardDto -> System.out.println(trelloBoardDto));
        }

    }




}
