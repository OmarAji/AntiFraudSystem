package antifraud.ip;

import antifraud.ip.dto.SuspiciousIpDeleteResponse;
import antifraud.ip.dto.SuspiciousIpRequest;
import antifraud.ip.dto.SuspiciousIpResponse;
import antifraud.ip.validation.IPv4Validator;
import antifraud.ip.validation.ValidIPv4;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/antifraud/suspicious-ip")
@RequiredArgsConstructor
@Validated
public class SuspiciousIpController {

    private final SuspiciousIpService suspiciousIpService;

    @Autowired
    private Validator validator;


    @PostMapping()
    public ResponseEntity<SuspiciousIpResponse> registerSuspiciousIp(@RequestBody @Valid SuspiciousIpRequest suspiciousIpRequest) {
        var response = suspiciousIpService.registerSuspiciousIp(suspiciousIpRequest);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{ip}")
    public ResponseEntity<SuspiciousIpDeleteResponse> deleteSuspiciousIp(@Valid @ValidIPv4 @PathVariable(name= "ip")
                                                                             String ip
    ) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>><");
        System.out.println(ip);
        var response = suspiciousIpService.deleteSuspiciousIp(ip);
        return ResponseEntity.ok().body(response);
    }


    @GetMapping()
    public ResponseEntity<List<SuspiciousIpResponse>> getSuspiciousIps() {

        var response = suspiciousIpService.getSuspiciousIps();
        return ResponseEntity.ok().body(response);
    }


}
