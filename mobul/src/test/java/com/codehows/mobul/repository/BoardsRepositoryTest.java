package com.codehows.mobul.repository;

import static org.assertj.core.api.Assertions.*;

public class BoardsRepositoryTest {


}

//    @DataJpaTest 어노테이션을 통해 JPA 레포지토리 관련 테스트에 필요한 설정이 자동으로 이루어지도록 합니다. 이 테스트에서는 게시글 등록 후 등록된 게시글을 조회하는 테스트를 수행합니다. given, when, then 패턴에 따라 코드가 작성되었으며, 게시글의 필수 정보인 게시글 제목, 내용, 작성자, 작




/*

@SpringBootTest
class BoardsRepositoryTest {


    @Autowired
    UsersRepository usersRepository;

    @Autowired
    BoardsRepository boardsRepository;

    @Test
    public void InsertDummies() {
        Users users = new Users();
        // test 유저 생성
        users.setUserId("test");

        //테스트 케이스에 users 생성
        usersRepository.save(users);
        Users user = usersRepository.findByUserId("test"); // UsersRepository 에 메서드 추상 메서드 작성


        IntStream.rangeClosed(1, 10).forEach(i -> {
            Boards boards = new Boards();
            boards.setBoardTitle("test" + i);
            boards.setUsers(user);

            //create
            boardsRepository.save(boards);
        });
    }
*/

