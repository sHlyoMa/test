package com.enya.test.desktop;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Main {


    public static void main(String[] args) {
        EntityManager em = Persistence.createEntityManagerFactory("derby").createEntityManager();
        Scanner s = new Scanner(System.in);
        switch (s.next()) {
            case "add": {
                Employee e = new Employee();
                String name = args.length > 1 ? args[1] : UUID.randomUUID().toString();
                e.setName(name);
                em.getTransaction().begin();
                em.persist(e);
                em.getTransaction().commit();
                System.out.println("Added ");
                break;
            }
            case "all":{
                List<Employee> all =em.createQuery("SELECT e FROM Employee e").getResultList();
                for (Employee employee : all) {
                    System.out.println(employee);
                }
                break;
            }

        }
        main(null);
    }
}
