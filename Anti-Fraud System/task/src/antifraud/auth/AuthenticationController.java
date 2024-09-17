package antifraud.auth;

import antifraud.auth.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/user")
    public ResponseEntity<AuthenticationResponse> registerUser(@RequestBody RegisterRequest registerRequest) {
        var responseBody = authenticationService.registerUser(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }

    @GetMapping("/list")
    public ResponseEntity<List<AuthenticationResponse>> listAllUser() {
        var responseBody = authenticationService.getAllUsers();
        return ResponseEntity.ok().body(responseBody);
    }

    @DeleteMapping("/user/{username}")

    public ResponseEntity<DeletionResponse> deleteUser(@PathVariable String username) {
        var responseBody = authenticationService.deleteUser(username);
        return ResponseEntity.ok().body(responseBody);
    }

    @PutMapping("/role")
    public ResponseEntity<AuthenticationResponse> changeRole(
            @RequestBody RoleRequest roleRequest,
            Authentication connectedUser
    ) {
        var responseBody = authenticationService.changeRole(roleRequest,connectedUser);
        return ResponseEntity.ok().body(responseBody);
    }


    @PutMapping("/access")
    public ResponseEntity<LockStatusResponse> changeLockStatus(@RequestBody LockStatusRequest lockStatusRequest,
                                                               Authentication connectedUser
    ) {
        var responseBody = authenticationService.changeLockStatus(lockStatusRequest,connectedUser);
        return ResponseEntity.ok().body(responseBody);
    }
}
