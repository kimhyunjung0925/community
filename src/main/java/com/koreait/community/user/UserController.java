package com.koreait.community.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.koreait.community.Const;
import com.koreait.community.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    //------로그인 처리
    @GetMapping("/login")
    public void login() {}

    @PostMapping("/login")
    public String loginProc(UserEntity entity, RedirectAttributes reAttr) {
        int result = service.login(entity);
        if(result != 1) {
            reAttr.addFlashAttribute(Const.TRY_LOGIN, entity); //실패시 세션에서 값받아와서 원래 값 그대로 출력할 수 있는..?
            switch(result) {
                case 0:
                    //reAttr.addAttribute("","") // get 방식 이용
                    reAttr.addFlashAttribute(Const.MSG, Const.ERR_1); //세션 이용해 값 전달
                    break;
                case 2:
                    reAttr.addFlashAttribute(Const.MSG, Const.ERR_2);
                    break;
                case 3:
                    reAttr.addFlashAttribute(Const.MSG, Const.ERR_3);
                    break;
            }
            return "redirect:/user/login";
        }
        return "redirect:/board/list/1";
    }

    //-----로그아웃 처리
    @GetMapping("/logout")
    public String logout(HttpSession hs){
        hs.invalidate();
        return "redirect:/user/login";
    }

    //-----회원가입 처리
    @GetMapping("/join")
    public void join() {}

    @PostMapping("/join")
    public String joinProc(UserEntity entity,RedirectAttributes reAttr) {
        int result = service.join(entity);
        if(result == 0){
            reAttr.addFlashAttribute(Const.MSG,Const.ERR_4);
            return "redirect:/user/join";
        }
         //회원가입 성공하면 로그인 처리
        service.login(entity); //planepw 넘어가야함
        return "redirect:/board/list";
    }

    //---같은 아이디 있는지 체크
    @GetMapping("/idChk/{uid}")
    @ResponseBody //return이 json 받을때는 requestBody?
    public Map<String ,Integer> idChk(@PathVariable String uid){
        System.out.println("uid : " + uid);
        Map<String, Integer> res = new HashMap();
        res.put("result", service.idChk(uid));
        return res;
    }

    //============================마이페이지================================//

    //----프로필
    @GetMapping("/mypage/profile")
    public void mypageProfile(){
    }

    @ResponseBody
    @PostMapping("/mypage/profile")
    public String mypageProfileProc(MultipartFile profileimg){ //profile js에 fData name 이랑 맞춰줘야함
        System.out.println("fileName: " + profileimg.getOriginalFilename());
        return "Good!";
    }
}
