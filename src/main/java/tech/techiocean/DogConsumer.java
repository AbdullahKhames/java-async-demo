package tech.techiocean;

import lombok.Data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

@Data
public class DogConsumer implements Consumer<Dog> {
    private List<Dog> dogs = new CopyOnWriteArrayList<>();
    @Override
    public void accept(Dog dog) {
        dogs.add(dog);
        Path dogFile = Paths.get("src/main/resources/dog.txt");
        try {
            Files.write(dogFile, dog.toString().getBytes("UTF-8"), Files.exists(dogFile) ? StandardOpenOption.APPEND : StandardOpenOption.CREATE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
