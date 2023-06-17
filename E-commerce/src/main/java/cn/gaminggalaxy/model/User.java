package cn.gaminggalaxy.model;

public class User {
	private int id;
	private String name;
	private String cognome;
	private String email;
	private String password;
	private String userType;
	private String address;
	private String city;
	private String provincia;
	private String zipCode;
	private String cartNumber;
	private String mese;
	private String cvv;
	
	public User() {
		super();
	}


	
	public User(String email, String password, String name, String cognome, String address, String city, String provincia, String zipCode, String cartNumber, String mese, String cvv) {
		this.email = email;
	    this.password = password;
		this.name=name;
		this.cognome = cognome;
	    this.address = address;
	    this.city = city;
	    this.provincia = provincia;
	    this.zipCode = zipCode;
	    this.cartNumber = cartNumber;
	    this.mese = mese;
	    this.cvv = cvv;
	}


	public User(int id, String name, String email, String password) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
	}
	
	public User(int id, String email ) {
		this.id=id;
		this.email=email;
	}
	
	public User(int id, String password,String unused) {
		this.id=id;
		this.password=password;
	}
	
	public User(int id, String address,String unused, int unused2) {
		this.id=id;
		this.address=address;
	}
	
	public User(int id, String city,String unused, int unused2, int unused3) {
		this.id=id;
		this.city=city;
	}
	
	public User(int id, String provincia,int unused, int unused2, int unused3) {
		this.id=id;
		this.provincia=provincia;
	}
	public User(int id, String zipCode,int unused, int unused2, int unused3,int unused4) {
		this.id=id;
		this.zipCode=zipCode;
	}
	public User(int id, String cartNumber,int unused, int unused2, int unused3,int unused4, int unused5) {
		this.id=id;
		this.cartNumber=cartNumber;
	}
	public User(int id, String mese,int unused, int unused2, int unused3,int unused4, int unused5,int unused6) {
		this.id=id;
		this.mese=mese;
	}
	public User(int id, String cvv,int unused, int unused2, int unused3,int unused4, int unused5,int unused6,int unused7) {
		this.id=id;
		this.cvv=cvv;
	}
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}
	
	
	public String getCognome() {
		return cognome;
	}


	public void setCognome(String cognome) {
		this.cognome = cognome;
	}




	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getProvincia() {
		return provincia;
	}


	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}


	public String getZipCode() {
		return zipCode;
	}


	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}


	public String getCartNumber() {
		return cartNumber;
	}


	public void setCartNumber(String cartNumber) {
		this.cartNumber = cartNumber;
	}
	
	



	public String getMese() {
		return mese;
	}



	public void setMese(String mese) {
		this.mese = mese;
	}



	public String getCvv() {
		return cvv;
	}



	public void setCvv(String cvv) {
		this.cvv = cvv;
	}



	public String getUserType() {
		return userType;
	}



	public void setUserType(String userType) {
		this.userType = userType;
	}

}
