public class Client {

	private int idClient;
	private string nomClient;
	private string prenomClient;
	private string adresseClient;
	private int telClient;

	public int getIdClient() {
		return this.idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public string getNomClient() {
		return this.nomClient;
	}

	public void setNomClient(string nomClient) {
		this.nomClient = nomClient;
	}

	public string getPrenomClient() {
		return this.prenomClient;
	}

	public void setPrenomClient(string prenomClient) {
		this.prenomClient = prenomClient;
	}

	public string getAdresseClient() {
		return this.adresseClient;
	}

	public void setAdresseClient(string adresseClient) {
		this.adresseClient = adresseClient;
	}

	public int getTelClient() {
		return this.telClient;
	}

	public void setTelClient(int telClient) {
		this.telClient = telClient;
	}

	/**
	 * 
	 * @param id
	 * @param nom
	 * @param prenom
	 * @param adresse
	 * @param tel
	 * @param sexe
	 */
	public Client(int id, string nom, string prenom, string adresse, int tel, Sexe sexe) {
		// TODO - implement Client.Client
		throw new UnsupportedOperationException();
	}

	public string toString() {
		// TODO - implement Client.toString
		throw new UnsupportedOperationException();
	}

}