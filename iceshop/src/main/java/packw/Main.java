package packw;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Main {

    public static void main(String[] args) throws IOException {
//         String json = "{\"name\":\"Nobunaga\", \"email\":\"nobunaga@gmail.com\"}";
//
//         ObjectMapper mapper = new ObjectMapper();
//         Info info  = mapper.readValue(json, Info.class);
//
//         System.out.println(info.name);
    	ObjectMapper mapper = new ObjectMapper();   
    	String json = "{\"ices\":[{\"product_num\":0,\"icenum\":1,\"container\":2,\"quantity\":1,\"ice\":[[0,3],[null,null],[null,null]],\"price\":520,\"is_active\":true},{\"product_num\":1,\"icenum\":2,\"container\":1,\"quantity\":1,\"ice\":[[3,2],[8,1],[null,null]],\"price\":620,\"is_active\":true}]}";
    	//String json = "{\"Ices\":[{\"product_num\":0,\"icenum\":1,\"container\":2,\"quantity\":1,\"price\":520,\"is_active\":true},{\"product_num\":1,\"icenum\":2,\"container\":1,\"quantity\":1,\"price\":620,\"is_active\":true}]}";
    	//String json = "{\"product_num\":0,\"icenum\":1,\"container\":2,\"quantity\":1,\"price\":520,\"is_active\":true}";
    	//String json = "{\"product_num\":0,\"icenum\":1,\"container\":2,\"quantity\":1,\"ice\":[[0,3],[null,null],[null,null]],\"price\":520,\"is_active\":true}";
    	
    	JsonObject jo = mapper.readValue(json, JsonObject.class);
    	
    	List<Ices> icelist = new ArrayList<Ices>();
    	icelist = jo.getIces();
    	System.out.println(icelist.get(0).getContainer());
    }
}