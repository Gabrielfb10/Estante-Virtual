package estantevirtual;
import estantevirtual.model.Media;
import estantevirtual.model.Book;
import estantevirtual.model.AudioBook;

public class EstanteVirtual {
    public static void main (String[] args) {
        //teste
        Media book = new Book(
                1L, "Dom Casmurro", "Machado de Assis",
                "Romance", "Livro do clássico do romance", 300
        );

        Media audioBook = new AudioBook(
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


    }
}