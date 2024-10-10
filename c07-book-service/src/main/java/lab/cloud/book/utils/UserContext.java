package lab.cloud.book.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserContext {
	
	public static final String CORRELATION_ID = "tmx-correlation-id";
	public static final String AUTH_TOKEN = "tmx-auth-token";
	public static final String USER_ID = "tmx-user-id";
	public static final String AUTHOR_ID = "tmx-author-id";

	private String correlationId = new String();
	private String authToken = new String();
	private String userId = new String();
	private String authorId = new String();

}
