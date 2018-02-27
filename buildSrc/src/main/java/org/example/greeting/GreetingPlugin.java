package org.example.greeting;

import org.gradle.api.NamedDomainObjectContainer;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

import javax.inject.Inject;

import org.gradle.internal.reflect.Instantiator;
import org.gradle.tooling.provider.model.ToolingModelBuilderRegistry;

/**
 *
 */
public class GreetingPlugin implements Plugin<Project> {
    private Instantiator instantiator;
    private ToolingModelBuilderRegistry registry;
    private Project project;
    private GreetingPluginExtension greetingPluginExtension;
    private BookManagerExtension bookManagerExtension;

    @Inject
    public GreetingPlugin(Instantiator instantiator, ToolingModelBuilderRegistry registry) {
        this.instantiator = instantiator;
        this.registry = registry;
        System.out.printf("GreetingPlugin constructor-->> %s, %s \n", this.instantiator.toString(), this.registry.toString());
    }

    public void apply(Project project) {
        this.project = project;
        configureExtension();
        configureTask();
    }

    protected void configureExtension() {
        // this.instantiator = ((DefaultGradle) project.getGradle()).getServices().get(Instantiator.class);
        greetingPluginExtension = project.getExtensions().create("greeting", GreetingPluginExtension.class, project, this.instantiator);
        NamedDomainObjectContainer<Library> libraryNamedDomainObjectContainer = project.container(Library.class, new LibraryFactory(this.instantiator));
        NamedDomainObjectContainer<Book> bookNamedDomainObjectContainer = project.container(Book.class, new BookFactory(this.instantiator));
        bookManagerExtension = project.getExtensions().create("bookManager", BookManagerExtension.class,
                new Object[]{project, this.instantiator, libraryNamedDomainObjectContainer, bookNamedDomainObjectContainer});
    }

    protected void configureTask() {
        project.getTasks().create("hello", GreetingTask.class, (task) -> {
            System.out.printf("GreetingPlugin::apply-->> %s, %s \n", task.getMessage(), task.getRecipient());
            System.out.printf("GreetingPlugin::apply-->> %s, %s \n", this.greetingPluginExtension.getExtMessage(), this.greetingPluginExtension.getExtRecipient());
            task.setGre(this.greetingPluginExtension);
        });

        project.getTasks().create("write", WriteBookTask.class, (task) -> {
            task.setBookManagerExtension(bookManagerExtension);
        });
    }
}