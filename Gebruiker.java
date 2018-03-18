
public class Gebruiker {
	private String password;
	private String gebruikersnaam;

	public Gebruiker(String gebruikersnaam, String password) {

		this.password = password;
		this.gebruikersnaam = gebruikersnaam;
	}

	public String getGebruikersnaam() {
		return gebruikersnaam;
	}

	public String getPassword() {
		return password;
	}

	public String toString() {
		return gebruikersnaam + "," + password;

	}

}
