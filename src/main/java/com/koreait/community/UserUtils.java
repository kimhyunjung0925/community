package com.koreait.community;

import com.koreait.community.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
public class UserUtils {
    @Autowired
    private HttpSession hs;

    public void setLoginUser(UserEntity entity){ //session에 로그인 정보 담음
        hs.setAttribute(Const.LOGIN_USER, entity);
    }

    public UserEntity getLoginUser(){ //정보 담은거 가져옴
        return (UserEntity) hs.getAttribute(Const.LOGIN_USER);
    }

    public int getLoginUserPk(){ //로그인한 사람 iuser 값 가져옴, 로그인 안되어있으면 0
        return getLoginUser() == null ? 0 : getLoginUser().getIuser();
    }


}
