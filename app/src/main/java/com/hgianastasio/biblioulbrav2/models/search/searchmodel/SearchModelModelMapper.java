package com.hgianastasio.biblioulbrav2.models.search.searchmodel;

import com.hgianastasio.biblioulbrav2.core.base.boundaries.Mapper;
import com.hgianastasio.biblioulbrav2.core.search.constants.SearchBase;
import com.hgianastasio.biblioulbrav2.core.search.constants.SearchField;
import com.hgianastasio.biblioulbrav2.core.search.constants.SearchLanguage;
import com.hgianastasio.biblioulbrav2.core.search.constants.SearchMedia;
import com.hgianastasio.biblioulbrav2.core.search.models.SearchModel;

import javax.inject.Inject;

/**
 * Created by heitor_12 on 31/05/17.
 */

public class SearchModelModelMapper extends Mapper<SearchModel,SearchModelModel> {
    @Inject public SearchModelModelMapper() {}

    @Override
    public SearchModelModel transform(SearchModel input) {
        SearchModelModel output = new SearchModelModel();
        output.setBase(input.getBase().getId());
        output.setCookie(input.getCookie());
        output.setSearch(input.getSearch());
        output.setExact(input.isExact());
        output.setField(input.getField().getId());
        output.setMedia(input.getMedia().getId());
        output.setLanguage(input.getLanguage().getId());
        output.setPage(input.getPage());
        return output;
    }

    @Override
    public SearchModel transformBack(SearchModelModel input) {
        SearchModel output = new SearchModel();
        output.setSearch(input.getSearch());
        output.setPage(input.getPage());
        output.setCookie(input.getCookie());
        output.setExact(input.isExact());
        output.setBase(SearchBase.findById(input.getBase()));
        output.setField(SearchField.findById(input.getField()));
        output.setMedia(SearchMedia.findById(input.getMedia()));
        output.setLanguage(SearchLanguage.findById(input.getLanguage()));
        return output;
    }
}
