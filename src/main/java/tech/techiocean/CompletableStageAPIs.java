package tech.techiocean;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CompletableStageAPIs implements DogsGetter{
    DogsResource dogsResource = new DogsResource();

    @Override
    public Dog getDog() {
        Instant start = Instant.now();
        DogConsumer dogConsumer = new DogConsumer();
        var completableFutures = new ArrayList<CompletableFuture<Dog>>();

        completableFutures.add(CompletableFuture.supplyAsync(() -> dogsResource.getDog1()));
        completableFutures.add(CompletableFuture.supplyAsync(() -> dogsResource.getDog2()));
        completableFutures.add(CompletableFuture.supplyAsync(() -> dogsResource.getDog3()));
        completableFutures.add(CompletableFuture.supplyAsync(() -> dogsResource.getDog4()));
        completableFutures.add(CompletableFuture.supplyAsync(() -> dogsResource.getDog5()));

//        Dog dog1 = completableFutures.get(0).join();
        List<CompletableFuture<Void>> voids = new ArrayList<>();
        completableFutures.forEach(completableFuture -> voids.add(completableFuture.thenAccept(dogConsumer)));
        CompletableFuture<Void> allOf = CompletableFuture.allOf(voids.toArray(new CompletableFuture[5]));
        System.out.println(dogConsumer.getDogs());
        allOf.join(); // blocking code!
//        try{
//            Thread.sleep(1000); // blocking code!
//        }catch (Exception ex){}
        Instant end = Instant.now();

        System.out.printf("method executed in %dms\n", Duration.between(start, end).toMillis());
        System.out.println(dogConsumer.getDogs());

        return null;
    }
}
