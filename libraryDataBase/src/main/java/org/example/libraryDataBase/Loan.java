package org.example.libraryDataBase;

import jakarta.persistence.*;

@Entity
    public class Loan {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Integer LoanId;

        @ManyToOne(cascade = CascadeType.MERGE)
        @JoinColumn(name="user_id")
        private User user;

        @ManyToOne(cascade =  CascadeType.MERGE)
        @JoinColumn(name="book_id")
        private Book book;

        private String loanDate;

        private String terminDate;

        private String returnDate;

    public Integer getLoanId() {
        return LoanId;
    }

    public void setLoanId(Integer loanId) {
        this.LoanId = loanId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(String loanDate) {
        this.loanDate = loanDate;
    }

    public String getTerminDate() {
        return terminDate;
    }

    public void setTerminDate(String terminDate) {
        this.terminDate = terminDate;
    }

    public String getReturnDate() {
        return returnDate;
    }
    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public Integer getBookId(){
        return book.getBookId();
    }

}
