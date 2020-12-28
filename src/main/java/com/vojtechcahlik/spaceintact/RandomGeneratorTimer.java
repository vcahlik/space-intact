package com.vojtechcahlik.spaceintact;

import java.util.Random;

/**
 * Holds a specific generation period, which gets randomized into intervals. Then it decides whether a new object should be generated during this cycle.
 * Makes generation of sprites simple and resource efficient.
 */
public final class RandomGeneratorTimer {
    public Class objectType;
    private int cyclesInPeriod;
    private int cyclesUntilNextGeneration;
    private static Random random = new Random();

    public RandomGeneratorTimer(Object objectType, double interval) {
        this.objectType = objectType.getClass();
        setInterval(interval);
    }
    
    public RandomGeneratorTimer(double interval) {
        this.objectType = null;
        setInterval(interval);
    }
    
    /**
     * Convert the interval to cycles.
     * @param interval - interval in seconds
     */
    public void setInterval(double interval) {
        this.cyclesInPeriod = Game.convertSecondsToCycles(interval);
        this.cyclesUntilNextGeneration = (int)((0.2 + random.nextDouble()) * cyclesInPeriod);
    }
    
    /**
     * Gets called on every game cycle.
     * @return true if an object should be created during this cycle.
     */
    public boolean generate() {
        if (cyclesUntilNextGeneration <= 0) {
            cyclesUntilNextGeneration = (int)((0.2 + random.nextDouble()) * cyclesInPeriod);
            return true;
        } else {
            cyclesUntilNextGeneration--;
        }
        return false;
    }
    
}