package deepthinking.fgi.common;

public class ResultDto<T> {

	private String msg;
	private String LogDate;
	private Integer status;
	private T data;
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public String getLogDate() {
		return LogDate;
	}
	public void setLogDate(String logDate) {
		LogDate = logDate;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
}
