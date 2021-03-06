package com.ohmyraid.rest.account;

import com.ohmyraid.common.wrapper.ResultView;
import com.ohmyraid.dto.account.SignUpInpDto;
import com.ohmyraid.dto.account.SignUpResDto;
import com.ohmyraid.service.account.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@Api(tags = "AccountController")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @ApiOperation(value = "회원가입",notes = "로그인이메일, 패스워드, 별명을 입력하여 계정을 등록한다.")
    @PostMapping(value = "/signup")
    public ResultView<SignUpResDto> signUp(
            @RequestBody SignUpInpDto signUpInpDto){
        return new ResultView<>(accountService.signUp(signUpInpDto));
    }

}
