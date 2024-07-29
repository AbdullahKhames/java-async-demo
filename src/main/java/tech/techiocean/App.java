package tech.techiocean;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        DogsGetter dogsGetter = new ConcurrentDogGetter();
        DogsGetter dogsGetter1 = new SyncDogGetter();

        System.out.println(dogsGetter.getDog());
        System.out.println(dogsGetter1.getDog());
    }


}
