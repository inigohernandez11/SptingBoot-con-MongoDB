package es.profile.demo.mapper;

import lombok.Getter;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
public class OrikaObjectMapper implements ObjectMapper {

    private final MapperFacade mapperFacade;

    public OrikaObjectMapper(Optional<MapperFactory> mapperFactory) {
        MapperFactory selectedMapperFactory = mapperFactory.orElseGet(() -> new DefaultMapperFactory.Builder().build());
        this.mapperFacade = selectedMapperFactory.getMapperFacade();
    }

    @Override
    public <D> D map(Object o, Class<D> clazz) {
        return mapperFacade.map(o, clazz);
    }

    @Override
    public <D> Collection<D> map(Collection<?> oList, Class<D> clazzDestino) {
        return mapCollection(oList, clazzDestino);
    }

    @Override
    public <D> List<D> map(List<?> oList, Class<D> clazzDestino) {
        return mapCollection(oList, clazzDestino);
    }


    private <D> List<D> mapCollection(Collection<?> oList, Class<D> clazzDestino) {
        if (Objects.isNull(oList)) {
            return null;
        }
        return oList.stream().map(o-> map(o,clazzDestino)).collect(Collectors.toList());
    }

}
