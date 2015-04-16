package web;
/**
 * 存放ip信息的实体类至于构造函数的set方法的返回就不要吐槽了...
 * @author Seven
 */
public class JsonEntity {
	private String ip;
	private String as;
	private String city;
	private String region;
	private String country;
	private String loc;
	private String org;
	private String json;
	public String getIp() {
		return ip;
	}
	public JsonEntity setIp(String ip) {
		this.ip = ip;
		return this;
	}
	public String getAs() {
		return as;
	}
	public JsonEntity setAs(String as) {
		this.as = as;
		return this;
	}
	public String getCity() {
		return city;
	}
	public JsonEntity setCity(String city) {
		this.city = city;
		return this;
	}
	public String getRegion() {
		return region;
	}
	public JsonEntity setRegion(String region) {
		this.region = region;
		return this;
	}
	public String getCountry() {
		return country;
	}
	public JsonEntity setCountry(String country) {
		this.country = country;
		return this;
	}
	public String getLoc() {
		return loc;
	}
	public JsonEntity setLoc(String loc) {
		this.loc = loc;
		return this;
	}
	public String getOrg() {
		return org;
	}
	public JsonEntity setOrg(String org) {
		this.org = org;
		return this;
	}
	public String getJson() {
		return json;
	}
	public JsonEntity setJson(String json) {
		this.json = json;
		return this;
	}
	@Override
	public String toString() {
		return "ip=" + ip + ", \nas=" + as + ", \ncity=" + city
				+ ", \nregion=" + region + ", \ncountry=" + country + ", \nloc="
				+ loc + ", \norg=" + org + ", \n" + json;
	}
	
	
}
