package com.wradchuk.springbootdockerandroidserver.core;

public class RequestMessage {

    private Commands command;
    private Object message;
    private String index;


    public RequestMessage() {
        this.command = Commands.UNKNOWN;
        this.message = "";
        this.index = "";
    }
    public RequestMessage(Commands _command, Object _message, String _index) {
        this.command = _command;
        this.message = _message;
        this.index = _index;
    }

    public Commands getCommand() {
        return command;
    }
    public void setCommand(Commands _command) {
        this.command = _command;
    }

    public Object getMessage() {
        return message;
    }
    public void setMessage(Object _message) {
        this.message = _message;
    }

    public String getIndex() {
        return index;
    }
    public void setIndex(String _index) {
        this.index = _index;
    }
}
