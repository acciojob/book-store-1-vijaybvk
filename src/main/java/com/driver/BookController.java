package com.driver;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("books")
public class BookController {

    private List<Book> bookList;
    private int id;
    HashMap<Integer, Book> booksById;

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BookController(){
        this.bookList = new ArrayList<Book>();
        this.booksById = new HashMap<>();
        setId(1);
    }

    // post request /create-book
    // pass book as request body
    @PostMapping("/create-book")
    public ResponseEntity<Book> createBook(@RequestBody() Book book){
        // Your code goes here.
        book.setId(this.id);
        this.id += 1;
        bookList.add(book);
        booksById.put(book.getId(), book);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    // get request /get-book-by-id/{id}
    // pass id as path variable
    @GetMapping("/get-book-by-id/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id")String id){
        return new ResponseEntity<>(booksById.get(Integer.parseInt(id)), HttpStatus.OK);
    }

    // delete request /delete-book-by-id/{id}
    // pass id as path variable
    // deleteBookById()
    @DeleteMapping("/delete-book-by-id/{id}")
    public ResponseEntity<String> deleteBookById(@PathVariable("id")String id){
        if(booksById.containsKey(Integer.parseInt(id))){
            bookList.remove(booksById.get(Integer.parseInt(id)));
            booksById.remove(Integer.parseInt(id));
        }
        return new ResponseEntity("Success",HttpStatus.OK);
    }

    // get request /get-all-books
    // getAllBooks()
    @GetMapping("/get-all-books")
    public ResponseEntity<List<Book>> getAllBooks(){
        return new ResponseEntity<>(bookList, HttpStatus.OK);
    }

    // delete request /delete-all-books
    // deleteAllBooks()
    @DeleteMapping("/delete-all-books")
    public ResponseEntity<String> deleteAllBooks(){
        bookList.clear();
        booksById.clear();
        this.id = 1;
        return new ResponseEntity("Success", HttpStatus.OK);
    }

    // get request /get-books-by-author
    // pass author name as request param
    // getBooksByAuthor()
    @GetMapping("/get-books-by-author")
    public ResponseEntity<List<Book>> getBooksByAuthor(@RequestParam("author")String author){
        List<Book> ans = new ArrayList<>();
        for(Book currBook : bookList){
            if(currBook.getAuthor().equals(author)){
                ans.add(currBook);
            }
        }

        return new ResponseEntity<>(ans, HttpStatus.OK);
    }

    // get request /get-books-by-genre
    // pass genre name as request param
    // getBooksByGenre()
    @GetMapping("/get-books-by-genre")
    public ResponseEntity<List<Book>> getBooksByGenre(@RequestParam("genre")String genre){
        List<Book> ans = new ArrayList<>();
        for(Book currBook : bookList){
            if(currBook.getGenre().equals(genre)){
                ans.add(currBook);
            }
        }

        return new ResponseEntity<>(ans, HttpStatus.OK);
    }
}