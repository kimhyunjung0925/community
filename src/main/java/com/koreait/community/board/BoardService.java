package com.koreait.community.board;

import com.koreait.community.UserUtils;
import com.koreait.community.model.BoardDTO;
import com.koreait.community.model.BoardEntity;
import com.koreait.community.model.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class BoardService {
    @Autowired
    private BoardMapper mapper;

    @Autowired
    private UserUtils userUtils;

    //----글 리스트
    public List<BoardVO> selBoardList(BoardDTO dto) {
        return mapper.selBoardList(dto);
    }

    //----글 작성
    public int insBoard(BoardEntity entity) {
        entity.setIuser(userUtils.getLoginUserPk());
        return mapper.insBoard(entity);
    }

    //----글디테일, hits up
    public BoardVO selBoard(BoardDTO dto) {
        BoardVO detail = mapper.selBoard(dto);
        if (dto.getLastip() != null && !Objects.equals(dto.getLastip(), detail.getLastip())) {  //iboard, lastip
            int hitsResult = mapper.addHits(dto);
            if (hitsResult == 1) {
                detail.setHits(detail.getHits() + 1); // dbip랑 방금들어온 ip주소가 달랐을 때 hits 1올림
            }
        }
        return detail;
    }

    //-----글삭제
    public int delBoard(BoardEntity entity) { //icategory, iboard
        entity.setIuser(userUtils.getLoginUserPk());
        entity.setIsdel(1);
        return mapper.updBoard(entity); //icategory, iboard, iuser, isdel
    }

    //-----글수정
    public int updBoard(BoardEntity entity){
        try {
            entity.setIuser(userUtils.getLoginUserPk());
            return mapper.updBoard(entity);
        } catch (Exception e){
            e.printStackTrace();
            return 2; //
        }
    }
}
