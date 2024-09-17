package antifraud.auth.mapper;

import antifraud.auth.dto.AuthenticationResponse;
import antifraud.auth.dto.LockStatusResponse;
import antifraud.auth.dto.RegisterRequest;
import antifraud.user.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationMapper {

    public User mapTO(RegisterRequest registerRequest) {
        return User.builder()
                .username(registerRequest.getUsername())
                .name(registerRequest.getName())
                .password(registerRequest.getPassword())
                .build();
    }


    public AuthenticationResponse mapTO(User user) {
        return AuthenticationResponse.builder()
                .username(user.getUsername())
                .name(user.getName())
                .id(user.getId())
                .role(user.getRole())
                .build();
    }

    public List<AuthenticationResponse> mapTO(List<User> users) {
        return users.stream().map(this::mapTO).toList();
    }

    public LockStatusResponse mapTOLockResponse(User user) {
        return LockStatusResponse.builder()
                .status("User " + user.getUsername() + " " + (user.getLocked()? "locked" : "unlocked")+"!")
                .build();
    }
}
