package estantevirtual.model;

import estantevirtual.strategy.AudioBookXPCalculator;
import estantevirtual.strategy.XPCalculator;

public class AudioBookModel extends MediaModel{
    private static final XPCalculator XP_CALCULATOR = new AudioBookXPCalculator();
    private int durationSeconds;

    public AudioBookModel(Long id, String title, String author,
                          String genre, String description, int durationSeconds) {
        super(id, title, author, genre, description);

        this.durationSeconds = durationSeconds;

    }
    @Override
    public int getTotalUnits(){
        return durationSeconds;
    }
    @Override
    public String formatSize(){
        int hours = durationSeconds / 3600;
        int minutes = (durationSeconds % 3600) / 60;
        return hours + "h " + minutes + "min";
    }

    @Override
    public XPCalculator getXpCalculator() {
        return XP_CALCULATOR;
    }
}
