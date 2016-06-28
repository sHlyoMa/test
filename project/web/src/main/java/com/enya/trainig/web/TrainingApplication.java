package com.enya.trainig.web;

import com.enya.trainig.web.rest.HelloWorld;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/rest/")
public class TrainingApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(HelloWorld.class);
        return classes;
    }
}
