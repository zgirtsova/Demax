package app.domain.dto;

import org.modelmapper.ExpressionMap;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public final class DTOConvertUtil {

    private DTOConvertUtil() {
    }

    public static <S, D> D convert(S source, Class<D> destinationClass) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(source, destinationClass);
    }

    public static <S, D> List<D> convert(Iterable<S> sourceIter, Class<D> destinationClass) {
        ModelMapper mapper = new ModelMapper();
        List<D> resultList = new ArrayList<>();
        for (S s : sourceIter) {
            D d = convert(s, destinationClass);
            resultList.add(d);
        }

        return resultList;
    }

    public static <S, D> Set<D> convertToSet(Iterable<S> sourceIter, Class<D> destinationClass) {
        ModelMapper mapper = new ModelMapper();
        Set<D> resultSet = new HashSet<>();
        for (S s : sourceIter) {
            D d = convert(s, destinationClass);
            resultSet.add(d);
        }

        return resultSet;
    }


    private  static <S, D> D convertCustom(S source, Class<D> destClass, ExpressionMap<S, D>... exprMaps) {
        ModelMapper mapper = new ModelMapper();
        TypeMap<S, D> typeMap = mapper.createTypeMap((Class<S>) source.getClass(), destClass);
        for (ExpressionMap<S, D> exprMap : exprMaps) {
            typeMap.addMappings(exprMap);
        }
        return typeMap.map(source);
    }

}
