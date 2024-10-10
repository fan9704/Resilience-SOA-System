package lab.cloud.book.utils;

import org.springframework.util.Assert;

public class UserContextHolder {
	private static final ThreadLocal<UserContext> userContextTl = new ThreadLocal<>();

	public static final UserContext getContext() {
		UserContext context = userContextTl.get();
		if (context == null) {
			context = createEmptyContext();
			userContextTl.set(context);
		}
		return userContextTl.get();
	}

	public static final void setContext(UserContext context) {
		Assert.notNull(context, "Must non-null UserContext object");
		userContextTl.set(context);
	}

	public static final UserContext createEmptyContext() {
		return new UserContext();
	}
}
