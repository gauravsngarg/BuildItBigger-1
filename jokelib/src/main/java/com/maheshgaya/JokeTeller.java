package com.maheshgaya;

public class JokeTeller {
    private String joke;
    public JokeTeller(){
        this.joke = "The programmer got stuck in a shower because the instructions on the shampoo bottle said...\n"+
                "\tLather, Rinse, Repeat.";
    }

    public JokeTeller(String joke){
        this.joke = joke;
    }

    //the class getter and setter
    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke){
        this.joke = joke;
    }


}
