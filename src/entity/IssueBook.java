package entity;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class IssueBook {

    private  SimpleStringProperty bookId;
    private  SimpleStringProperty memberId;
    private  SimpleStringProperty issueTime;
    private  SimpleIntegerProperty renewCount;
    private  SimpleStringProperty renewDate;

    public IssueBook(String bookId, String memberId, String issueTime, Integer renewCount, String renewDate) {
        this.bookId = new SimpleStringProperty(bookId);
        this.memberId = new SimpleStringProperty(memberId);
        this.issueTime = new SimpleStringProperty(issueTime);
        this.renewCount = new SimpleIntegerProperty(renewCount);
        this.renewDate = new SimpleStringProperty(renewDate);
    }

    public IssueBook(String bookId, String memberId) {
        this.bookId = new SimpleStringProperty(bookId);
        this.memberId = new SimpleStringProperty(memberId);
    }

    public String getBookId() {
        return bookId.get();
    }

    public SimpleStringProperty bookIdProperty() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId.set(bookId);
    }

    public String getMemberId() {
        return memberId.get();
    }

    public SimpleStringProperty memberIdProperty() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId.set(memberId);
    }

    public String getIssueTime() {
        return issueTime.get();
    }

    public SimpleStringProperty issueTimeProperty() {
        return issueTime;
    }

    public void setIssueTime(String issueTime) {
        this.issueTime.set(issueTime);
    }

    public int getRenewCount() {
        return renewCount.get();
    }

    public SimpleIntegerProperty renewCountProperty() {
        return renewCount;
    }

    public void setRenewCount(int renewCount) {
        this.renewCount.set(renewCount);
    }

    public String getRenewDate() {
        return renewDate.get();
    }

    public SimpleStringProperty renewDateProperty() {
        return renewDate;
    }

    public void setRenewDate(String renewDate) {
        this.renewDate.set(renewDate);
    }
}

