package br.com.nascisoft.apoioterritoriols.cadastro.util;

public final class StringUtils {

	public static String toCamelCase(String string) {		
		if (string != null) {
			StringBuilder retorno = new StringBuilder();
			String[] array = string.split(" ");
			for (int i = 0; i < array.length; i++) {
				if (!StringUtils.isEmpty(array[i])) {
					String aposPrimeiraLetra = array[i].substring(1);
					retorno.append(array[i].substring(0, 1)).append(
							aposPrimeiraLetra.toLowerCase()).append(" ");
				}
			}
			return retorno.toString();
		} else {
			return null;
		}
	}
	
	public static boolean isEmpty(String string) {
		return string == null || "".equals(string);
	}
}
