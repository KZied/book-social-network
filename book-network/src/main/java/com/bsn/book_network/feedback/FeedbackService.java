package com.bsn.book_network.feedback;

import com.bsn.book_network.book.Book;
import com.bsn.book_network.book.BookRepository;
import com.bsn.book_network.exception.OperationNotPermittedException;
import com.bsn.book_network.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FeedbackService {

    private final BookRepository bookRepository;
    private final FeedbackMapper feedbackMapper;
    private final FeedbackRepository feedbackRepository;

    public Integer saveFeedback(FeedbackRequest request, Authentication connectedUser) {
        Book book = bookRepository.findById(request.bookId()).orElseThrow( () -> new RuntimeException("Book not found"));
        if(book.isArchived() || !book.isShareable()) {
            throw new OperationNotPermittedException("You cannot give a feedback for an archived or not a shareable book !");
        }
        User user = (User) connectedUser.getPrincipal();
        if(Objects.equals(book.getOwner().getId(), user.getId())) {
            throw new OperationNotPermittedException("You cannot give a feedback for your own book !");
        }
        Feedback feedback = feedbackMapper.toFeedback(request);
        return feedbackRepository.save(feedback).getId();
    }
}
