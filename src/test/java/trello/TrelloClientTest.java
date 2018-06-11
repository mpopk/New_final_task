package trello;

import com.kodilla.Task_final.dto.TrelloBoardDto;
import com.kodilla.Task_final.trello.client.TrelloClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class TrelloClientTest {

    private static final String ALL_BOARDS_URI = "http://test.com/boards?key=test&token=test&fields=name,id&lists=all";


    @InjectMocks
    private TrelloClient trelloClient;

    @Mock
    private RestTemplate restTemplate;



    @Test
    public void shouldReturnEmptyList() throws URISyntaxException {
        // Given
        URI uri = new URI("http://trello.com/boards?key=jakiskey&token=jakistoken&fields=name,id&lists=all");
        when(restTemplate.getForObject(uri, TrelloBoardDto[].class)).thenReturn(null);
        // When
        List<TrelloBoardDto> boards =  trelloClient.getTrelloBoards();

        // Then
        assertEquals(Collections.emptyList(), boards);

    }

}
