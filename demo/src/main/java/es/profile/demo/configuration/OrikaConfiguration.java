package es.profile.demo.configuration;

import es.profile.demo.mapper.ObjectMapper;
import es.profile.demo.mapper.OrikaObjectMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.Optional;

@Configuration
public class OrikaConfiguration {

    @Bean("orikaMapper")
    @Primary
    public ObjectMapper orikaObjectMapper(){
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        return new OrikaObjectMapper(Optional.of(mapperFactory));
    }
}
