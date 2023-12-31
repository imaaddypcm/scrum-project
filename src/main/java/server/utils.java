package server;

import java.util.HashMap;

/**
 * Utility class providing methods for handling server-side operations.
 * @author Arie Geiger, Jose Cortes, Kyle Cushing, Erik Zeladita
 * @version Nov 21, 2023
 */
public class utils {
	/**
	 * Get hash map of arguments from GET query
	 * @param source GET query string
	 * @return Hash map of arguments
	 */
	public static HashMap<String, String> convertQueryStringToHashMap(String source) {
		HashMap<String, String> data = new HashMap<String, String>();

		if (source == null)
			return data;

		final String[] arrParameters = source.split("&");
		for (final String tempParameterString : arrParameters) {

			final String[] arrTempParameter = tempParameterString
					.split("=");

			if (arrTempParameter.length >= 2) {
				final String parameterKey = arrTempParameter[0];
				final String parameterValue = arrTempParameter[1];
				data.put(parameterKey, parameterValue);
			} else {
				final String parameterKey = arrTempParameter[0];
				data.put(parameterKey, "");
			}
		}

		return data;
	}
}
