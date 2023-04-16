package com.codehows.mobul.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUsers is a Querydsl query type for Users
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUsers extends EntityPathBase<Users> {

    private static final long serialVersionUID = -1056664251L;

    public static final QUsers users = new QUsers("users");

    public final EnumPath<com.codehows.mobul.constant.Role> role = createEnum("role", com.codehows.mobul.constant.Role.class);

    public final StringPath userId = createString("userId");

    public final StringPath userOne = createString("userOne");

    public final StringPath userPassword = createString("userPassword");

    public final StringPath userPhone = createString("userPhone");

    public final StringPath userThree = createString("userThree");

    public final StringPath userTwo = createString("userTwo");

    public QUsers(String variable) {
        super(Users.class, forVariable(variable));
    }

    public QUsers(Path<? extends Users> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUsers(PathMetadata metadata) {
        super(Users.class, metadata);
    }

}

