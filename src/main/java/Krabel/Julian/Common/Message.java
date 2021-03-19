package Krabel.Julian.Common;

import java.io.Serializable;

public class Message implements Serializable {

    static final long serialVersionUID=1;
    private int number;
    private String content;

    public Message(int number, String content) {
        this.number = number;
        this.content = content;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}