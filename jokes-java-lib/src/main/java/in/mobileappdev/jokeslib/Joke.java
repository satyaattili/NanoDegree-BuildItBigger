package in.mobileappdev.jokeslib;

/**
 * Created by satyanarayana.avv on 30-08-2016.
 */

public class Joke {

    private JokeCategory category;
    private String joke;

    public Joke(JokeCategory category, String joke) {
        this.category = category;
        this.joke = joke;
    }

    public JokeCategory getCategory() {
        return category;
    }

    public void setCategory(JokeCategory category) {
        this.category = category;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }
}
