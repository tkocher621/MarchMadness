package com.company;
import java.util.*;

class comp implements Comparator<Team>
{
    public int compare(Team t1, Team t2)
    {
        return t1.finalScore < t2.finalScore ? 1 : -1;
    }
}

public class Main {

    public static void main(String[] args) {

        Team team = new Team("a", 0, 0, 0, 0, 0, 0, 0, 0);
        Team team2 = new Team("anything else", 0, 0, 0, 0, 0, 0, 0, 0);

        ArrayList<Team> teams = new ArrayList<>();
        teams.add(team);
        teams.add(team2);

        Collections.sort(teams, new comp());

        System.out.println(teams.get(0).teamName);

    }
}