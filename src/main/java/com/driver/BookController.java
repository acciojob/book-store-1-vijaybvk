package com.driver;

import java.util.ArrayList;
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
        this.id = 1;
    }

    @PostMapping("/create-book")
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        bookList.add(book);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @GetMapping("/get-book-by-id/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") String id){
        for(Book book:bookList){
            if(id.equals(book.getId())) return new ResponseEntity(book,HttpStatus.ACCEPTED);
        }
        return null;
    }

    @DeleteMapping("/delete-book-by-id/{id}")
    public ResponseEntity<String> deleteBookById(@PathVariable("id") String id){
        for(Book book:bookList){
            if(id.equals(book.getId())){
                bookList.remove(book);
                return new ResponseEntity("Deleted",HttpStatus.ACCEPTED);
            }
        }
        return null;
    }

    @GetMapping("/get-all-books")
    public ResponseEntity<List<Book>> getAllBooks(){
        return new ResponseEntity(bookList,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete-all-books")
    public ResponseEntity<String> deleteAllBooks(){
        bookList.clear();
        return  new ResponseEntity("Deleted",HttpStatus.ACCEPTED);
    }

    @GetMapping("/get-books-by-author")
    // pass author name as request param
    public ResponseEntity<List<Book>> getBooksByAuthor(@RequestParam("author") String author){
        List<Book> list=new ArrayList<>();
        for(Book book:bookList){
            if(book.getAuthor().equals(author)) list.add(book);
        }
        return new ResponseEntity(list,HttpStatus.ACCEPTED);
    }

    @GetMapping("/get-books-by-genre")
    public ResponseEntity<List<Book>> getBooksByGenre(@RequestParam("genre") String genre){
        List<Book> list=new ArrayList<>();
        for(Book book:bookList){
            if(book.getGenre().equals(genre)) list.add(book);
        }
        return new ResponseEntity(list,HttpStatus.ACCEPTED);
    }
}
