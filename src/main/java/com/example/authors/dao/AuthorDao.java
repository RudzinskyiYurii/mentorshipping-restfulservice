package com.example.authors.dao;

import com.example.authors.model.Author;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class AuthorDao {

    private static final Map<String, Author> AUTHOR_MAP = new HashMap<>();

    static {
        init();
    }

    private static void init(){
        Author author1 = new Author("Oleg", "Martynuk");
        Author author2 = new Author("John", "Travolta");
        Author author3 = new Author("John", "Smith");

        AUTHOR_MAP.put("Martynuk", author1);
        AUTHOR_MAP.put("Travolta", author2);
        AUTHOR_MAP.put("Smith", author3);
    }

    public Author getAuthor(String lastName) {
        return AUTHOR_MAP.get(lastName);
    }

    public Author addAuthor(Author author) {
        AUTHOR_MAP.put(author.getLastName(), author);
        return author;
    }

    public Author updateAuthor(Author author) {
        AUTHOR_MAP.put(author.getLastName(), author);
        return author;
    }

    public void deleteAuthor(String lastName) {
        AUTHOR_MAP.remove(lastName);
    }

    public List<Author> getAllAuthors() {
        Collection<Author> c = AUTHOR_MAP.values();
        List<Author> list = new ArrayList<Author>();
        list.addAll(c);
        return list;
    }



}
