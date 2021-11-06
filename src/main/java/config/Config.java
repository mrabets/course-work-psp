package config;

public enum Config {
	url("jdbc:mysql://localhost:3306/db_utest?useUnicode=true&serverTimezone=UTC&useSSL=true&verifyServerCertificate=false"),
	username("mrabets"),
	password("12102001Km_"),
	driver("com.mysql.cj.jdbc.Driver");
	
	public final String label;

    private Config(String label) {
        this.label = label;
    }
}
