package antifraud.auth;


import antifraud.auth.dto.*;
import antifraud.auth.mapper.AuthenticationMapper;
import antifraud.user.*;
import antifraud.user.exception.*;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final AuthenticationMapper authenticationMapper;


    public AuthenticationResponse registerUser(RegisterRequest registerRequest) throws RuntimeException {
        if (Strings.isBlank(registerRequest.getName())
                || Strings.isBlank(registerRequest.getUsername())
                || Strings.isBlank(registerRequest.getPassword())
        ) {
            throw new RegistrationException("user can not be created: missing some values.");
        }
        userRepository.findByUsername(registerRequest.getUsername())
                .ifPresent(user -> {
                    throw new UserAlreadyExistException("User already in the Database with the username:" + user.getUsername());
                });
        User user = authenticationMapper.mapTO(registerRequest);
        user.setRole(
                ((userRepository.count() > 0) ? "MERCHANT" : "ADMINISTRATOR"  )
        );
        user.setLocked(!user.getRole().equals("ADMINISTRATOR"));
        return authenticationMapper.mapTO(userRepository.save(user));
    }

    public List<AuthenticationResponse> getAllUsers() {
        return authenticationMapper.mapTO(userRepository.findAll());
    }

    public DeletionResponse deleteUser(String username) throws RuntimeException {
        var user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UserNotFoundExecution("User not found ");
        }
        userRepository.delete(user.get());
        return DeletionResponse.builder()
                .username(username)
                .build();

    }

    public AuthenticationResponse changeRole(RoleRequest roleRequest, Authentication connectedUser) throws RuntimeException {
        connectedUser.getAuthorities();
        var user = userRepository.findByUsername(roleRequest.getUsername());
        if (user.isEmpty()) {
            throw new UserNotFoundExecution("User not found ");
        }
        if (!("SUPPORT".equals(roleRequest.getRole()) || "MERCHANT".equals(roleRequest.getRole()))) {
            throw new NotAllowedRoleException("Role can not be change to" + roleRequest.getUsername());
        }
        if (roleRequest.getRole().equals(user.get().getRole())) {
            throw new RoleAlreadyAssignException("User already have this role");
        }
        user.get().setRole(roleRequest.getRole());

        return authenticationMapper.mapTO(userRepository.save(user.get()));

    }

    public LockStatusResponse changeLockStatus(LockStatusRequest lockStatusRequest, Authentication connectedUser) {
        var user = userRepository.findByUsername(lockStatusRequest.getUsername());
        if (user.isEmpty()) {
            throw new UserNotFoundExecution("User not found ");
        }
        if ("ADMINISTRATOR".equals(user.get().getRole())) {
            throw new UserCanNotLockedException("user can not locked");
        }
        user.get().setLocked("LOCK".equals(lockStatusRequest.getOperation()));
        var savedItem = userRepository.save(user.get());
        return authenticationMapper.mapTOLockResponse(savedItem);
    }
}
