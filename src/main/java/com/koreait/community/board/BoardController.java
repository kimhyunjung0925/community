package com.koreait.community.board;

import com.koreait.community.Const;
import com.koreait.community.model.BoardDTO;
import com.koreait.community.model.BoardEntity;
import com.koreait.community.model.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService service;

    //-----리스트 가져오기
    @GetMapping("/list/{icategory}")
    public String list(@PathVariable int icategory, BoardDTO dto, Model model) {
        model.addAttribute(Const.I_CATEGORY, icategory);
        model.addAttribute(Const.List, service.selBoardList(dto));
        dto.setIcategory(icategory);
        return "board/list";
    }

    //-----글쓰기
    @GetMapping("/write")
    public void write() {}

    @PostMapping("/write")
    public String writeProc(BoardEntity entity) {
        int result = service.insBoard(entity);
        return "redirect:/board/list/" + entity.getIcategory();
    }

    //------글디테일
    @GetMapping("/detail")
    public void detail(Model model, BoardDTO dto, HttpServletRequest req){
        String lastIp = req.getHeader("X-FORWARDED-FOR");
        if(lastIp == null) {
           lastIp = req.getRemoteAddr();
        }
        dto.setLastip(lastIp);
        model.addAttribute(Const.DATA,service.selBoard(dto));
    }

    //-----글수정
    @GetMapping("/mod")
    public String mod(BoardDTO dto, Model model) {
        model.addAttribute(Const.DATA,service.selBoard(dto));
        return "board/write"; //write jsp쓸거라서 리턴값 String board/write로!
    }

    @PostMapping("/mod")
    private String modProc(BoardEntity entity){
        int result = service.updBoard(entity);
        return "redirect:/board/detail?iboard=" + entity.getIboard();
    }

    //----글삭제(DB에서는 삭제X)
    @GetMapping("/del")
    public String delProc(BoardEntity entity){
        int result = service.delBoard(entity);
        return "redirect:/board/list/" + entity.getIcategory();
    }

}