package cn.edu.xmu.dorm.exception;



import lombok.Getter;
import cn.edu.xmu.dorm.utils.ReturnNo;

@Getter
public class DormException extends RuntimeException{
    private ReturnNo returnNo;

    public DormException(ReturnNo returnNo, String message) {
        super(message);
        this.returnNo = returnNo;
    }

    public DormException(ReturnNo returnNo) {
        super(returnNo.getMessage());
        this.returnNo = returnNo;
    }
}
