package com.enya.trainig.ejb.services;

import com.enya.training.model.TrUser;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserService implements IUserService {

    @PersistenceContext(name = "tr-pu")
    private EntityManager entityManager;

    public String run() {
        TrUser u = new TrUser();
        u.setLogin("enya@t.com");
        u.setPass("123");
        StringUtils.isNotBlank("test");
        entityManager.persist(u);
        return "UserService";
    }
}
