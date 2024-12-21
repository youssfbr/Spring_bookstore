package com.github.youssfbr.bookstore.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tb_books")
public class Book implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String title;

    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)//remover junto com FetchType.Lazy
    @ManyToOne//(fetch = FetchType.LAZY)(cascade = CascadeType.MERGE)(fetch = FetchType.LAZY) persist  Quando salvamos a entidade person , a entidade address também será salva.
    @JoinColumn(name = "publisher_id" , nullable=false)
    private Publisher publisher;

    public Book() { }

    public Book(UUID id , String title) {
        this.id = id;
        this.title = title;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;
        return Objects.equals(id , book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
