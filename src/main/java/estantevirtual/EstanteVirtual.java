package estantevirtual;

import estantevirtual.model.UserModel;
import estantevirtual.service.LibraryService;
import estantevirtual.service.MediaService;

public class EstanteVirtual {
    public static void main (String[] args) {
        MediaService mediaService = new MediaService();
        LibraryService libraryService = new LibraryService();
        //teste
        estantevirtual.model.MediaModel book = mediaService.createBook(
                1L, "Dom Casmurro", "Machado de Assis",
                "Romance", "Livro do clássico do romance", 300
        );

        estantevirtual.model.MediaModel audioBook = mediaService.createAudioBook(
                2L, "Dom Casmurro", "Machado de Assis",
                "Romance", "Audiolivro do clássico romance", 9000
        );

        System.out.println(book.getId());
        System.out.println(book.getTitle());
        System.out.println(book.getAuthor());
        System.out.println(book.getGenre());
        System.out.println(book.getDescription());
        System.out.println(book.formatSize());

        System.out.println(audioBook.getId());
        System.out.println(audioBook.getTitle());
        System.out.println(audioBook.getAuthor());
        System.out.println(audioBook.getGenre());
        System.out.println(audioBook.getDescription());
        System.out.println(audioBook.formatSize());
        System.out.println(audioBook.getTotalUnits());

        UserModel reader = new UserModel(1L, "Leitor");
        libraryService.updateProgress(libraryService.addMedia(reader, book), 10);
        libraryService.updateProgress(libraryService.addMedia(reader, audioBook), 120);
        System.out.println("Nível após registrar o progresso: " + reader.getLevel());
    }
}
