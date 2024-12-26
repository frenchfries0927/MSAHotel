package com.playdata.front_admin.service;

import com.playdata.front_admin.dto.TableDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class TableService {

    // 페이지 번호와 크기를 기준으로 Page 객체를 반환
    public Pageable getPageable(int pageNo, int pageSize) {
        return PageRequest.of(pageNo, pageSize);
    }

    // 페이징 정보 (현재 페이지 번호, 총 페이지 수, 이전 페이지, 다음 페이지)를 담은 TableDTO 반환
    public TableDTO getPageInfo(Page<?> page) {
        int currentPage = page.getNumber();
        int totalPages = page.getTotalPages();
        int prevPage = (currentPage > 0) ? currentPage - 1 : 0;
        int nextPage = (currentPage < totalPages - 1) ? currentPage + 1 : totalPages - 1;

        // TableDTO 객체로 페이징 정보 반환
        return new TableDTO(currentPage, totalPages, prevPage, nextPage);
    }
}
