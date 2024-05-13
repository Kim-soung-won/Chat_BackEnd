package com.kkk.javaapp;

import com.kkk.kotlinapp.testDTO;
import com.kkk.kotlinapp.상속.Counter;


public class ko {
    public void aa(){
        Counter.INSTANCE.up();
        System.out.println(Counter.INSTANCE.getCount());
    }
}

