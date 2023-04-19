package com.codehows.mobul.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoards is a Querydsl query type for Boards
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoards extends EntityPathBase<Boards> {

    private static final long serialVersionUID = 1055379120L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoards boards = new QBoards("boards");

    public final StringPath boardContent = createString("boardContent");

    public final DateTimePath<java.time.LocalDateTime> boardDate = createDateTime("boardDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> boardId = createNumber("boardId", Long.class);

    public final NumberPath<Long> boardLike = createNumber("boardLike", Long.class);

    public final StringPath boardTag = createString("boardTag");

    public final StringPath boardTitle = createString("boardTitle");

    public final NumberPath<Long> boardView = createNumber("boardView", Long.class);

    public final StringPath boardWriter = createString("boardWriter");

    public final QUsers users;

    public QBoards(String variable) {
        this(Boards.class, forVariable(variable), INITS);
    }

    public QBoards(Path<? extends Boards> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBoards(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBoards(PathMetadata metadata, PathInits inits) {
        this(Boards.class, metadata, inits);
    }

    public QBoards(Class<? extends Boards> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.users = inits.isInitialized("users") ? new QUsers(forProperty("users")) : null;
    }

}

