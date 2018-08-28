package com.luizfelipe.cursomc.resources.utils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class URL {

    public static String decodeParam(String s){
        try {
            return URLDecoder.decode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
           // e.printStackTrace();
            return "";
        }
    }


    public static List<Integer> decodeIntList(String s){
        /*String[] vet = s.split(",");
        List<Integer> list = new ArrayList<>();

        for (int i =0; i<vet.length - 1; i++){
            list.add(Integer.parseInt(vet[i]));

        }
        return list;
        */

        // Nova forma com lambda
        return Arrays.asList(s.split(",")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());

    }
}
