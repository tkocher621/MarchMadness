package com.company;

import java.util.*;
import java.io.*;

class comp implements Comparator<Team>
{
    public int compare(Team t1, Team t2)
    {
        return t1.finalScore < t2.finalScore ? 1 : -1;
    }
}

public class Main {

    private static float ParseStat(String in)
    {
        String[] data = in.split(" ");

        float temp = -1f;

        for (String b : data)
        {
            try
            {
                if (b.contains("."))
                {
                    temp = Float.parseFloat(b);
                }
            }
            catch (Exception x)
            {
                continue;
            }
        }
        return temp;
    }

    private static boolean TryParseFloat(String f)
    {
        try
        {
            Float.parseFloat(f);
            return true;
        }
        catch (Exception x)
        {
            return false;
        }
    }

    private static float GetStat(String file, String teamName) throws IOException
    {
        Scanner scan = new Scanner(new File(file));
        while (scan.hasNext())
        {
            String a = scan.nextLine();
            if (a.contains(teamName)) return ParseStat(a);
        }
        return -1;
    }

    private static String GetTeamName(String line)
    {
        String name = new String();

        boolean isName = false;

        for (int i = 0; i < line.length(); i++)
        {
            String b = Character.toString(line.charAt(i));

            if (!TryParseFloat(b))
            {
                isName = true;
            }
            else if (TryParseFloat(b) && isName)
            {
                isName = false;
                return name.trim();
            }

            if (isName)
            {
                name += b;
            }
        }
        return name.trim();
    }

    private static List<String> GetTeamNames(String fileName) throws IOException
    {
        List<String> teamNames = new ArrayList<>();

        Scanner scan = new Scanner(new File(fileName));
        while (scan.hasNext())
        {
            teamNames.add(GetTeamName(scan.nextLine()));
        }

        return teamNames;
    }

    private static List<Team> CalcTeams() throws IOException
    {
        List<String> teamNames = GetTeamNames("3PointPercentage.txt");
        List<Team> teams = new ArrayList<>();

        for (String team : teamNames)
        {
            teams.add(new Team(team,
                    GetStat("ScoringMargin.txt", team),
                    GetStat("FieldGoalPercentage.txt", team),
                    GetStat("ReboundsPerGame.txt", team),
                    GetStat("turnOverMargin.txt", team),
                    GetStat("FreeThrowPercentage.txt", team),
                    GetStat("AssistsPerGame.txt", team),
                    GetStat("StealsPerGame.txt", team),
                    GetStat("3PointPercentage.txt", team)));
        }
        return teams;
    }

    public static void main(String[] args) throws IOException
    {
        List<Team> teams = CalcTeams();
        Collections.sort(teams, new comp());
        // Index 0 is the best team
    }
}