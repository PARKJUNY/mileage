package airbnb;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;

@Entity
@Table(name="Mileage_table")
public class Mileage {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long mileageId;
    private Long roomId;
    private Long payId;
    private Integer mileagePoint;
    private String status;

    @PostPersist
    public void onPostPersist(){
        ////////////////////////////
        // 결제 승인 된 경우 - 마일리지 증가
        ////////////////////////////

        // 이벤트 발행 -> MileageIncreased        
        MileageIncreased mileageIncreased = new MileageIncreased();
        
        BeanUtils.copyProperties(this, mileageIncreased);
        mileageIncreased.publishAfterCommit();
    }

    @PostUpdate
    public void onPostUpdate(){

        //////////////////////
        // 결제 취소 된 경우 - 마일리지 감소
        //////////////////////

        // 이벤트 발행 -> MileageDecreased        
        MileageDecreased mileageDecreased = new MileageDecreased();
        BeanUtils.copyProperties(this, mileageDecreased);
        mileageDecreased.publishAfterCommit();
    }

    public Long getMileageId() {
        return mileageId;
    }

    public void setMileageId(Long mileageId) {
        this.mileageId = mileageId;
    }
    
    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Long getPayId() {
        return payId;
    }

    public void setPayId(Long payId) {
        this.payId = payId;
    }
    
    public Integer getMileagePoint() {
        return mileagePoint;
    }

    public void setMileagePoint(Integer mileagePoint) {
        this.mileagePoint = mileagePoint;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
