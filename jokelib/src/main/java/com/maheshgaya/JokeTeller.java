package com.maheshgaya;

import java.util.Random;

public class JokeTeller {
    private String joke;
    //Joke from http://stackoverflow.com/questions/234075/what-is-your-best-programmer-joke
    private static final String[] mJokes = new String[]{
            "A SQL query goes into a bar, walks up to two tables and asks, \"Can I join you?\"",
            "Q: how many programmers does it take to change a light bulb?\n" +
                    "\n" +
                    "A: none, that's a hardware problem",
            "When your hammer is C++, everything begins to look like a thumb.",
            "The programmer got stuck in a shower because the instructions on the shampoo bottle said...\n"+
                    "\tLather, Rinse, Repeat."

    };
    public JokeTeller(){
        Random rand = new Random();
        int randNumber = rand.nextInt(4);
        this.joke = mJokes[randNumber];
    }

    public JokeTeller(String joke){
        this.joke = joke;
    }

    //the class getter and setter
    public String getFunnyJoke() {
        return joke;
    }

    public void setFunnyJoke(String joke){
        this.joke = joke;
    }


}
