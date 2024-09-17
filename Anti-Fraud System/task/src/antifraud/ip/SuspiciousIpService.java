package antifraud.ip;


import antifraud.ip.dto.SuspiciousIpDeleteResponse;
import antifraud.ip.dto.SuspiciousIpRequest;
import antifraud.ip.dto.SuspiciousIpResponse;
import antifraud.ip.exception.SuspiciousIpAlreadyExistException;
import antifraud.ip.exception.SuspiciousIpNotFoundException;
import antifraud.ip.mapper.SuspiciousIpMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SuspiciousIpService {

    private final SuspiciousIpRepository suspiciousIpRepository;
    private final SuspiciousIpMapper suspiciousIpMapper;

    public SuspiciousIpResponse registerSuspiciousIp(SuspiciousIpRequest suspiciousIpRequest) {

        var suspiciousIp = suspiciousIpRepository.findByIp(suspiciousIpRequest.getIp());
        if (suspiciousIp.isPresent()) {
            throw new SuspiciousIpAlreadyExistException("ip already registered");
        }
        var savedItem = suspiciousIpRepository.save(suspiciousIpMapper.mapToIp(suspiciousIpRequest));
        return suspiciousIpMapper.mapFromIp(savedItem);
    }


    public SuspiciousIpDeleteResponse deleteSuspiciousIp(String ip) {
        var suspiciousIp = suspiciousIpRepository.findByIp(ip);
        if (suspiciousIp.isEmpty()) {
            throw new SuspiciousIpNotFoundException("ip not found");
        }
        suspiciousIpRepository.delete(suspiciousIp.get());
        return suspiciousIpMapper.mapFromIpToDeleteResponse(suspiciousIp.get());
    }

    public List<SuspiciousIpResponse> getSuspiciousIps() {

        return suspiciousIpRepository.findAll().stream().map(suspiciousIpMapper::mapFromIp).toList();
    }
}
