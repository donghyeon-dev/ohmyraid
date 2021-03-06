package com.ohmyraid.dto.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * ThreadLocal에 담길 토큰 VO
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ThreadInfDto {

    private String accessToken;


}
