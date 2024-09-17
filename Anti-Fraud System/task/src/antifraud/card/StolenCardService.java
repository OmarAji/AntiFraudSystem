package antifraud.card;


import antifraud.card.dto.RegisterStolenCardRequest;
import antifraud.card.dto.StolenCardDeleteResponse;
import antifraud.card.dto.StolenCardResponse;
import antifraud.card.exception.StolenCardAlreadyExistException;
import antifraud.card.mapper.StolenCardMapper;
import antifraud.ip.exception.SuspiciousIpNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StolenCardService {

    private final StolenCardRepository stolenCardRepository;
    private final StolenCardMapper mapper;

    public StolenCardResponse registerStolenCard(@Valid RegisterStolenCardRequest request) {
        var card = stolenCardRepository.findByNumber(request.getNumber());
        if (card.isPresent()) {
            throw new StolenCardAlreadyExistException("card already registered");
        }
        var savedItem = stolenCardRepository.save(mapper.mapTo(request));
        return mapper.mapToStolenCardResponse(savedItem);
    }

    public StolenCardDeleteResponse deleteStolenCard(String number) {
        var card = stolenCardRepository.findByNumber(number);
        if (card.isEmpty()) {
            throw new SuspiciousIpNotFoundException("ip not found");
        }
        stolenCardRepository.delete(card.get());
        return mapper.mapFromStolenCardToDeleteResponse(card.get());
    }

    public List<StolenCardResponse> getStolenCards() {
        return stolenCardRepository.findAll().stream().map(mapper::mapToStolenCardResponse).toList();
    }
}
