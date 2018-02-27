package org.example.greeting;

import org.gradle.api.Project;

import java.lang.String;

import org.gradle.internal.reflect.Instantiator;

import javax.inject.Inject;

/**
 *
 */
public class InnerExtension {
    protected Project project;
    private Instantiator instantiator;

    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("InnerExtension::setName");
        this.name = name;
    }

    public InnerExtension() {
        System.out.println("InnerExtension constructor");
    }
}