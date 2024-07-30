package tech.techiocean;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ConcurrentDogGetter implements DogsGetter{
    DogsResource dogsResource = new DogsResource();

    @Override
    public Dog getDog() {
        Instant start = Instant.now();

        var futures = new ArrayList<Future<Dog>>();
        try (ExecutorService executorService = Executors.newFixedThreadPool(8)){
             futures.add( executorService.submit(() -> dogsResource.getDog1()));
             futures.add( executorService.submit(() -> dogsResource.getDog2()));
             futures.add( executorService.submit(() -> dogsResource.getDog3()));
             futures.add( executorService.submit(() -> dogsResource.getDog4()));
             futures.add( executorService.submit(() -> dogsResource.getDog5()));
        }catch (Exception ex){}
        Dog dog1 = futures.stream()
                .map(ConcurrentDogGetter::getDog)
                .min(Comparator.comparingInt(dog -> dog.getId()))
                .orElseThrow();
        Instant end = Instant.now();

        System.out.printf("method executed in %d\n", Duration.between(start, end).toMillis());
        return dog1;
    }

    private static Dog getDog(Future<Dog> future) {
        try {
            Dog dog = future.get();
            return dog;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
