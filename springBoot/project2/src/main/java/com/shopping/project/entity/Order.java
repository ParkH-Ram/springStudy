package com.shopping.project.entity;


import com.shopping.project.constant.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDateTime orderDate;    // 주문일

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;    // 주문상태

    // 이미 다대일 단방향 매핑을 했으므로 양방향 매핑이 된다  p202
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL,     // cascade를 추가하면서 영속성 옵션 설정
                orphanRemoval = true)  // 고아 객체 제거 orphanRemoval = true
    private List<OrderItem> orderItems = new ArrayList<>();



    private LocalDateTime regTime;

    private LocalDateTime updateTime;
}
