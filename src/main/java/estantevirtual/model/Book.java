package estantevirtual.model;

public class Book extends Media{
    private int quantityPages;

    public Book(Long id, String title, String author,
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
}



