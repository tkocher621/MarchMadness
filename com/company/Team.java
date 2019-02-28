package com.company;

public class Team {

    String teamName;

    float pointsPerGame;
    float fieldGoals;
    float reboundsPerGame;
    float turnoversPerGame;
    float freeThrowsPerGame;
    float assistsPerGame;
    float stealsPerGame;
    float threePointersPerGame;

    float finalScore;

    public Team(String _teamName,
                float _pointsPerGame, float _fieldGoals, float _reboundsPerGame,
                float _turnoversPerGame, float _freeThrowsPerGame, float _assistsPerGame,
                float _stealsPerGame, float _threePointerPerGame)
    {
        teamName = _teamName;

        // Multiply values below by weighted values
        pointsPerGame = _pointsPerGame;
        fieldGoals = _fieldGoals;
        reboundsPerGame = _reboundsPerGame;
        turnoversPerGame = _turnoversPerGame;
        freeThrowsPerGame = _freeThrowsPerGame;
        assistsPerGame = _assistsPerGame;
        stealsPerGame = _stealsPerGame;
        threePointersPerGame = _threePointerPerGame;
    }

    public void CalculateScore()
    {
        // store in finalscore
        if (teamName.equalsIgnoreCase("a")) finalScore = 123;
        else finalScore = 222;
    }

    public float GetStatValue(float stat)
    {
        return stat * 100f;
    }

}
