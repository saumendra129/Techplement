package test.abhi.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class QuoteService {

    @Value("${quotes.api.url}")
    private String quotesApiUrl;

    private final RestTemplate restTemplate;

    public QuoteService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getRandomQuote() {
        ResponseEntity<String> response = restTemplate.getForEntity(quotesApiUrl, String.class);
        return response.getBody();
    }

    public String searchQuotesByAuthor(String author) {
        ResponseEntity<String> response = restTemplate.getForEntity(quotesApiUrl + "?author=" + author, String.class);
        return response.getBody();
    }
}
