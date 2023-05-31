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

        try{

            Member member = new Member();
            member.setName("라머미");
            member.setMemberType(MemberType.ADMIN);
            em.persist(member);
            System.out.println(member.toString());
            et.commit(); // commit 빠지면 db로 value가 넘어가지 않는다.

        } catch ( Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        emf.close();


    }

}
