package pa.platform.queue;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Messages {
	public final String BUNDLE_NAME = "pa.platform.queue.queueattributes"; //$NON-NLS-1$

	public final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
			.getBundle(BUNDLE_NAME);

	public Messages() {
	}

	public String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
