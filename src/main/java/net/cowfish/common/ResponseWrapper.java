package net.cowfish.common;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseWrapper<T> {
	private Integer code;
	private String status;
	private String msg;
	private T data;
	 
	
	public static <T> ResponseWrapper<T> createResponse(Integer code,String status,String msg,T data){
		ResponseWrapper<T> responseWrapper = new ResponseWrapper<T>();
		responseWrapper.setData(data);
		responseWrapper.setMsg(msg);
		responseWrapper.setCode(code);
		responseWrapper.setStatus(status);
		return responseWrapper;
		
	}
	public static  <T> ResponseWrapper<T> ok(String msg,T data){
		return createResponse(200,"success",msg,data);
	}
	public static  <T> ResponseWrapper<T> ok(){
		return createResponse(200,"success","",null);
	}
	public static  <T> ResponseWrapper<T> ok(String msg){
		return createResponse(200,"success",msg,null);
	}
	public static  <T> ResponseWrapper<T> ok(T data){
		return createResponse(200,"success","",data);
	}
	public static <T> ResponseWrapper<T> fail(String msg,T data){
		return createResponse(500,"fail",msg,data);
	}
	public static <T> ResponseWrapper<T> fail(String msg){
		return createResponse(500,"fail",msg,null);
	}
	public static <T> ResponseWrapper<T> fail(String msg,Integer code){
		return createResponse(code,"fail",msg,null);
	}

}
