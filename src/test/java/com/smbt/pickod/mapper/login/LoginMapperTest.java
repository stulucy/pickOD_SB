package com.smbt.pickod.mapper.login;

import com.smbt.pickod.dto.login.LoginInputDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
class LoginMapperTest {
    @Autowired
    private LoginMapper loginMapper;

    public LoginInputDTO logputDTO;

    //제대로 된 로그인 시도 - memberNum을 제대로 가져옴
    @Test
    public void getMmbrIdTest(){
        logputDTO = new LoginInputDTO();
        logputDTO.setMemberId("kardiem@naver.com");
        logputDTO.setMemberPassword("ASDF1234");

        Long memberNum = loginMapper.isMemberExist(logputDTO).
                orElseThrow(()->new AssertionError("아이디 비밀번호 조합이 맞지 않습니다"));

        assertThat(memberNum).isEqualTo(6L);
    }

    //이메일 혹은 비밀번호가 안맞아서 memberNum을 못가지고 올 경우
    @Test
    public void getMmbrIdTestMiss(){
        logputDTO = new LoginInputDTO();
        logputDTO.setMemberId("kardiem@naver.com");
        logputDTO.setMemberPassword("ASDF1235");

        Long memberNum = loginMapper.isMemberExist(logputDTO).
                orElse(-1L);

        assertThat(memberNum).isEqualTo(-1L);
    }
}