package com.addr;

import java.io.StringReader;
import java.net.URI;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilderFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import ch.qos.logback.classic.Logger;

public class RestTemplateServiceImpl implements ParseService{

	private final Logger log = (Logger) org.slf4j.LoggerFactory.getLogger(RestTemplateServiceImpl.class);	
	
	public String restTemplate(Map<String, Object> map) throws Exception{
		String baseUrl = "openapi.epost.go.kr";
		String url = "";
		String responseStr = "";
		String svcKey      = "서비스키값";
		
		RestTemplate restTpl = new RestTemplate();
		URI uri = UriComponentsBuilder.newInstance()
				.scheme("http")
				.host(baseUrl)
				.path("/postal/retrieveNewAdressAreaCdService/retrieveNewAdressAreaCdService/getNewAddressListAreaCd")
				.queryParam("ServiceKey"   , URLDecoder.decode(svcKey, "UTF-8"))
				.queryParam("searchSe"     , (String) map.get("searchSe"     ))
				.queryParam("srchwrd"      , (String) map.get("srchwrd"      ))
				.queryParam("countPerPage" , (String) map.get("countPerPage" ))
				.queryParam("currentPage"  , (String) map.get("currentPage"  ))
				.build()
				.encode()
				.toUri();

		
		
		responseStr = restTpl.getForObject(uri, String.class);
		
		// [getForEntity]
		// ResponseEntity<String> response = restTpl.getForEntity(uri, String.class);
		// 응답코드 확인
		// System.out.println(response.getStatusCode().is2xxSuccessful());
		// responseStr = response.getBody();
		log.info(responseStr);
		
		return responseStr;	
	}
	
	@Override
	public List<Address> getAddrList(Map<String, Object> map) throws Exception {
		return parseXmlStr(this.restTemplate(map));
	}

	private List<Address> parseXmlStr(String xmlStr){
		List<Address> addrList = new ArrayList<Address>();
		StringReader sr = new StringReader(xmlStr);
		InputSource is = new InputSource(sr);
		NodeList result = null;
		try {
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
			Node node = doc.getDocumentElement();
			//Node node = doc.getElementsByTagName("NewAddressListResponse");
			result = node.getChildNodes();
			for (int i = 0; i < result.getLength(); i++) {
				Node tmpNode = result.item(i);
				if (!(tmpNode.getNodeName().equals("cmmMsgHeader"))) {
					NodeList addrNodeList = tmpNode.getChildNodes();
					Address addr = new Address();
					
					Map<String, String> addrMap = new HashMap<>();
					for (int j = 0; j < addrNodeList.getLength(); j++) {
						addrMap.put(addrNodeList.item(j).getNodeName(), addrNodeList.item(j).getTextContent());
					}
					
					addr.setLnmAdres ( addrMap.get("lnmAdres"));
					addr.setZipNo    ( addrMap.get("zipNo")   );
					addr.setRnAdres  ( addrMap.get("rnAdres") );
					addrList.add(addr);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return addrList;
	}	
	
	
	
	
}
