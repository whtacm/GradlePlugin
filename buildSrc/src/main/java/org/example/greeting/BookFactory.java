package org.example.greeting;

import org.gradle.api.NamedDomainObjectFactory;
import org.gradle.internal.reflect.Instantiator;

/**
 * Created by robin on 2/27/18.
 */

public class BookFactory implements NamedDomainObjectFactory<Book> {
    Instantiator instantiator;

    public BookFactory(Instantiator instantiator) {
        this.instantiator = instantiator;
    }

    @Override
    public Book create(String name) {
        return instantiator.newInstance(Book.class, new Object[]{name, instantiator});
    }
}
