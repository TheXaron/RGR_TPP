package lab3.components;

import lab3.models.Account;
import lab3.models.SpaceObject;
import lab3.repositories.SpaceRepo;
import lab3.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class InitData implements CommandLineRunner {
    private final SpaceRepo sRepo;
    private final UserRepo uRepo;
    private final PasswordEncoder enc;

    @Override
    public void run(String... args) {
        if (uRepo.count() == 0) {
            uRepo.save(new Account(null, "admin", enc.encode("admin123"), "ROLE_ADMIN"));
            SpaceObject sun = sRepo.save(new SpaceObject(null, "Сонце", "Зірка", null));
            SpaceObject earth = sRepo.save(new SpaceObject(null, "Земля", "Планета", sun.getId()));
            sRepo.save(new SpaceObject(null, "Місяць", "Супутник", earth.getId()));
            log.info("РГР: База ініціалізована деревом Сонце->Земля->Місяць");
        }
    }
}