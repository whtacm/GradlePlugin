package org.example.greeting;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.Nested;

public class GreetingTask extends DefaultTask {
    private String message;
    private String recipient;
    private GreetingPluginExtension gre;

    @Input
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
        System.out.println("GreetingTask::setMessage");
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    @Nested
    public GreetingPluginExtension getGre() {
        return gre;
    }

    @Nested
    public void setGre(GreetingPluginExtension gre) {
        this.gre = gre;
        System.out.println("GreetingTask::setGre");
    }

    @TaskAction
    void sayGreeting() {
        System.out.printf("GreetingTask::sayGreeting-->> %s, %s!\n", getMessage(), getRecipient());
        System.out.printf("GreetingTask::sayGreeting-->> %s, %s!\n", getGre().getExtMessage(), getGre().getExtRecipient());
    }

    @TaskAction
    void sayGoodbye() {
        System.out.printf("GreetingTask::sayGoodbye-->> %s\n", getGre().mInnerExtension.getName());
    }
}