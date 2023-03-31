package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;


    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

/*
    private EntityManager em;

    public SpringConfig(EntityManager em) {
        this.em = em;
    }
*/


/*    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource){
        this.dataSource = dataSource;
    }*/

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }

    //AOP에 스프링 빈에 직접 등록 하면 이걸 보고 아~ AOP가 등록돼서 쓰이는구나 하고 알 수 있음
/*    @Bean
    public TimeTraceAop timeTraceAop(){
        return  new TimeTraceAop();
}*/

/*    @Bean
    public MemberRepository memberRepository(){*/
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource); // 스프링, 객체 지향을 쓰는 이유  쉽게 갈아 끼울 수 있다.
//        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);


}


