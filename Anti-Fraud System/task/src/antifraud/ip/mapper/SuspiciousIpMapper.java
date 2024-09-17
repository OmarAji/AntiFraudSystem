package antifraud.ip.mapper;

import antifraud.ip.Entity.SuspiciousIp;
import antifraud.ip.dto.SuspiciousIpDeleteResponse;
import antifraud.ip.dto.SuspiciousIpRequest;
import antifraud.ip.dto.SuspiciousIpResponse;
import org.springframework.stereotype.Service;

@Service
public class SuspiciousIpMapper {

    public SuspiciousIp mapToIp(SuspiciousIpRequest request) {
        return SuspiciousIp.builder()
                .ip(request.getIp())
                .build();
    }

    public SuspiciousIpResponse mapFromIp(SuspiciousIp suspiciousIp) {
        return SuspiciousIpResponse.builder()
                .ip(suspiciousIp.getIp())
                .id(suspiciousIp.getId())
                .build();
    }

    public SuspiciousIpDeleteResponse mapFromIpToDeleteResponse(SuspiciousIp suspiciousIp) {
        return SuspiciousIpDeleteResponse.builder()
                .status("IP"
                        + " "
                        + suspiciousIp.getIp()
                        + " "
                        + "successfully removed!"
                )
                .build();
    }
}
