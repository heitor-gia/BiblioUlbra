package com.hgianastasio.biblioulbrav2.data.models.search.searchresult;

import com.hgianastasio.biblioulbrav2.core.base.boundaries.Mapper;
import com.hgianastasio.biblioulbrav2.core.search.models.SearchBook;
import com.hgianastasio.biblioulbrav2.core.search.models.SearchResult;
import com.hgianastasio.biblioulbrav2.data.models.search.searchbook.SearchBookEntity;
import com.hgianastasio.biblioulbrav2.data.models.search.searchbook.SearchBookEntityMapper;

import javax.inject.Inject;

/**
 * Created by heitor_12 on 09/05/17.
 */

public class SearchResultEntityMapper extends Mapper<SearchResult,SearchResultEntity> {
    SearchBookEntityMapper entityMapper;

    @Inject
    public SearchResultEntityMapper(SearchBookEntityMapper mapper) {
        this.entityMapper = mapper;
    }

    @Override
    public SearchResultEntity transform(SearchResult input) {
        return null;
    }

    @Override
    public SearchResult transformBack(SearchResultEntity input) {
        SearchResult output = new SearchResult();
        if(input.getScalar()==null){
            output.setResult(entityMapper.tansformBack(input.getResult()));
            output.setCookie(input.getCookie());
            output.setItens(input.getItens());
            output.setPages(input.getPages());
            output.setCurrentPage(input.getCurrentPage());
        }
        output.setEmptyResult(input.getScalar()!=null);
        return output;
    }
}
