package Data;
import java.util.ArrayList;

public class VoterObject implements Comparable<VoterObject>{
	private int voter_id;
	private String voter_reg_num;
	private String name_prefix;
	private String first_name;
	private String middle_name;
	private String last_name;
	private String name_suffix;
	private int age;
	private String gender;
	private String race;
	private String ethnic;
	private String street_address;
	private String city;
	private String state;
	private int zip_code;
	private String full_phone_num;
	
	private String birth_place;
	private String register_date;
	private String download_month;
	
	
	public VoterObject() {
		super();
	}
	
	public VoterObject(int voter_id, String middle_name) {
		super();
		this.voter_id = voter_id;
		this.middle_name = middle_name;
	}
	public static ArrayList<String> getAttributeName() {
		ArrayList<String> res=new ArrayList<String>();
		res.add("voter_reg_num");
		res.add("name_prefix");
		res.add("first_name");
		res.add("middle_name");
		res.add("last_name");
		res.add("name_suffix");
		res.add("gender");
		res.add("race");
		res.add("ethnic");
		res.add("street_address");
		res.add("city");
		res.add("state");
		res.add("zip_code");
		res.add("full_phone_num");
		res.add("birth_place");
		res.add("register_date");
		res.add("download_month");
		return res;
		
	}
	
	//类比较器
	@Override
	public int compareTo(VoterObject o) {
		// TODO Auto-generated method stub
		return o.voter_id-this.voter_id;
	}
	
	public String getFull_phone_num() {
		return full_phone_num;
	}


	
	public void setFull_phone_num(String full_phone_num) {
		this.full_phone_num = full_phone_num;
	}

	
	public int getVoter_id() {
		return voter_id;
	}

	public void setVoter_id(int voter_id) {
		this.voter_id = voter_id;
	}

	public String getVoter_reg_num() {
		return voter_reg_num;
	}

	public void setVoter_reg_num(String voter_reg_num) {
		this.voter_reg_num = voter_reg_num;
	}

	public String getName_prefix() {
		return name_prefix;
	}

	public void setName_prefix(String name_prefix) {
		this.name_prefix = name_prefix;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getMiddle_name() {
		return middle_name;
	}

	public void setMiddle_name(String middle_name) {
		this.middle_name = middle_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getName_suffix() {
		return name_suffix;
	}

	public void setName_suffix(String name_suffix) {
		this.name_suffix = name_suffix;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public String getEthnic() {
		return ethnic;
	}

	public void setEthnic(String ethnic) {
		this.ethnic = ethnic;
	}

	public String getStreet_address() {
		return street_address;
	}

	public void setStreet_address(String street_address) {
		this.street_address = street_address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZip_code() {
		return zip_code;
	}

	public void setZip_code(int zip_code) {
		this.zip_code = zip_code;
	}

	public String getBirth_place() {
		return birth_place;
	}

	public void setBirth_place(String birth_place) {
		this.birth_place = birth_place;
	}

	public String getRegister_date() {
		return register_date;
	}

	public void setRegister_date(String register_date) {
		this.register_date = register_date;
	}

	public String getDownload_month() {
		return download_month;
	}

	public void setDownload_month(String download_month) {
		this.download_month = download_month;
	}
	
	
	public String getByAttributeNameReturnString(String s) {
		switch(s) {
		case "voter_reg_num": return voter_reg_num;
		case "name_prefix":return name_prefix;
		case "first_name":return first_name;
		case "last_name": return last_name;
		case "middle_name":return middle_name;
		default:return first_name;
		}
	}
	public int getByAttributeNameReturnInt(String s) {
		switch(s) {
		case "voter_id": return voter_id;
		case "zip_code": return zip_code;
		default: return age;
		}
	}
	public String getString(String str) {
		if(str.equals("voter_reg_num")) {
			return this.getVoter_reg_num();
		}
		else if(str.equals("name_prefix"))
			return this.getName_prefix();
		else if(str.equals("first_name"))
			return this.getFirst_name();
		else if(str.equals("middle_name"))
			return this.getMiddle_name();
		else if(str.equals("last_name"))
			return this.getLast_name();
		else if(str.equals("name_suffix"))
			return this.getName_suffix();
		else if(str.equals("gender"))
			return this.getGender();
		else if(str.equals("race"))
			return this.getRace();
		else if(str.equals("ethnic"))
			return this.getEthnic();
		else if(str.equals("street_address"))
			return this.getStreet_address();
		else if(str.equals("city"))
			return this.getCity();
		else if(str.equals("state"))
			return this.getState();
		else if(str.equals("full_phone_num"))
			return this.getFull_phone_num();
		else
			return null;
	}
	public int getInt (String str) {
		if(str.equals("voter_id")) {
			return this.getVoter_id();
		}
		else if(str.equals("age"))
			return this.getAge();
		else if(str.equals("zip_code"))
			return this.getZip_code();
		else 
			return -12345678;
	}
	
	
	
	
	public void printData() {
		System.out.println(
						getVoter_id()
+"/"+					getVoter_reg_num()
+"/"+					getName_prefix()
+"/"+					getFirst_name()
+"/"+					getMiddle_name()
+"/"+					getLast_name()
+"/"+					getName_suffix()
+"/"+					getAge()
+"/"+					getGender()
+"/"+					getRace()
+"/"+					getEthnic()
+"/"+					getStreet_address()
+"/"+					getCity()
+"/"+					getState()
+"/"+					getZip_code()
+"/"+					getFull_phone_num()
+"/"+					getBirth_place()
+"/"+					getRegister_date()
+"/"+					getDownload_month()	);	
	}
	
}
