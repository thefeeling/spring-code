package com.addr;

import java.util.List;
import java.util.Map;

public interface ParseService {
	List<Address> getAddrList(Map<String, Object> map) throws Exception;
}
