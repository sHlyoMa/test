package com.enya.test.desktop;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * Created by yhrush on 6/27/2016.
 */
public class Main {
    public static void main(String[] args) {
        EntityManager em = Persistence.createEntityManagerFactory("DesktopPU").createEntityManager();
    }
}
