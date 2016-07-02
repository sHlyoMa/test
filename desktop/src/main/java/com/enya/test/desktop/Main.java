package com.enya.test.desktop;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Main {

    private static final EntityManager em = Persistence.createEntityManagerFactory("derby").createEntityManager();

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("enter operation");
        switch (s.next()) {
            case "add": {
                Employee e = new Employee();
                System.out.println("enter user name");
                String inputName = s.next();
                String name = inputName != null ? inputName : UUID.randomUUID().toString();
                e.setName(name);
                em.getTransaction().begin();
                em.persist(e);
                em.getTransaction().commit();
                System.out.println("Added ");
                break;
            }
            case "all": {
                List<Employee> all = em.createQuery("SELECT e FROM Employee e").getResultList();
                for (Employee employee : all) {
                    System.out.println(employee);
                }
                break;
            }
            case "find": {
                List<Employee> all = em.createQuery("select e from Employee e where e.name like (:name)").setParameter("name", "%" + s.next() + "%").getResultList();
                for (Employee employee : all) {
                    System.out.println(employee);
                }
            }

        }
        main(null);
    }
}
