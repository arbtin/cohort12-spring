package mil.army.moda.springexample.example;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.JsonNode;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/examples")
public class ExampleController {

    private final ExampleService exampleService;

    public ExampleController(ExampleService exampleService) {
        this.exampleService = exampleService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Example> createExample(@RequestBody Example example) {
        exampleService.createExample(example);
        return ResponseEntity.status(201).body(example);
    }

    @GetMapping
    public List<Example> getAllExamples() {
        return exampleService.getAllExamples();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Example>> getExample(@PathVariable Long id) {
        return ResponseEntity.ok(exampleService.getExample(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Example> updateExample(@RequestBody JsonNode example, @PathVariable Long id) {
        return ResponseEntity.ok(exampleService.updateExample(example, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> removeExample(@PathVariable Long id) {
        return ResponseEntity.ok(exampleService.removeExample(id));
    }
}
