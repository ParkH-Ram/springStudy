package com.codehows.mobul.repository;

import com.codehows.mobul.entity.Boards;
import com.codehows.mobul.entity.QBoards;
import com.codehows.mobul.entity.Users;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.test.context.TestPropertySource;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class BoardsRepositoryTest {

    @Autowired
    BoardsRepository boardsRepository;

    @Autowired
    AuthRepository authRepository;

    @PersistenceContext
    EntityManager em;   // 엔티티 매니저는 Querydsl 부터 사용한다
    //영속성 컨텍스트를 사용하기 위해 @PersistenceContext를 사용 하고 EntityManager 빈을 주입



    @Test
    @DisplayName("상품 저장 테스트 ")
    public void 상품저장(){
        Boards boards = new Boards();
        Users users = new Users();
        users.setUserId("그의 이름은");
        users.setUserPassword("5555");
        users.setUserPhone("444");
        boards.setBoardTitle("안녕하슈~");
        boards.setBoardContent("뭐라 처 씨부리노~~~");
        boards.setBoardDate(LocalDateTime.now());
        boards.setBoardWriter(users);
        authRepository.save(users);
        Boards saveBoard =  boardsRepository.save(boards);


        System.out.println(boards);

    }
    public void boardList(){
        for(int i=1; i<=10; i++){
            Boards boards = new Boards();
            Users users = new Users();
            users.setUserId("그의이름은" + i);
            users.setUserPassword("5555" + i );
            users.setUserPhone("444" + i );
            boards.setBoardTitle("게시판제목" + i );
            boards.setBoardContent("뭐라 처 씨부리노~~~" + i );
            boards.setBoardDate(LocalDateTime.now());
            boards.setBoardLike(500 + i);
            boards.setBoardWriter(users);
            authRepository.save(users);
            Boards saveBoard =  boardsRepository.save(boards);


            System.out.println("saveBoard = " + saveBoard);
            System.out.println("BoardsRepositoryTest.게시판목록");
            System.out.println(users);
        }
    }

    @Test
    @DisplayName("상품명 조회")
    public void 상품아이디조회(){
        this.boardList();
        List<Boards> boardsList = boardsRepository.findByBoardId(3L);
        for(Boards boards : boardsList)
            System.out.println(boards.toString());

    }
    
    @Test
    @DisplayName("제목 테스트")
    public void 검색테스트(){
        this.boardList();
        List<Boards> boardsList =  boardsRepository.findByBoardTitle("게시판제목4");
        for(Boards boards : boardsList){
            System.out.println(boards.toString());
        }
    }


    @Test
    @DisplayName("Or테스트")
    public void OR테스트() {
        Users users = new Users();
        Boards boards1 = new Boards();
        this.boardList();
        users.setUserId("그의이름은4");
        boards1.setBoardWriter(boards1.getBoardWriter());
        List<Boards> boardsList = boardsRepository.findByBoardWriterOrBoardTitle(users, "게시판제목4");
        for (Boards boards : boardsList) {
            System.out.println(boards.toString());
        }
    }

    @Test
    @DisplayName("좋아요 LessThan")
    public void findByBoardLikeLessThan(){
        this.boardList();
        List<Boards> boardsList = boardsRepository.findByBoardLikeLessThan(507);
        for(Boards boards : boardsList){
            System.out.println(boards.toString());


        }
    }

    @Test
    @DisplayName("좋아요 내림 차순")
    public void findByBoardLikeLessThanOrderByBoardLikeDesc(){
        this.boardList();
        List<Boards> boardsList=
        boardsRepository.findByBoardLikeLessThanOrderByBoardLikeDesc(505);
        for(Boards Boards : boardsList) {
            System.out.println(boardsList.toString());
        }
    }

    @Test
    @DisplayName("@Query를 이용한")
    public void findByBoardsLikeTest(){
        this.boardList();
        List<Boards> boardsList =
            boardsRepository.findByBoardsLike("게시판제목7");
        for(Boards boards : boardsList){
            System.out.println("boards = " + boards);
            System.out.println(boards.toString());
        }

    }

    @Test
    @DisplayName("Querydsl 조회")
    public void Querydsl조회(){
        this.boardList();
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QBoards qBoards = QBoards.boards;
        JPAQuery<Boards> query = queryFactory.selectFrom(qBoards)
            .where(qBoards.boardId.eq(4L))
            .where(qBoards.boardTitle.like("%" + "제목" + "%"))
            .orderBy(qBoards.boardLike.desc());

        List<Boards> boardsList = query.fetch();



        for(Boards boards : boardsList){
            System.out.println(boards.toString());

        }
    }
    public void createBoardList2() {
        for (int i = 1; i < 5; i++) {
            Boards boards = new Boards();
            boards.setBoardTitle("게시판 제목~" + i);
            boards.setBoardContent("게시판 내용" + i);
            boards.setBoardTag("게시판태그" + i);
            boards.setBoardLike(0 + i);
            boards.setBoardView(0 + i);
            boards.setBoardDate(LocalDateTime.now());

            boardsRepository.save(boards);
        }
        for (int i = 6; i < 10; i++) {
            Boards boards = new Boards();
            boards.setBoardTitle("게시판 제목~" + i);
            boards.setBoardContent("게시판 내용" + i);
            boards.setBoardTag("게시판태그" + i);
            boards.setBoardLike(0 + i);
            boards.setBoardView(0 + i);
            boards.setBoardDate(LocalDateTime.now());
            boardsRepository.save(boards);
        }
    }

    @Test
    @DisplayName("상품 쿼리 조회 2")
    public void 쿼리dsl조회2(){
        this.createBoardList2();

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QBoards boards = QBoards.boards;
        String boardTitle = "게시판 제목";
        Integer boardLike = 1000;
        String boardContext = "게시판 내용";

        booleanBuilder.and(boards.boardTitle.like("%" + boardTitle + "%"));
        booleanBuilder.and(boards.boardLike.gt(boardLike));

        if(StringUtils.equals(boardContext, "게시판 내용")){
            booleanBuilder.and(boards.boardContent.eq("게시판 내용"));
        }
        Pageable pageable = PageRequest.of(0,5);
        Page<Boards> boardsPagingResult = boardsRepository.findAll(booleanBuilder, pageable);
        System.out.println("total elements : " + boardsPagingResult.getTotalElements());

        List<Boards> boardsList = boardsPagingResult.getContent();
        for(Boards resultBoard :  boardsList){
            System.out.println(resultBoard.toString());
        }

    }







}













































 //  Jpa 미사용 테스트 코드
   /* MemoryBoardsRepository boardsRepository = new MemoryBoardsRepository();

    @AfterEach
    public void afterEach(){    // 콜백 메서드  테스트 하나 끝날 때 마다 콜백 메서드
        boardsRepository.clearStore();  // 테스트가 끝날 때마다 저장소를 지운다
    }

    @Test
    public void save(){

        for(int i=1; i<5; i++) {
            Boards boards = new Boards();
            boards.setBoardId(boards.getBoardId());

            boardsRepository.save(boards);

            Boards result = boardsRepository.findById(boards.getBoardId()).get();

//        System.out.println("result = " + (result == boards));
//        System.out.println(result);
//        Assertions.assertEquals(boards, result);  // boards = 기대 값 , result = 들어오는 값?
            assertThat(boards).isEqualTo(result);
            //import org.assertj.core.api.Assertions; 기억
            System.out.println("result = " + result);
            System.out.println("BoardsRepositoryTest.save");
        }
    }

    @Test
    public void findById(){
        Boards board1 = new Boards();
        board1.setBoardId(board1.getBoardId());
        boardsRepository.save(board1);

        Boards board2 = new Boards();
        board2.setBoardId(board2.getBoardId());
        boardsRepository.save(board2);
        System.out.println(board1 + " + "+ board2);

        Boards result = boardsRepository.findById(board2.getBoardId()).get();

        assertThat(result).isEqualTo(board2);


    }

    @Test
    public void findAll(){
        Boards boards1 = new Boards();
        boards1.setBoardId(boards1.getBoardId());
        boardsRepository.save(boards1);

        Boards boards2 = new Boards();
        boards2.setBoardId(boards2.getBoardId());
        boardsRepository.save(boards2);

        List<Boards> result = boardsRepository.findAll();  // findAll 의 타입은 List<> 타입

        Assertions.assertThat(result.size()).isEqualTo(2);  // 기대한 값은 2인데   입력 값이 3이라서 에러



    }



 *//*       Boards boards = new Boards();
        boards.setBoardTitle("게시판 제목");
        boards.setBoardDate(LocalDateTime.now());
        boards.setBoardTag("게시판 태그");
        boards.setBoardId(1L);
        boards.setBoardContent();
        boards.setBoardLike();
        boards.setBoardView();*//*



}*/