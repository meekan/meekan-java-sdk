package com.meekan.api.utils;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author idog
 * 
 */
public class Utils {

	private static final ObjectMapper objectMapper;
	static {
		objectMapper = new ObjectMapper();
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
	}

	/**
	 * put value in map only if not null
	 * 
	 * @param params
	 * @param key
	 * @param value
	 */
	public static <T> void putIfNotNull(Map<String, Collection<String>> params, String key, T value) {
		if (value != null) {
			params.put(key, Collections.singleton(String.valueOf(value)));
		}
	}

	/**
	 * @return true if there is at least one item in the map, false otherwise
	 */
	public static boolean isNotEmpty(Map<?, ?> map) {
		return map != null && !map.isEmpty();
	}

	/**
	 * @return true if there is at least one item in the collection, false otherwise
	 */
	public static boolean isNotEmpty(Collection<?> collection) {
		return collection != null && !collection.isEmpty();
	}

	/**
	 * @return true if there the string length greater than 0, false otherwise
	 */
	public static boolean isNotEmpty(final CharSequence string) {
		return string != null && string.length() > 0;
	}

	public static ObjectMapper getJSONObjectMapper() {
		return objectMapper;
	}

	public static String writeValueAsString(Object object) {
		try {
			return getJSONObjectMapper().writeValueAsString(object);
		} catch (JsonProcessingException e) {
			return null;
		}
	}

	public static JsonNode parseStringToJsonNode(String content) throws JsonProcessingException, IOException {
		return getJSONObjectMapper().readTree(content);
	}
}
