package com.example.redirectattrdemo.vo;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;

import lombok.Builder;
import lombok.Data;

/** SPA초기화정보 */
@Data
@Builder
public class SpaInfoResponseVO implements Serializable {

    /** 사용자정보 */
    @NotNull 
    private DummyUserInfo userinfo;

    /** 옵션: KMC처리결과 */
    @Nullable 
    private DummyKmcResultContext kmcResult;

    /** SPA초기화정보.사용자정보 */
    @Data
    @Builder
    public static class DummyUserInfo implements Serializable {
        @NotNull @NotEmpty
        private String userName;
    }
}