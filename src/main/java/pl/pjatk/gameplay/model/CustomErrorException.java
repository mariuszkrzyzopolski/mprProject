package pl.pjatk.gameplay.model;

public class CustomErrorException extends RuntimeException{

    private String customMessage;

    public String getCustomMessage() {
        return customMessage;
    }

    public void setCustomMessage(String customMessage) {
        this.customMessage = customMessage;
    }


    public CustomErrorException() {
        super();
    }
    public CustomErrorException(String s) {
        super();
        this.customMessage = s;
    }



}
