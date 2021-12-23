import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class Crypt {
	public String hash(String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA");
			
			md.update(password.getBytes());
			byte[] arr = md.digest();
			StringBuilder sb = new StringBuilder();
			for(byte x: arr) {
				sb.append(x);
			}
			
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "";
		
	}
}