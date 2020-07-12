package com.truefan.taskmgmt.dto;

public class ResponseDto {
    private String operation;
    private String output;
    private Object data;

    public enum OperationType {
        REGISTER_USER("REGISTER_USER"),
        CREATE_TASK("CREATE_TASK"),
        ASSIGN_TASK("ASSIGN_TASK"),
        MY_TASKS("MY_TASKS");

        private String type;

        OperationType(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
