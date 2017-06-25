package com.temunide;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class JokeEngine{
    private Map<Integer,String> jokeMap;
    {
        jokeMap = new HashMap<>();
        StringBuilder builder;
        for(int i = 0; i<10;i++) {
            builder = new StringBuilder("I am a ");
            for(int k = 0;k<i;k++){
                builder.append("very ");
            }
            builder.append("good Joke");
            jokeMap.put(i,builder.toString());
        }
    }


    private JokeEngine(){

    };
   public static JokeEngine getInstance(){
        return new JokeEngine();
   }

   public String getJoke(){

       return jokeMap.get(new Random().nextInt(jokeMap.size()));
   }

}
