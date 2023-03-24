package com.example.redirectattrdemo.vo;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

/** 
 * KMC콜백처리결과문맥 
 * - 콜백받은 곳에서 FlashMap에 저장후
 * - SPA초기화정보API등에서 클라이언트에 전달
 * 
 * @author deepfree
 */
@Data
@Builder
public class DummyKmcResultContext implements Serializable {

    /** 콜백받은 시간 */
    private String callbackTime;
    
}
