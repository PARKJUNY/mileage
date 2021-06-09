package airbnb;

import airbnb.config.kafka.KafkaProcessor;

import java.util.Optional;

//import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{

    @Autowired
    private MileageRepository mileageRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPaymentCancelled_DecreaseMileage(@Payload PaymentCancelled paymentCancelled){

        if(paymentCancelled.isMe()){

            /////////////////////////////////////////////
            // 결제 취소 요청이 왔을 때 -> status -> cancelled 
            /////////////////////////////////////////////
            System.out.println("\n\n##### listener DecreaseMileage : " + paymentCancelled.toJson() + "\n\n");
            
            // 취소시킬 mileage 추출
            long mileageId = paymentCancelled.getMileageId(); // 취소시킬 mileage ID

            Optional<Mileage> res = mileageRepository.findById(mileageId);
            Mileage mileage = res.get();

            mileage.setMileagePoint(mileage.getMileagePoint()); // 마일리지 감소양
            mileage.setStatus("Mileage Canceled"); // 마일리지 감소
            mileage.setMileagePoint(0);

            System.out.println("Edited mileageID    : " + mileage.getMileageId());
            System.out.println("Edited roomID       : " + mileage.getRoomId());
            System.out.println("Edited payID        : " + mileage.getPayId());
            System.out.println("Edited mileagePoint : " + mileage.getMileagePoint());
            System.out.println("Edited status       : " + mileage.getStatus());

            // DB Update
            mileageRepository.save(mileage);

        }
    }

}
