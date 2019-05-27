package me.daniel.kotlinspringbootquerydsl.dto;


import org.springframework.lang.Nullable;

public class Bus {
    private final int no;
    private final String title;

    private Bus(int no, String title) {
        this.no = no;
        this.title = title;
    }

    public int getNo() {
        return no;
    }

    public String getTitle() {
        return title;
    }


    @Nullable
    public static Bus of() {
        return null;
    }
}
