package es.profile.demo.mapper;

import java.util.Collection;
import java.util.List;

public interface ObjectMapper {

    /**
     * @param o     .
     * @param clazz .
     * @param <D>   .
     * @return .
     */
    <D> D map(Object o, Class<D> clazz);

    /**
     * @param oList        .
     * @param clazzDestino .
     * @param <D>          .
     * @return .
     */
    <D> Collection<D> map(Collection<?> oList, Class<D> clazzDestino);

    /**
     * @param oList        .
     * @param clazzDestino .
     * @param <D>          .
     * @return .
     */
    <D> List<D> map(List<?> oList, Class<D> clazzDestino);

}

