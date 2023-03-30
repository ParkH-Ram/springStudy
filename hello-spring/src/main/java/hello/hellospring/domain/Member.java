package hello.hellospring.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Member {

   //PK(Primary Key)를 매핑 해줘야 함
   //DB에 값을 넣으면 DB가 값을 자동으로 생성해주는 것 IDENTITY 전략이라 한다
   @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String name;

   public Long getId() {

      return id;
   }

   public void setId(Long id) {

      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {

      this.name = name;
   }
}
