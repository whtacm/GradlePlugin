package org.example.greeting;

import org.gradle.api.Project;

import java.lang.String;

import org.gradle.internal.reflect.Instantiator;

import javax.inject.Inject;

import org.gradle.api.Action;

/**
 *
 */
public class GreetingPluginExtension {
    private String extMessage;
    private String extRecipient;

    protected Project project;
    private Instantiator instantiator;

    public InnerExtension mInnerExtension;

    public void innerExtension(Action<InnerExtension> action) {
        action.execute(mInnerExtension);
    }

    public String getExtMessage() {
        return extMessage;
    }

    public void setExtMessage(String extMessage) {
        System.out.println("GreetingPluginExtension::setExtMessage");
        this.extMessage = extMessage;
    }

    public String getExtRecipient() {
        return extRecipient;
    }

    public void setExtRecipient(String extRecipient) {
        this.extRecipient = extRecipient;
    }

    public GreetingPluginExtension(Project project, Instantiator instantiator) {
        this.project = project;
        this.instantiator = instantiator;
        System.out.println("GreetingPluginExtension constructor");
        mInnerExtension = instantiator.newInstance(InnerExtension.class);
    }
}