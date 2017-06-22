package com.hgianastasio.biblioulbrav2.data.models.search.searchmodel;

import com.hgianastasio.biblioulbrav2.core.base.boundaries.Mapper;
import com.hgianastasio.biblioulbrav2.core.search.constants.SearchBase;
import com.hgianastasio.biblioulbrav2.core.search.constants.SearchField;
import com.hgianastasio.biblioulbrav2.core.search.constants.SearchLanguage;
import com.hgianastasio.biblioulbrav2.core.search.constants.SearchMedia;
import com.hgianastasio.biblioulbrav2.core.search.models.SearchModel;

import javax.inject.Inject;

/**
 * Created by heitor_12 on 09/05/17.
 */

public class SearchModelEntityMapper extends Mapper<SearchModel,SearchModelEntity> {
    @Inject public SearchModelEntityMapper(){}

    @Override
    public SearchModelEntity transform(SearchModel input) {
        SearchModelEntity output = new SearchModelEntity();
        output.setExact(input.isExact()?"Y":"N");
        output.setCookie(input.getCookie());
        output.setSearch(input.getSearch());
        output.setPage(input.getPage());
        output.setBase(input.getBase()!=null?input.getBase().getCode():"");
        output.setMedia(input.getMedia()!=null?input.getMedia().getCode():"");
        output.setField(input.getField()!=null?input.getField().getCode():"");
        output.setLanguage(input.getField()!=null?input.getLanguage().getCode():"");

        return output;
    }

    @Override
    public SearchModel transformBack(SearchModelEntity input) {
        SearchModel output = new SearchModel();
        output.setBase(SearchBase.findByCode(input.getBase()));
        output.setMedia(SearchMedia.findByCode(input.getMedia()));
        output.setField(SearchField.findByCode(input.getField()));
        output.setLanguage(SearchLanguage.findByCode(input.getLanguage()));
        output.setExact(input.isExact().equalsIgnoreCase("y"));
        output.setCookie(input.getCookie());
        output.setSearch(input.getSearch());
        output.setPage(input.getPage());
        return output;
    }
}
