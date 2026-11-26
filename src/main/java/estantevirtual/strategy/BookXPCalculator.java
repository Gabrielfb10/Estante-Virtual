package estantevirtual.strategy;

public class BookXPCalculator implements XPCalculator{

    @Override
    public int calculateXp(int previous, int current){
        return (current - previous) * 3;
    }
}
