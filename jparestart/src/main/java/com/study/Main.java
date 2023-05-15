package com.study;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        // 팩토리는 DB 당 하나만 생성!
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaStudy");

        // em은 고객의 요청이 올때마다 생성.. 사용 하고 바로 버린다.  em은 thread 간 공유 절대 불가
        //JPA 모든 데이터 변경은 transaction 안에서 실행
        EntityManager em = emf.createEntityManager();  // 매번 생성해줘야 한다.
        EntityTransaction et = em.getTransaction();
        et.begin();

        try{
//            Member member = new Member();
           /* //em. 으로 생성  하고 커밋 저장 까지
            member.setId(2L);
            member.setName("HelloB");
            em.persist(member);*/

            //조 회
           Member findMember = em.find(Member.class, 2L);  // 조회 하는 방법
/*            System.out.println(findMember.getId());
            System.out.println(findMember.getName());*/

            
/*
            // 삭제 하는 방법
            em.remove(findMember);
*/
            // 수정
            findMember.setName("HelloJPA");




            et.commit();
        } catch (Exception e){
            et.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}