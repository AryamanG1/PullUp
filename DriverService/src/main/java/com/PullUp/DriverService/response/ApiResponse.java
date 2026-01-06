package com.PullUp.DriverService.response;

import com.PullUp.DriverService.response.ApiResponse;

public class ApiResponse<T> {
		
	private String message;
	private String status;
	private T data;
	
	
	public ApiResponse(String message, String status, T data) {
		this.message = message;
		this.status = status;
		this.data = data;
	}
	
	public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(message, "success", data);
    }

    public static <T> ApiResponse<T> failure(String message) {
        return new ApiResponse<>(message, "failure", null);
    }
    
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
}

