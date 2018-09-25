package com.example.bguarts.SharedClasses;

import java.util.List;

public class AppUser {

    private String name;
    private List<Integer> playersAges;
    private int score;
    private String email;

    public AppUser(String name, List<Integer> playersAges, int score, String email) {
        this.name = name;
        this.playersAges = playersAges;
        this.score = score;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getPlayersAges() {
        return playersAges;
    }

    public void setPlayersAges(List<Integer> playersAges) {
        this.playersAges = playersAges;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", playersAges=" + playersAges +
                ", score=" + score +
                ", email='" + email + '\'' +
                '}';
    }
}
