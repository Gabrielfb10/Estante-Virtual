package estantevirtual.service;

import estantevirtual.model.AudioBook;
import estantevirtual.model.Book;

public class MediaService {
    public Book createBook(Long id, String title, String author,
                           String genre, String description,
                           int quantityPages) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Informe o título!");
        }

        if (author == null || author.isBlank()) {
            throw new IllegalArgumentException("Informe o nome do autor!");
        }

        if (quantityPages <= 0) {
            throw new IllegalArgumentException("A quantidade de páginas deve ser maior que 0!");
        }
        return new Book(id, title, author, genre, description, quantityPages);
    }

    public AudioBook createAudioBook(Long id, String title, String author,
                                     String genre, String description,
                                     int durationSeconds) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Informe o título!");
        }

        if (author == null || author.isBlank()) {
            throw new IllegalArgumentException("Informe o nome do autor!");
        }

        if (durationSeconds <= 0){
            throw new IllegalArgumentException("A duração deve ser maior que 0 segundos!");
        }

        return new AudioBook(id, title, author, genre, description, durationSeconds);
    }
    }

