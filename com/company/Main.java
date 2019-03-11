package com.company;
import javax.imageio.IIOException;
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
                if (b.contains(".")) temp = Float.parseFloat(b);
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
        // Use any file to get all potential team names
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
                    GetStat("3PointPercentage.txt", team),
                    GetStat("StrengthOfSchedule.txt", team)));
        }
        return teams;
    }

    private static List<Team> GenerateBracket(List<Team> teams)
    {
        boolean toggle = true;
        List<Team> team1 = new ArrayList<>();
        List<Team> team2 = new ArrayList<>();

        for (int i = 0; i < teams.size(); i++)
        {
            if (toggle) team1.add(teams.get(i));
            else team2.add(teams.get(i));
            toggle = !toggle;
        }

        List<Team> winners = new ArrayList<>();

        for (int i = 0; i < team1.size(); i++)
        {
            System.out.println(team1.get(i).teamName + " | " + team1.get(i).finalScore);
            System.out.println(team2.get(i).teamName + " | " + team2.get(i).finalScore);

            winners.add(((team1.get(i).finalScore > team2.get(i).finalScore) ? team1 : team2).get(i));
            System.out.println("Round Winner: " + winners.get(i).teamName);
            if (i != team1.size() - 1) System.out.println("-------------");
        }
        if (winners.size() == 2)
        {
            System.out.println("\n----------CHAMPION----------");
            Team winner = (winners.get(0).finalScore > winners.get(1).finalScore) ?
                    winners.get(0) : winners.get(1);
            System.out.println(winner.teamName + " | " + winner.finalScore);
            return null;
        }
        else
        {
            System.out.println("\n----------NEXT ROUND----------\n");
            GenerateBracket(winners);
        }
        return winners;
    }

    private static List<Team> GetPlayingTeams(List<Team> teams, List<String> names) throws IOException
    {
        List<Team> playingTeams = new ArrayList<>();
        //if (names.size() % 4 != 0 || names.size() % 2 != 0 || names.size() <= 0)
        if (names.size() != 64 && names.size() != 68)
        {
            System.out.println("Error: cannot create bracket from team size.");
            return null;
        }
        for (int i = 0; i < teams.size(); i++)
        {
            Team team = teams.get(i);
            if (names.contains(team.teamName)) playingTeams.add(teams.get(i));
        }
        return playingTeams;
    }

    public static boolean CheckTeams(List<String> playingTeams) throws IOException
    {
        Scanner scan = new Scanner(new File("PlayingTeams.txt"));
        List<Team> allTeams = CalcTeams();
        while (scan.hasNext())
        {
            String line = scan.nextLine();
            for (int i = 0; i < allTeams.size(); i++)
            {
                if (allTeams.get(i).teamName.equalsIgnoreCase(line))
                {
                    playingTeams.add(line);
                    break;
                }
                if (i == allTeams.size() - 1)
                {
                    System.out.println(line + " isn't a valid team name.");
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException
    {
        List<String> playingTeams = new ArrayList<>();
        if (CheckTeams(playingTeams))
        {
            List<Team> teams = GetPlayingTeams(CalcTeams(), playingTeams);
            if (teams != null) GenerateBracket(teams);
        }
    }
}