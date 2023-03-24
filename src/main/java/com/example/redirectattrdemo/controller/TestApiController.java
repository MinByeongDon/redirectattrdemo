package com.example.redirectattrdemo.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.FlashMapManager;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.example.redirectattrdemo.vo.DummyKmcResultContext;
import com.example.redirectattrdemo.vo.SpaInfoResponseVO;
import com.example.redirectattrdemo.vo.SpaInfoResponseVO.DummyUserInfo;
import com.example.redirectattrdemo.vo.SpaInfoResponseVO.SpaInfoResponseVOBuilder;

import lombok.extern.slf4j.Slf4j;

/**
 * API 컨트롤러
 * 
 * @author deepfree
 */
@RestController
@Controller
@Slf4j
public class TestApiController {
    
    /**
     * SPA가 최초에 초기화를 위해 호출하는 API
     * - 각종 정책전달
     * - 최초 방문타입에 의한 회원정보등
     * 
     * - (옵션) 재진입시 KMC콜백처리결과문맥등 전달
     */
    @RequestMapping("/api/spainfo")
    public SpaInfoResponseVO spainfo(HttpServletRequest request,  HttpServletResponse response) {
        
        //1. SPA초기화정보 전달 (최초방문/재방문)
        SpaInfoResponseVOBuilder resultBuilder = SpaInfoResponseVO.builder()
            .userinfo(DummyUserInfo.builder()
            .userName("홍길동")
            .build());
            
        //2. 재진입문맥 있는 경우 전달
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
        if(flashMap != null) {
            //KMC콜백 처리결과 있는 경우 전달
            DummyKmcResultContext kmcResult = (DummyKmcResultContext) flashMap.getOrDefault("kmcResult", null);
            log.info("/api/spainfo => KMC콜백처리결과: {}", kmcResult);
            if (kmcResult != null) {
                resultBuilder.kmcResult(kmcResult);
            }
        }

        return resultBuilder.build();
    }

}
