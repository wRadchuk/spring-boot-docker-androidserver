package com.wradchuk.springbootdockerandroidserver.core;

public class ResponseMessage {

    private Commands command;
    private Object data;
    private String index;


    public ResponseMessage() {
        this.command = Commands.UNKNOWN;
        this.data = "";
        this.index = "";
    }
    public ResponseMessage(Commands _command, Object _data, String _index) {
        this.command = _command;
        this.data = _data;
        this.index = _index;
    }

    public Commands getCommand() {
        return command;
    }
    public void setCommand(Commands _command) {
        this.command = _command;
    }

    public Object getData() {
        return data;
    }
    public void setData(Object _data) {
        this.data = _data;
    }

    public String getIndex() {
        return index;
    }
    public void setIndex(String _index) {
        this.index = _index;
    }
}
