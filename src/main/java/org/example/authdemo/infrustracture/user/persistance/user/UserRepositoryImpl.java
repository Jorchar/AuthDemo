package org.example.authdemo.infrustracture.user.persistance.user;

import lombok.AllArgsConstructor;
import org.example.authdemo.domain.user.model.Role;
import org.example.authdemo.domain.user.model.User;
import org.example.authdemo.domain.user.port.UserRepository;
import org.example.authdemo.infrustracture.user.persistance.EntityNotFoundException;
import org.example.authdemo.infrustracture.user.persistance.role.RoleJpa;
import org.example.authdemo.infrustracture.user.persistance.role.RoleJpaMapper;
import org.example.authdemo.infrustracture.user.persistance.role.RoleJpaRepository;
import org.example.authdemo.infrustracture.user.utils.Const;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Component
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;
    private final RoleJpaRepository roleJpaRepository;

    @Override
    public User getUserByEmail(String email) {
        var userJpa = userJpaRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "User not found"));
        return UserJpaMapper.mapToDomainUser(userJpa);
    }

    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        return userJpaRepository.findAll(pageable)
                .map(UserJpaMapper::mapToDomainUser);
    }

    @Override
    public User save(User user) {
        var userJpa = new UserJpa(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
        RoleJpa defaultRole = roleJpaRepository.findByName(Const.DEFAULT_ROLE)
                .orElseThrow(() -> new EntityNotFoundException("Role not found"));
        userJpa.addRole(defaultRole);
        var createdUser = userJpaRepository.save(userJpa);
        return UserJpaMapper.mapToDomainUser(createdUser);
    }

    @Override
    public User patchUserFirstNameByEmail(String firstName, String email) {
        UserJpa userJpa = userJpaRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        userJpa.setFirstName(firstName);
        userJpaRepository.save(userJpa);
        return UserJpaMapper.mapToDomainUser(userJpa);
    }

    @Override
    public void deleteUserByEmail(String email) {
        UserJpa userJpa = userJpaRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        userJpaRepository.delete(userJpa);

    }

    @Override
    public User updateUser(User domainUser) {
        UserJpa userJpa = userJpaRepository.findByEmail(domainUser.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        userJpa.setFirstName(domainUser.getFirstName());
        userJpa.setLastName(domainUser.getLastName());
        return UserJpaMapper.mapToDomainUser(userJpa);
    }

    @Override
    public List<Role> addUserRole(User domainUser, Role domainRole) {
        UserJpa userJpa = userJpaRepository.findByEmail(domainUser.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        RoleJpa roleJpa = roleJpaRepository.findByName(domainRole.getName())
                .orElseThrow(() -> new EntityNotFoundException("Role not found"));
        userJpa.addRole(roleJpa);
        userJpaRepository.save(userJpa);
        return userJpa.getRoles().stream()
                .map(RoleJpaMapper::mapToDomainRole).toList();
    }

    @Override
    public List<Role> removeUserRole(User domainUser, Role domainRole) {
        UserJpa userJpa = userJpaRepository.findByEmail(domainUser.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        RoleJpa roleJpa = roleJpaRepository.findByName(domainRole.getName())
                .orElseThrow(() -> new EntityNotFoundException("Role not found"));
        userJpa.removeRole(roleJpa);
        userJpaRepository.save(userJpa);
        return userJpa.getRoles().stream()
                .map(RoleJpaMapper::mapToDomainRole).toList();
    }
}
