package org.musca.message;

import com.google.gson.Gson;

import java.time.LocalDate;

/**
 * Created by jonas on 13.09.14.
 */
public class MessagePojo {

    final String someText;
    final Integer someNumber;
    final LocalDate someDate;

    public MessagePojo(String someText, Integer someNumber, LocalDate someDate) {
        this.someText = someText;
        this.someNumber = someNumber;
        this.someDate = someDate;
    }

    public String getSomeText() {
        return someText;
    }

    public Integer getSomeNumber() {
        return someNumber;
    }

    public LocalDate getSomeDate() {
        return someDate;
    }

    public String toJson() {
        return new Gson().toJson(this);
    }

    @Override
    public String toString() {
        return "MessagePojo{" +
                "someText='" + someText + '\'' +
                ", someNumber=" + someNumber +
                ", someDate=" + someDate +
                '}';
    }
}
