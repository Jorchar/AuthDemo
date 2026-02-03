package org.example.authdemo.infrustracture.user.persistance;

import lombok.AllArgsConstructor;
import org.example.authdemo.domain.user.model.User;
import org.example.authdemo.domain.user.port.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Component
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;
    private final UserJpaMapper userJpaMapper;

    @Override
    public User save(User user) {
        var userJpa = new UserJpa(user.getFirstName(), user.getLastName());
        userJpaRepository.save(userJpa);
        return user;
    }

    @Override
    public User patchUserFirstNameByLastName(String firstName, String lastName) {
        UserJpa userJpa = userJpaRepository.findFirstByLastName(lastName)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "User not found"));
        userJpa.setFirstName(firstName);
        userJpaRepository.save(userJpa);
        return userJpaMapper.mapToDomainUser(userJpa);
    }
}
