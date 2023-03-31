package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceAop {

    // hello.hellospring 패키지 하위 쪽 ( 자식들 ) 한테 다 적용하라는 뜻
    @Around("execution(* hello.hellospring..*(..))")
    // 호출 될 때마다 조인 포인트에다가 중간에서 인터셉트가 걸리는 것
    // return에 다른 메서드를 넣어서 다른 작업도 가능하게 가능 함 
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        System.out.println("Start : " + joinPoint.toString());
        try{
            return joinPoint.proceed();
        }finally{
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("End : " + joinPoint.toString()+ " " + timeMs + "ms");
        }
    }

}
