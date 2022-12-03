package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("books")
public class BookController {
    @Autowired
    BookService bookService;

    // One example controller, make the rest by yourself
    @PostMapping("/create-book")
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        Book newbook = bookService.createBook(book);
        return new ResponseEntity<>(newbook, HttpStatus.CREATED);
    }

    @GetMapping("/get-book-by-id/{id}")
    public ResponseEntity<Book> bookObjectById(@PathVariable String id){
        Book newbook = bookService.findBookById(id);
        return new ResponseEntity<>(newbook, HttpStatus.FOUND);
    }

    @GetMapping("/get-all-books")
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> bookList = bookService.findAllBooks();
        return new ResponseEntity<>(bookList, HttpStatus.FOUND);
    }

    @GetMapping("/get-books-by-author")
    public ResponseEntity<List<Book>> getBooksByAuthor(@RequestParam String author){
        List<Book> bookList = bookService.findBooksByAuthor(author);
        return new ResponseEntity<>(bookList, HttpStatus.FOUND);
    }

    @GetMapping("/get-books-by-genre")
    public ResponseEntity<List<Book>> getBooksByGenre(@RequestParam String genre){
        List<Book> bookList = bookService.findBooksByGenre(genre);
        return new ResponseEntity<>(bookList, HttpStatus.FOUND);
    }

    @DeleteMapping("/delete-book-by-id/{id}")
    public ResponseEntity deleteBookById(@PathVariable String id){
        bookService.deleteBookById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete-all-books")
    public ResponseEntity deleteBookById(){
        bookService.deleteAllBooks();
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
