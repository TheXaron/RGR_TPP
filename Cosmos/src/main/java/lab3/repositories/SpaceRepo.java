package lab3.repositories;

import lab3.models.SpaceObject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpaceRepo extends JpaRepository<SpaceObject, Long> {}