package com.hgianastasio.biblioulbrav2.core.base.boundaries;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by heitorgianastasio on 4/24/17.
 */

public abstract class Mapper<I,O> {

    public abstract O transform(I input);


    public final List<O> trasform(List<I> input){
        List<O> result = new LinkedList<>();
        for (I inputObj : input)
            result.add(transform(inputObj));
        return result;
    }


    public abstract I transformBack(O input);


    public final List<I> tansformBack(List<O> input){
        List<I> result = new LinkedList<>();
        for (O inputObj : input)
            result.add(transformBack(inputObj));
        return result;

    }
}
