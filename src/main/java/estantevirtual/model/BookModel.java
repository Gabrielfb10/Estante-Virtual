package estantevirtual.model;

import estantevirtual.strategy.BookXPCalculator;
import estantevirtual.strategy.XPCalculator;

public class BookModel extends MediaModel{
    private static final XPCalculator XP_CALCULATOR = new BookXPCalculator();
    private int quantityPages;

    public BookModel(Long id, String title, String author,
                     String genre, String description, int quantityPages) {
        super(id, title, author, genre, description);

        this.quantityPages = quantityPages;
    }
    @Override
    public int getTotalUnits(){
        return quantityPages;
    }
    @Override
    public String formatSize(){
        return quantityPages + " páginas";
    }

    @Override
    public XPCalculator getXpCalculator() {
        return XP_CALCULATOR;
    }
}



