package tech.techiocean;

import java.util.Random;

public class DogsResource {
    Random random = new Random();
    public Dog getDog1(){
        try {
            Thread.sleep(random.nextInt(1, 100));
        }catch (Exception ex){}
        return Dog.builder()
                .id(1)
                .name("dog1")
                .build();
    }
    public Dog getDog2(){
        try {
            Thread.sleep(random.nextInt(1, 100));
        }catch (Exception ex){}
        return Dog.builder()
                .id(2)
                .name("dog2")
                .build();
    }
    public Dog getDog3(){
        try {
            Thread.sleep(random.nextInt(1, 100));
        }catch (Exception ex){}
        return Dog.builder()
                .id(3)
                .name("dog3")
                .build();
    }
    public Dog getDog4(){
        try {
            Thread.sleep(random.nextInt(1, 100));
        }catch (Exception ex){}
        return Dog.builder()
                .id(4)
                .name("dog4")
                .build();
    }
    public Dog getDog5(){
        try {
            Thread.sleep(random.nextInt(1, 100));
        }catch (Exception ex){}
        return Dog.builder()
                .id(5)
                .name("dog5")
                .build();
    }
}
