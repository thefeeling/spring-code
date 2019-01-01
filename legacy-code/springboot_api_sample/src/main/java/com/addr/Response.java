package com.addr;

import java.util.List;
import java.util.Map;

public class Response<T> {
	public Result<T> getResponse(ParseService svc, Map<String, Object> map) throws Exception{
		Result<T> apiResult = new Result<T>("200", "ok", (List<T>) svc.getAddrList(map));
		return apiResult;
	}
}
