package org.example.greeting;

import org.gradle.internal.reflect.Instantiator;

/**
 * Created by robin on 2/27/18.
 */

public class Book {
    private String name;
    private Library bookLocation;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Library getBookLocation() {
        return bookLocation;
    }

    public void setBookLocation(Library bookLocation) {
        this.bookLocation = bookLocation;
    }

    public Book(String name, Instantiator instantiator) {
        this.name = name;
    }
}
