package com.addr;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="result")
@XmlType(name="response", namespace="com.addr.Result", propOrder={"flag", "message", "recordset"})
public class Result<T> {
	private String flag;
	private String message;
	
	List<T> resultSet;

	public Result(String flag, String message, List<T> resultSet) {
		super();
		this.flag = flag;
		this.message = message;
		this.resultSet = resultSet;
	}

	@Override
	public String toString() {
		return "Result [flag=" + flag + ", message=" + message + ", resultSet=" + resultSet + "]";
	}

	public String getFlag() {
		return flag;
	}

	@XmlElement(name="flag")
	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getMessage() {
		return message;
	}

	@XmlElement(name="message")
	public void setMessage(String message) {
		this.message = message;
	}

	
    @XmlElementWrapper(name="dataset")
    @XmlElementRefs({
        @XmlElementRef(name="data", type=Address.class)
    })
	public List<T> getResultSet() {
		return resultSet;
	}

	public void setResultSet(List<T> resultSet) {
		this.resultSet = resultSet;
	}
}
