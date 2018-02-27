package org.example.greeting;

import org.gradle.api.Action;
import org.gradle.api.NamedDomainObjectContainer;
import org.gradle.api.Project;
import org.gradle.internal.reflect.Instantiator;

/**
 * Created by robin on 2/27/18.
 */

public class BookManagerExtension {
    private Project project;
    private Instantiator instantiator;

    public NamedDomainObjectContainer<Library> getLibraries() {
        return libraries;
    }

    public void setLibraries(NamedDomainObjectContainer<Library> libraries) {
        this.libraries = libraries;
    }

    public NamedDomainObjectContainer<Book> getBooks() {
        return books;
    }

    public void setBooks(NamedDomainObjectContainer<Book> books) {
        this.books = books;
    }

    NamedDomainObjectContainer<Library> libraries;
    NamedDomainObjectContainer<Book> books;

    public BookManagerExtension(Project project, Instantiator instantiator, NamedDomainObjectContainer<Library> libraries, NamedDomainObjectContainer<Book> books) {
        this.project = project;
        this.instantiator = instantiator;
        this.libraries = libraries;
        this.books = books;
    }

    public void libraries(Action<? super NamedDomainObjectContainer<Library>> action) {
        action.execute(this.libraries);
    }

    public void books(Action<? super NamedDomainObjectContainer<Book>> action) {
        action.execute(this.books);
    }

}
