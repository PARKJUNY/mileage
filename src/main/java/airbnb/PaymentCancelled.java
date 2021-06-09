package airbnb;

public class PaymentCancelled extends AbstractEvent {
    private Long payId;
    private Long rsvId;
    private Long roomId;
    private Long mileageId;
    private String status;

    public PaymentCancelled(){
        super();
    }
    
    public Long getPayId() {
        return payId;
    }

    public void setPayId(Long payId) {
        this.payId = payId;
    }
    public Long getRsvId() {
        return rsvId;
    }

    public void setRsvId(Long rsvId) {
        this.rsvId = rsvId;
    }
    public Long getRoomId() {
        return roomId;
    }

    public void setMileageId(Long mileageId) {
        this.mileageId = mileageId;
    }
    public Long getMileageId() {
        return mileageId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
