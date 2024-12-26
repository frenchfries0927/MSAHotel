package com.playdata.front_admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TableDTO {
    private int currentPage;
    private int totalPages;
    private int prevPage;
    private int nextPage;
}
