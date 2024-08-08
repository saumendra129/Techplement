package test.abhi.controller;




import test.abhi.service.QuoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/quotes")
public class QuoteController {

    private final QuoteService quoteService;

    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @GetMapping("/random")
    public ResponseEntity<String> getRandomQuote() {
        try {
            String quote = quoteService.getRandomQuote();
            if (quote != null && !quote.isEmpty()) {
                return ResponseEntity.ok(quote);
            } else {
                return ResponseEntity.status(404).body("No quote found.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error fetching quote: " + e.getMessage());
        }
    }

    @GetMapping("/search")
    public ResponseEntity<String> searchQuotesByAuthor(@RequestParam String author) {
        try {
            String quotes = quoteService.searchQuotesByAuthor(author);
            if (quotes != null && !quotes.isEmpty()) {
                return ResponseEntity.ok(quotes);
            } else {
                return ResponseEntity.status(404).body("No quotes found for this author.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error searching quotes: " + e.getMessage());
        }
    }
}
