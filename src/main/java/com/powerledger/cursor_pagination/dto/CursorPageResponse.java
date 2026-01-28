package com.powerledger.cursor_pagination.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CursorPageResponse<T> {
    private List<T> data;
    private String nextCursor;
}
