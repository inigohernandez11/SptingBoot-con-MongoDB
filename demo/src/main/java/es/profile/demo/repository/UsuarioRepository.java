package es.profile.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends MongoRepository<es.profile.demo.repository.models.UsuarioRepository, String> {
}
