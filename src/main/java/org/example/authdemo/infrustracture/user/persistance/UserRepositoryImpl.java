package org.example.authdemo.infrustracture.user.persistance;

import lombok.AllArgsConstructor;
import org.example.authdemo.domain.user.model.User;
import org.example.authdemo.domain.user.port.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Component
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    @Override
    public User getUserByFirstName(String firstName) {
        var userJpa = userJpaRepository.findByFirstName(firstName)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "User not found"));
        return UserJpaMapper.mapToDomainUser(userJpa);
    }

    @Override
    public List<User> getAllUsers() {
        var jpaUsers = userJpaRepository.findAll();
        return UserJpaMapper.mapToDomainUserList(jpaUsers);
    }

    @Override
    public User save(User user) {
        var userJpa = new UserJpa(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
        var createdUser = userJpaRepository.save(userJpa);
        return UserJpaMapper.mapToDomainUser(createdUser);
    }

    @Override
    public User patchUserFirstNameByEmail(String firstName, String email) {
        UserJpa userJpa = userJpaRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "User not found"));
        userJpa.setFirstName(firstName);
        userJpaRepository.save(userJpa);
        return UserJpaMapper.mapToDomainUser(userJpa);
    }

    @Override
    public void deleteUserByEmail(String email) {
        UserJpa userJpa = userJpaRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "User not found"));
        userJpaRepository.delete(userJpa);

    }

    @Override
    public User updateUser(User domainUser) {
        UserJpa userJpa = userJpaRepository.findByEmail(domainUser.getEmail())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "User not found"));
        userJpa.setFirstName(domainUser.getFirstName());
        userJpa.setLastName(domainUser.getFirstName());
        return UserJpaMapper.mapToDomainUser(userJpa);
    }
}
