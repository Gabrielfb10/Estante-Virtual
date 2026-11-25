package estantevirtual.strategy;

public class AudioBookXPCalculator implements XPCalculator {

    @Override
    public int calculateXp(int previous, int current){
        int previousMinutes = previous / 60;
        int currentMinutes = current / 60;
        return currentMinutes - previousMinutes;
    }
}
