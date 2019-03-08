package com.company;

public class Team {

    String teamName;

    private float pointsPerGame;
    private float fieldGoals;
    private float reboundsPerGame;
    private float turnoversPerGame;
    private float freeThrowsPerGame;
    private float assistsPerGame;
    private float stealsPerGame;
    private float threePointersPerGame;

    float finalScore;

    public Team(String _teamName,
                float _pointsPerGame, float _fieldGoals, float _reboundsPerGame,
                float _turnoversPerGame, float _freeThrowsPerGame, float _assistsPerGame,
                float _stealsPerGame, float _threePointerPerGame)
    {
        teamName = _teamName;

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
        finalScore += (Math.random() * 11); // Add slight random factor
    }

}
