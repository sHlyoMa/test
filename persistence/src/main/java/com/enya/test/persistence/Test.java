package com.enya.test.persistence;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * Created by yhrush on 6/24/2016.
 */
public class Test {

    public static void main(String[] args) {
        for (String arg : args) {
            System.out.println(arg);
        }
        System.out.println(StringUtils.isEmpty(""));
        EntityManager em = Persistence.createEntityManagerFactory("pu").createEntityManager();
    }
}
