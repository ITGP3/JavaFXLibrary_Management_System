package book;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

public class Book {

    private final SimpleStringProperty id;
    private final SimpleStringProperty title;
    private final SimpleStringProperty author;
    private final SimpleStringProperty publisher;
    private final SimpleBooleanProperty avaliable;
    private final SimpleStringProperty shelf;
    private final SimpleStringProperty category;
    private final SimpleStringProperty status;


    public Book(String id, String title, String author, String publisher, boolean avaliable, String shelf, String category, String status) {
        this.id = new SimpleStringProperty(id);
        this.title = new SimpleStringProperty(title);
        this.author = new SimpleStringProperty(author);
        this.publisher = new SimpleStringProperty(publisher);
        this.avaliable = new SimpleBooleanProperty(avaliable);
        this.shelf = new SimpleStringProperty(shelf);
        this.category = new SimpleStringProperty(category);
        this.status = new SimpleStringProperty(status);
    }


    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getTitle() {
        return title.get();
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getAuthor() {
        return author.get();
    }

    public SimpleStringProperty authorProperty() {
        return author;
    }

    public void setAuthor(String author) {
        this.author.set(author);
    }

    public String getPublisher() {
        return publisher.get();
    }

    public SimpleStringProperty publisherProperty() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher.set(publisher);
    }

    public boolean isAvaliable() {
        return avaliable.get();
    }

    public SimpleBooleanProperty avaliableProperty() {
        return avaliable;
    }

    public void setAvaliable(boolean avaliable) {
        this.avaliable.set(avaliable);
    }

    public String getShelf() {
        return shelf.get();
    }

    public SimpleStringProperty shelfProperty() {
        return shelf;
    }

    public void setShelf(String shelf) {
        this.shelf.set(shelf);
    }

    public String getCategory() {
        return category.get();
    }

    public SimpleStringProperty categoryProperty() {
        return category;
    }

    public void setCategory(String category) {
        this.category.set(category);
    }

    public String getStatus() {
        return status.get();
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }
}
