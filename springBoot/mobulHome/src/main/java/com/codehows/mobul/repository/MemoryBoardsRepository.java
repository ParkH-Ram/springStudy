/*
package com.codehows.mobul.repository;

import com.codehows.mobul.entity.Boards;
import org.aspectj.lang.annotation.After;

import java.util.*;

public class MemoryBoardsRepository implements BoardsRepository{
    private static Map<Long, Boards> boardMap = new HashMap<>();
    private static Long sequence = 0L;

    @Override
    public Boards save(Boards boards){
        boards.setBoardId(++sequence);
        boardMap.put(boards.getBoardId(), boards);
        return boards;

    }

       // 옵셔널을 쓰는 이유 널 값이 반환 될 가능성이 있을 때
    @Override
    public Optional<Boards> findById(Long boardId) {
        return Optional.ofNullable(boardMap.get(boardId));
    }

    @Override
    public Optional<Boards> findByTitle(String boardTitle) {
        return boardMap.values().stream()
            .filter(boards -> boards.getBoardTitle().equals(boardTitle))
            .findAny();
    }

    @Override
    public List<Boards> findAll() {
        return new ArrayList<>(boardMap.values());
    }

    public void clearStore(){
        boardMap.clear();
    }


}
*/
