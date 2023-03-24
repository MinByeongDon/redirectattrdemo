package com.example.redirectattrdemo.controller;

import java.time.ZonedDateTime;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.FlashMapManager;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import com.example.redirectattrdemo.view.SpaRedirectView;
import com.example.redirectattrdemo.vo.DummyKmcResultContext;

import lombok.extern.slf4j.Slf4j;

/**
 * SPA 페이지 컨트롤러
 * 
 * @author deepfree
 */
@Controller
@RequestMapping()
@Slf4j
public class TestController {
    
    /**
     * SPA 최초진입페이지
     */
    @RequestMapping({"/", "/index"})
    public String index(HttpServletRequest request) {
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
        if(flashMap != null) {
            //KMC콜백 처리결과 있는 경우 전달
            DummyKmcResultContext kmcResult = (DummyKmcResultContext) flashMap.getOrDefault("kmcResult", null);
            log.info("/ => KMC콜백처리결과: {}", kmcResult);
        }
        return "index";
    }

    /** 
     * KMC콜백페이지
     */
    @RequestMapping("/cbkmc")
    public View cbkmc(HttpServletRequest request, HttpServletResponse response, RedirectAttributes attributes) {
        //RedirectAttributes에 KMC처리결과문맥 저장
        attributes.addFlashAttribute("kmcResult", 
            DummyKmcResultContext.builder().callbackTime(ZonedDateTime.now().toString()).build()
        );

        //NOTE: 아래와 같이 TargetRequestPath을 직접설정해도 RedirectionUrl로 target이 고정됨
        FlashMap flashMap = RequestContextUtils.getOutputFlashMap(request);
        flashMap.startExpirationPeriod(30); //Flash맵 TTL설정
        // flashMap.setTargetRequestPath("/api/spainfo"); //NOTE: 이렇게 설정해도 RedirectionUrl로 target이 고정됨

        //메인 SPA페이지로 redirection처리
        //return new RedirectView("/");
        return new SpaRedirectView("/", "/api/spainfo");
    }

    

}
