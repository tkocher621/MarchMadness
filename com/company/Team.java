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

        CalculateScore();
    }

    public void CalculateScore()
    {
        finalScore += (100 * pointsPerGame * 0.1f);
        finalScore += (100 * fieldGoals * 0.2f);
        finalScore += (100 * reboundsPerGame * 0.2f);
        finalScore += (100 * turnoversPerGame * 0.2f);
        finalScore += (100 * freeThrowsPerGame * 0.1f);
        finalScore += (100 * assistsPerGame * 0.05f);
        finalScore += (100 * stealsPerGame * 0.05f);
        finalScore += (100 * threePointersPerGame * 0.1f);
    }

}
