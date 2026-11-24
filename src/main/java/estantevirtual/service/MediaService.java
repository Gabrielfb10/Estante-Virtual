package estantevirtual.service;

import estantevirtual.model.AudioBookModel;
import estantevirtual.model.BookModel;

public class MediaService {
    public BookModel createBook(Long id, String title, String author,
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
        return new BookModel(id, title, author, genre, description, quantityPages);
    }

    public AudioBookModel createAudioBook(Long id, String title, String author,
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

        return new AudioBookModel(id, title, author, genre, description, durationSeconds);
    }
    }

