package com.lectopia.board;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.lectopia.board.domain.BoardVO;
import com.lectopia.board.persistence.BoardMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })
public class BoardDAOTest {
  @Autowired
  private BoardMapper boardMapper;

  private static Logger logger = LoggerFactory.getLogger(BoardDAOTest.class);

  @Test
  public void testCreate() throws Exception {

    BoardVO board = new BoardVO();
    board.setTitle("테스트 게시물");
    board.setContent("테스트 내용");
    board.setWriter("user01");
    boardMapper.create(board);
  }

  @Test
  public void testRead() throws Exception {
    BoardVO board = boardMapper.read(2);
    
    assertNotNull(board);
    assertEquals("user01", board.getWriter());
  }

  @Test
  public void testUpdate() throws Exception {
    BoardVO board = new BoardVO();
    board.setBno(1);
    board.setTitle("Test board (modified)");
    board.setContent("This is a the modified contents.");
    boardMapper.update(board);
  }

  @Test
  public void testDelete() throws Exception {
	  boardMapper.delete(1);
  }

  @Test
  public void testListAll() throws Exception {
    logger.info(boardMapper.listAll().toString());
  }
  
  @Test
  public void testURI(){
	  UriComponents uriComponents = UriComponentsBuilder.newInstance()
			  .path("/board/read")
			  .queryParam("bno", 12)
			  .queryParam("perPageNum", 20)
			  .build();
	  
	  logger.info("/board/read?bno=12&perPageNum=20");
	  logger.info(uriComponents.toString());
  }
}
