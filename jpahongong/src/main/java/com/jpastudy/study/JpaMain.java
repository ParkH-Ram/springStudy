package com.jpastudy.study;

import com.jpastudy.study.entity.Member;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class JpaMain {

    public static void main(String[] args) {
//		SpringApplication.run(StudyApplication.class, args);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaStudy");
        EntityManager em = emf.createEntityManager();

        EntityTransaction et = em.getTransaction();

        et.begin();

        Member member = new Member();
        member.setId(1L);
        member.setName("람");
        em.persist(member);
        System.out.println(member.toString());


        em.close();
        emf.close();
    }

}
