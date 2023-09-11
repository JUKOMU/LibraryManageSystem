package utils;

/**
 * 返回结果类
 */
public class Result {

    //定义错误的状态码  :规定： 200是正常，-1 是非法
    private Integer code=200;
    //定义一个错误的消息
    private String msg="";
    //定义一个Object类型的变量，用来封装数据
    private Object data;

    public Result() {}

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    //定义一个显示错误的静态常量
    public static final Result ERRORMSG=new Result(-1,"出错了");

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
