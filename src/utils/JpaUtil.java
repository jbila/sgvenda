package com.meldev.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {
	   private static final EntityManagerFactory entityManagerFactory;
	    static {
	    	
	        try {
	            entityManagerFactory = Persistence.createEntityManagerFactory("JPA_DATA");
	        } catch (Throwable ex) {
	            throw new ExceptionInInitializerError(ex);
	        }
	    }

	    public static EntityManager getEntityManager() {
	        return entityManagerFactory.createEntityManager();
	    }
	
}
