package com.codehows.mobul.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoardsFile is a Querydsl query type for BoardsFile
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoardsFile extends EntityPathBase<BoardsFile> {

    private static final long serialVersionUID = -735944628L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoardsFile boardsFile = new QBoardsFile("boardsFile");

    public final QBoards boards;

    public final NumberPath<Long> fileId = createNumber("fileId", Long.class);

    public final StringPath fileName = createString("fileName");

    public final StringPath fileOriName = createString("fileOriName");

    public final StringPath filePath = createString("filePath");

    public QBoardsFile(String variable) {
        this(BoardsFile.class, forVariable(variable), INITS);
    }

    public QBoardsFile(Path<? extends BoardsFile> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBoardsFile(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBoardsFile(PathMetadata metadata, PathInits inits) {
        this(BoardsFile.class, metadata, inits);
    }

    public QBoardsFile(Class<? extends BoardsFile> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.boards = inits.isInitialized("boards") ? new QBoards(forProperty("boards"), inits.get("boards")) : null;
    }

}

