package estantevirtual.model;

public abstract class Media {
    private Long id;
    private String title;
    private String author;
    private String genre;
    private String description;

    protected Media(Long id, String title,String author,String genre,String description){

        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Forneça um título!");
        }

        if (author == null || author.isBlank()) {
            throw new IllegalArgumentException("Forneça um autor!");
        }

        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.description = description;

    }
    //getters
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public String getDescription() {
        return description;
    }
    //metodos abstratos
    public abstract int getTotalUnits();

    public abstract String formatSize();

}
