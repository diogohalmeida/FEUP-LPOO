package model;

public class Score implements Comparable<Score> {
    private double time;
    private int enemiesKilled;
    private String date;


    public Score(double time, int aliensKilled, String date){
        this.time = time;
        this.enemiesKilled = aliensKilled;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public double getTime() {
        return time;
    }

    public int getEnemiesKilled() {
        return enemiesKilled;
    }

    public String toString(){
        return time + " , " + enemiesKilled + " , " + date + "\n";
    }

    @Override
    public int compareTo(Score score) {
        if (score.getTime() != time)
            return (int) (score.getTime() - time);
        else
            return score.getEnemiesKilled() - enemiesKilled;
    }
}
