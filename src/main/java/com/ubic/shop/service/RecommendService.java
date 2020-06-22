package com.ubic.shop.service;

import com.ubic.shop.domain.Product;
import com.ubic.shop.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RecommendService {

    public List<Product> getRecommendList(SessionUser user) {
        // es 에서 user 관련 데이터 중 - 쿼리실행
        // 값이 가장 높은 카테고리 가져오기

        // 해당 카테고리의 상품 4개 반환하기

        // 가져온 카테고리가 없다면 {하드코딩} 카테고리의 상품 4개 출력하기 - category id: 907001
        return null;
    }
}
