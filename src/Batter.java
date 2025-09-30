/*
 * @file: Batter.java
 * @description: Represents a baseball player with 20 fields from the CSV dataset.
 * @author: Zell Godbold
 * @date: September 25, 2025
 */

public class Batter implements Comparable<Batter> {
    private int id;
    private int rank;
    private int year;
    private String player;
    private int battedBallEvents;
    private double launchAngle;
    private double sweetSpotPercentage;
    private double maxEV;
    private double averageEV;
    private double flyBallLineDriveEV;
    private double groundBallEV;
    private int maxDistance;
    private double averageDistance;
    private double averageHomerun;
    private int hardHit95mph;
    private double hardHitPercentage;
    private double hardHitSwingPercentage;
    private int totalBarrels;
    private double barrelsBattedBallsPercentage;
    private double barrelsPlateAppearancePercentage;

    // Full constructor
    public Batter(int id, int rank, int year, String player,
                  int battedBallEvents, double launchAngle, double sweetSpotPercentage,
                  double maxEV, double averageEV, double flyBallLineDriveEV,
                  double groundBallEV, int maxDistance, double averageDistance,
                  double averageHomerun, int hardHit95mph, double hardHitPercentage,
                  double hardHitSwingPercentage, int totalBarrels,
                  double barrelsBattedBallsPercentage, double barrelsPlateAppearancePercentage) {
        this.id = id;
        this.rank = rank;
        this.year = year;
        this.player = player;
        this.battedBallEvents = battedBallEvents;
        this.launchAngle = launchAngle;
        this.sweetSpotPercentage = sweetSpotPercentage;
        this.maxEV = maxEV;
        this.averageEV = averageEV;
        this.flyBallLineDriveEV = flyBallLineDriveEV;
        this.groundBallEV = groundBallEV;
        this.maxDistance = maxDistance;
        this.averageDistance = averageDistance;
        this.averageHomerun = averageHomerun;
        this.hardHit95mph = hardHit95mph;
        this.hardHitPercentage = hardHitPercentage;
        this.hardHitSwingPercentage = hardHitSwingPercentage;
        this.totalBarrels = totalBarrels;
        this.barrelsBattedBallsPercentage = barrelsBattedBallsPercentage;
        this.barrelsPlateAppearancePercentage = barrelsPlateAppearancePercentage;
    }

    // Getters (only need these bc I am only printing 6 of the 20 data points in my print statement)
    // (could easily be adapted to get all 20)
    public String getPlayer() { return player; }
    public int getRank() { return rank; }
    public double getMaxEV() { return maxEV; }
    public double getAverageEV() { return averageEV; }
    public double getHardHitPercentage() { return hardHitPercentage; }
    public int getMaxDistance() { return maxDistance; }

    // toString method to format the printed string
    @Override
    public String toString() {
        return String.format("%s | Rank: %d | Max EV: %.1f | Avg EV: %.1f | Hard Hit %%: %.1f | Max Dist: %d",
                player, rank, maxEV, averageEV, hardHitPercentage, maxDistance);
    }

    // Equality based on player name
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Batter)) return false;
        Batter other = (Batter) obj;
        return this.player.equalsIgnoreCase(other.player);
    }

    // Compare by averageEV (for BST ordering)
    @Override
    public int compareTo(Batter other) {
        return Double.compare(this.averageEV, other.averageEV);
    }
}
