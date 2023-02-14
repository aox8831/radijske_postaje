package com.example.music_projekt;

public class Uporabnik {
    private static String eposta;
    private static String username;

    public Uporabnik( String username, String eposta) {
        this.eposta = eposta;
        this.username = username;
    }

    public static String getEposta() {
        return eposta;
    }

    public static String getUsername() {
        return username;
    }
}
