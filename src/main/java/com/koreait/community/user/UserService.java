package com.koreait.community.user;

import com.koreait.community.Const;
import com.koreait.community.UserUtils;
import com.koreait.community.model.UserEntity;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class UserService {
    @Autowired
    private UserMapper mapper;

    @Autowired
    private UserUtils userUtils;

    //로그인하기
    public int login(UserEntity entity){
        UserEntity dbUser = null;
        try{
            dbUser = mapper.selUser(entity);
        } catch (Exception e){
            e.printStackTrace();
            return 0; //알 수 없는 에러
        }

        if(dbUser == null){
            return 2; //아이디 없음
        } else if(!BCrypt.checkpw(entity.getUpw(),dbUser.getUpw())){
            return 3; //비밀번호 틀림
        }
        dbUser.setUpw(null);
        dbUser.setMdt(null);
        dbUser.setRdt(null);
        userUtils.setLoginUser(dbUser);
        return 1; //로그인 성공
    }

    //비밀번호 암호화
    public int join(UserEntity entity){ //uid upw nm gender
        UserEntity copyEntity = new UserEntity();
        try{
            BeanUtils.copyProperties(entity, copyEntity); //깊은복사:객체 똑같은거 하나 더 만듬, 객체 하나의 값을 변경해도 다른 객체 값 안바뀜
        } catch (Exception e){
            e.printStackTrace();
        }
        //비밀번호 암호화
        String hashPw = BCrypt.hashpw(entity.getUpw(), BCrypt.gensalt());
        copyEntity.setUpw(hashPw);
        return mapper.insUser(copyEntity);
    }

    //아이디 중복체크, 아이디가 있으면 리턴0 없으면 리턴1
    public int idChk(String uid){
        UserEntity entity = new UserEntity();
        entity.setUid(uid);

        UserEntity result = mapper.selUser(entity);
        return  result == null ? 1 : 0;
    }


}

//깊은복사:객체 똑같은거 하나 더 만듬, 객체 하나의 값을 변경해도 다른 객체 값 안바뀜
