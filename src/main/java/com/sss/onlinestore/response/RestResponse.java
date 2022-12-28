package com.sss.onlinestore.response;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RestResponse {
	private boolean success;
	private List<String> errorMessages;
	
	public boolean getSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public List<String> getErrorMessages() {
		return errorMessages;
	}
	
	public void addError(String message) {
		if(!Optional.ofNullable(errorMessages).isPresent()) {
			errorMessages = new ArrayList<String>();
		}
		errorMessages.add(message);
	}
}
