package com.ohmyraid.service.account;

import com.ohmyraid.common.result.ErrorResult;
import com.ohmyraid.domain.account.AccountEntity;
import com.ohmyraid.repository.account.AccountRepository;
import com.ohmyraid.vo.account.SignUpInpVo;
import com.ohmyraid.vo.account.SignUpResVo;
import jdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    public SignUpResVo signUp(SignUpInpVo signUpInpVo){
        accountRepository.save(AccountEntity.builder().
                email(signUpInpVo.getEmail())
                .password(signUpInpVo.getPassword())
        .build());

        List<AccountEntity> accountList = accountRepository.findAll();

        accountList = accountList.stream()
                .filter(a -> a.getEmail().equals(signUpInpVo.getEmail()))
                .collect(Collectors.toList());
        if(accountList.size() == 0){
            throw new InternalException("회원가입 오류");
        }else {
            SignUpResVo signUpResVo = new SignUpResVo();
            signUpResVo.setMessage("회원가입 성공");
            return signUpResVo;
        }
    }
}