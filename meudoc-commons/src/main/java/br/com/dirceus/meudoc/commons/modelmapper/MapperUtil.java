package br.com.dirceus.meudoc.commons.modelmapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

public class MapperUtil {

	private static ModelMapper modelMapper = new ModelMapper();


    private MapperUtil() {


    }

    public static <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {

        return source
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }
	
}