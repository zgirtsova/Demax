package app.domain.oopdesign;

import java.util.Random;
import java.util.Set;

public class Stapler {
    private static final int INITIAL_STAPLES_COUNT = 2;

    private int staplesCount;
    private Colors staplerColor;

    public Stapler() {
        setRandomColor();
        this.staplesCount = INITIAL_STAPLES_COUNT;
    }

    public Stapler(String staplerColor) {
        setStaplerColor(staplerColor);
        this.staplesCount = INITIAL_STAPLES_COUNT;
    }

    public void stap() {
        if (this.staplesCount <= 0) {
            System.out.println("Stapler needs refilling.");
        } else {
            System.out.println("Great! You just stapled!");
            this.staplesCount--;
        }
    }

    public void refill() {
        if (this.staplesCount == INITIAL_STAPLES_COUNT) {
            System.out.println("Already full.");
        } else {
            this.staplesCount = INITIAL_STAPLES_COUNT;
            System.out.println("Stapler refilled!");
        }
    }

    private void setRandomColor() {
        Random rn = new Random();
        int pos = rn.nextInt(Colors.values().length);
        this.staplerColor = Colors.values()[pos];
    }

    public Colors getStaplerColor() {
        return this.staplerColor;
    }

    private void setStaplerColor(String staplerColor) {
        this.staplerColor = Colors.valueOf(staplerColor.toUpperCase());
    }
}
