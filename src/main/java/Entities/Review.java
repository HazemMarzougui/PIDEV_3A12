package Entities;

import java.sql.Timestamp;

public class Review {


    private String title ;
    private int id_review ;
    private String comments ;

    private int value ;
    private int id_conseil ;

    private Timestamp dateCreation;

    public Review(){}

    public Review(int id_review ,String title , String comments , int value ,  int id_conseil)
    {
        this.id_review = id_review ;
        this.title = title ;
        this.comments = comments ;
        this.value = value ;
        this.id_conseil = id_conseil ;
    }
    public Review(String title , String comments , int value ,  int id_conseil)
    {
        this.title = title ;
        this.comments = comments ;
        this.value = value ;
        this.id_conseil = id_conseil ;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getId_review() {
        return id_review;
    }

    public void setId_review(int id_review) {
        this.id_review = id_review;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getId_conseil() {
        return id_conseil;
    }


    public String getDateCreation() {
        return String.valueOf(dateCreation);
    }

    public void setDateCreation(Timestamp dateCreation) {
        this.dateCreation = dateCreation;
    }

    public void setId_conseil(int id_conseil) {
        this.id_conseil = id_conseil;
    }

    @Override
    public String toString() {
        return "Review{" +
                "title='" + title + '\'' +
                ", id_review=" + id_review +
                ", comments='" + comments + '\'' +
                ", value=" + value +
                ", id_conseil=" + id_conseil +
                '}';
    }
}
