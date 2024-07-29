package tech.techiocean;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SyncDogGetter implements DogsGetter{
    DogsResource dogsResource = new DogsResource();
    @Override
    public Dog getDog() {
        Instant start = Instant.now();

        Dog dog1 = dogsResource.getDog1();
        Dog dog2 = dogsResource.getDog2();
        Dog dog3 = dogsResource.getDog3();
        Dog dog4 = dogsResource.getDog4();
        Dog dog5 = dogsResource.getDog5();
        List<Dog> dogs = new ArrayList<>(Arrays.asList(dog1, dog2, dog3, dog4, dog5));

        Dog dog6 = dogs.stream()
                .min(Comparator.comparingInt(dog -> dog.getId()))
                .orElseThrow();
        Instant end = Instant.now();
        System.out.printf("method executed in %s\n", Duration.between(start, end).toMillis());

        return dog6;
    }
}
