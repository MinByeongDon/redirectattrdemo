package com.example.redirectattrdemo.view;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

/**
 * SPA 이용시 RedirectAttribute를 특정 API로 전달하기 위한 뷰
 * 
 * - 기본 RedirectView는 RedirectAttribute를 Redirect Location으로만 전달가능
 * - SPA 페이지로 Redirect할 경우, RedirectAttribute를 특정 API로 전달해야함
 */
public class SpaRedirectView extends RedirectView {
    
    /** RedirectAttribute를 전달할 대상 API URL */
    private String targetApiUrl;

    public SpaRedirectView(String url, String targetApiUrl) {
        super(url);
        this.targetApiUrl = targetApiUrl;
    }

    public SpaRedirectView(String url, boolean contextRelative, String targetApiUrl) {
        super(url, contextRelative);
        this.targetApiUrl = targetApiUrl;
    }

    public SpaRedirectView(String url, boolean contextRelative, boolean http10Compatible, String targetApiUrl) {
        super(url, contextRelative, http10Compatible);
        this.targetApiUrl = targetApiUrl;
    }

    public SpaRedirectView(String url, boolean contextRelative, boolean http10Compatible, boolean exposeModelAttributes,
            String targetApiUrl) {
        super(url, contextRelative, http10Compatible, exposeModelAttributes);
        this.targetApiUrl = targetApiUrl;
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        //상위 구현은 그대로 실행
        super.renderMergedOutputModel(model, request, response);

        //대상URL을 API 타겟URL로 바꿔서 OutputFlashMap 저장 (원래 OutputFlashMap은 없어짐)
		RequestContextUtils.saveOutputFlashMap(targetApiUrl, request, response);
    }

}
