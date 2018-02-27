package org.example.greeting;

import org.gradle.api.NamedDomainObjectFactory;
import org.gradle.internal.reflect.Instantiator;

/**
 * Created by robin on 2/27/18.
 */

public class LibraryFactory implements NamedDomainObjectFactory<Library> {
    private Instantiator instantiator;

    public LibraryFactory(Instantiator instantiator) {
        this.instantiator = instantiator;
    }

    @Override
    public Library create(String name) {
        return instantiator.newInstance(Library.class, new Object[]{name});
    }
}
