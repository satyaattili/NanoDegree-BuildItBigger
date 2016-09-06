package in.mobileappdev.jokeslib;

import java.util.Random;

public class JokesProvider {

    private String j1 = "My dog used to chase people on a bike a lot. It got so bad, finally I had to take his bike away.";
    private String j2 = "There is nothing worse than child polio. No wait, there's women's soccer.";
    private String j3 = "After many years of studying at a university, I’ve finally become a PhD… or Pizza Hut Deliveryman as people call it";


    public JokesProvider() {
    }

    public static JokesProvider getJokesProvider(){
        return new JokesProvider();
    }

    public Joke getJoke(){



        String [] jokes= {j1,j2,j3};

        int min = 0;
        int max = jokes.length-1;

        Random r = new Random();
        int joke = r.nextInt(max - min + 1) + min;

        Joke selectedJoke = new Joke(JokeCategory.MOVIE, jokes[joke]);

        return selectedJoke;

    }
}
