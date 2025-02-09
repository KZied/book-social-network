package com.bsn.book_network.feedback;

import com.bsn.book_network.book.Book;
import com.bsn.book_network.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Feedback extends BaseEntity {

    private Double note;

    private String comment;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

}
