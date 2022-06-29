package com.jumbo.neareststores.model.response;

import com.jumbo.neareststores.entity.Store;
import lombok.Data;

import java.util.List;

@Data
public class SearchFilterResponse {
    private List<Store> stores;
    private Boolean hasNext;

}
