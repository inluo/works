package works.common;

/**
 * 自定义业务异常
 */
public class ServiceException extends RuntimeException{
    public ServiceException(String message){
        super(message);
    }
}
