package com.fullcycle.gatalogue.admin.infrastructure;

import com.fullcycle.gatalogue.admin.application.UseCase;

public class Main {
    public static void main(String[] args){
        System.out.println("Hello World");
        System.out.println(new UseCase().execute());
    }
}
