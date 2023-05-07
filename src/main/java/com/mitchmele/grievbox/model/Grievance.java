package com.mitchmele.grievbox.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;



@Entity
@Builder
@Table(name = "grievance", schema = "public")
public class Grievance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "rating", nullable = false)
    private long rating;

    public Grievance(Long id, String text, long rating) {
        this.id = id;
        this.text = text;
        this.rating = rating;
    }

    public Grievance() {

    }

    public void setText(String text) {
        this.text = text;
    }

    public void setRating(long rating) {
        this.rating = rating;
    }
//    @CreationTimestamp
//    @Column(name = "CREATED_TS", updatable = false)
//    private Date createdTs;

    public String getText() {
        return text;
    }

    public long getRating() {
        return rating;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
