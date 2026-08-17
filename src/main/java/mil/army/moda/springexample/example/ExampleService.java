package mil.army.moda.springexample.example;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Optional;

@Service
public class ExampleService {

    private final ExampleRepository exampleRepository;
    private final ObjectMapper mapper;

    public ExampleService(ExampleRepository exampleRepository, ObjectMapper mapper) {
        this.exampleRepository = exampleRepository;
        this.mapper = mapper;
    }

    public Example createExample(Example example) { return exampleRepository.save(example); }

    public List<Example> getAllExamples() {
        return exampleRepository.findAll();
    }

    public Optional<Example> getExample(Long id) {
        return exampleRepository.findById(id);
    }

    public Example updateExample(JsonNode updatedExample, Long id) {
        Example foundExample = getExample(id).orElseThrow(ExampleNotFoundException::new);
        mapper.readerForUpdating(foundExample).readValue(updatedExample);
        return createExample(foundExample);
    }

    public HttpStatus removeExample(Long id) {
        Example foundExample = getExample(id).orElseThrow(ExampleNotFoundException::new);
        exampleRepository.deleteById(foundExample.getId());
        return HttpStatus.NO_CONTENT;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class ExampleNotFoundException extends RuntimeException {
        public ExampleNotFoundException() {
            super("Example not found");
        }
    }
}
