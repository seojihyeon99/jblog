package jblog.dto;

public class JsonResult {
	private String result;	// "success" of "fail"
	private Object data;	// if success, set
	private String message;	// if fail, set
	
	public static JsonResult success(Object data) {
		return new JsonResult(data);
	}
	
	public static JsonResult success() {
		return new JsonResult();
	}

	public static JsonResult fail(String message) {
		return new JsonResult(message);
	}

	private JsonResult() {
		this.result = "success";
		this.data = null;
		this.message = null;
	}
	
	private JsonResult(Object data) {
		this.result = "success";
		this.data = data;
		this.message = null;
	}

	private JsonResult(String message) {
		this.result = "fail";
		this.data = null;
		this.message = message;
	}	
	
	public String getResult() {
		return result;
	}
	public Object getData() {
		return data;
	}
	public String getMessage() {
		return message;
	}	
	
}
