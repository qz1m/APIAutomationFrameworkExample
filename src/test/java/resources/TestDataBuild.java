package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlaceSerialize;
import pojo.LocationSerialize;

public class TestDataBuild {

	public AddPlaceSerialize addPlacePayload(String name, String language, String address)
	{
		AddPlaceSerialize p = new AddPlaceSerialize();
		p.setAccuracy(50);
		p.setAddress(address);
		p.setName(name);
		p.setLanguage(language);
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("http://google.com");
		
		List<String>list = new ArrayList<String>();
		list.add("shoe park");
		list.add("shop");
		p.setTypes(list);
		
		LocationSerialize l = new LocationSerialize();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);
		return p;
	}
	
	public String deletePlacePayload(String placeId)
	{
		return "{\n"
				+ "    \"place_id\":\""+placeId+"\"\n"
				+ "}";
	}
}
