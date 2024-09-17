package antifraud.card.mapper;

import antifraud.card.dto.RegisterStolenCardRequest;
import antifraud.card.dto.StolenCardResponse;
import antifraud.card.dto.StolenCardDeleteResponse;
import antifraud.card.entity.StolenCard;
import org.springframework.stereotype.Service;

@Service
public class StolenCardMapper {

    public StolenCard mapTo(RegisterStolenCardRequest request) {
        return StolenCard.builder()
                .number(request.getNumber())
                .build();
    }

    public StolenCardResponse mapToStolenCardResponse(StolenCard stolenCard) {
        return StolenCardResponse.builder()
                .id(stolenCard.getId())
                .number(stolenCard.getNumber())
                .build();
    }


    public StolenCardDeleteResponse mapFromStolenCardToDeleteResponse(StolenCard card) {
        return StolenCardDeleteResponse.builder()
                .status("Card"
                        + " "
                        + card.getNumber()
                        + " "
                        + "successfully removed!"
                )
                .build();
    }
}
