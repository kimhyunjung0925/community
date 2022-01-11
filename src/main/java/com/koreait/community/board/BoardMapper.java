package com.koreait.community.board;

import com.koreait.community.model.BoardDTO;
import com.koreait.community.model.BoardEntity;
import com.koreait.community.model.BoardVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<BoardVO> selBoardList(BoardDTO dto);
    int insBoard(BoardEntity entity);
    BoardVO selBoard(BoardDTO dto);
    int addHits(BoardDTO dto);
    int updBoard(BoardEntity entity);

}
