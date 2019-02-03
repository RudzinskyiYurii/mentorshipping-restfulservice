package com.example.authors.controller;

import com.example.authors.dao.AuthorDao;
import com.example.authors.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

import javax.jms.Queue;
import java.util.List;

@RestController
public class MainRESTController {

    @Autowired
    private AuthorDao authorDao;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private Queue queueAuthors;

    @Autowired
    private Queue queueAuthor;

    @RequestMapping("/")
    @ResponseBody
    public String welcome(){
        return  "Welcome to Spring Boot + REST + JWT Example.";
    }

    @RequestMapping(value = "/authors", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE})
    @ResponseBody
    public List<Author> getAuthors(){
        List<Author> authors = authorDao.getAllAuthors();
        jmsTemplate.convertAndSend(queueAuthors, authors.toString());
        return authors;
    }

    @RequestMapping(value = "/author/{lastName}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE})
    @ResponseBody
    public Author getAuthor(@PathVariable("lastName") String lastName){
        jmsTemplate.convertAndSend(queueAuthor, (authorDao.getAuthor(lastName)).toString());
        return authorDao.getAuthor(lastName);
    }

    @RequestMapping(value = "/author", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE})
    @ResponseBody
    public Author addAuthor(@RequestBody Author author){
        System.out.println("{Service side} Creating author: " + author.getLastName());
        jmsTemplate.convertAndSend(queueAuthor, authorDao.addAuthor(author).toString());
        return authorDao.addAuthor(author);
    }

    @RequestMapping(value = "/author", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE})
    @ResponseBody
    public Author updateAuthor(@RequestBody Author author){
        System.out.println("{Service side} Editing author: " + author.getLastName());
        jmsTemplate.convertAndSend(queueAuthor, authorDao.updateAuthor(author).toString());
        return authorDao.updateAuthor(author);
    }

    @RequestMapping(value = "/author/{lastName}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE})
    @ResponseBody
    public void deleteAuthor(@PathVariable("lastName") String lastName){
        System.out.println("{Service side} Deleting author: " + lastName);
        authorDao.deleteAuthor(lastName);
    }

}
