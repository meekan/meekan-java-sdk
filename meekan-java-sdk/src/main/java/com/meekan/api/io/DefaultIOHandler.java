//package com.meekan.api.io;
//
///*
// * FoursquareAPI - Foursquare API for Java
// * Copyright (C) 2008 - 2011 Antti Leppä / Foyt
// * http://www.foyt.fi
// * 
// * License: 
// * 
// * Licensed under GNU Lesser General Public License Version 3 or later (the "LGPL")
// * http://www.gnu.org/licenses/lgpl.html
// */
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.StringWriter;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//
///**
// * Default implementation of the IOHandler
// * 
// * @author Antti Leppä
// * 
// */
//public class DefaultIOHandler extends IOHandler {
//
//	@Override
//	public Response fetchData(String url, Method method) {
//		int code = 200;
//
//		try {
//			URL aUrl = new URL(url);
//			HttpURLConnection connection = (HttpURLConnection) aUrl.openConnection();
//			try {
//				connection.setDoInput(true);
//				if (method != Method.GET) {
//					connection.setDoOutput(true);
//				}
//				connection.setRequestMethod(method.name());
//				connection.connect();
//
//				code = connection.getResponseCode();
//				if (code == 200) {
//					InputStream inputStream = connection.getInputStream();
//					return new Response(readStream(inputStream), code, connection.getResponseMessage());
//				} else {
//					return new Response("", code, getMessageByCode(code));
//				}
//
//			} finally {
//				connection.disconnect();
//			}
//		} catch (MalformedURLException e) {
//			return new Response("", 400, "Malformed URL: " + url);
//		} catch (IOException e) {
//			return new Response("", 500, e.getMessage());
//		}
//	}
//
//	/**
//	 * Reads input stream and returns it's contents as String
//	 * 
//	 * @param inputStream
//	 *            input stream to be readed
//	 * @return Stream's content
//	 * @throws IOException
//	 */
//	private String readStream(InputStream inputStream) throws IOException {
//		StringWriter responseWriter = new StringWriter();
//
//		char[] buf = new char[1024];
//		int l = 0;
//
//		InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
//		while ((l = inputStreamReader.read(buf)) > 0) {
//			responseWriter.write(buf, 0, l);
//		}
//
//		responseWriter.flush();
//		responseWriter.close();
//		return responseWriter.getBuffer().toString();
//	}
//
//	/**
//	 * Returns message for code
//	 * 
//	 * @param code
//	 *            code
//	 * @return Message
//	 */
//	private String getMessageByCode(int code) {
//		switch (code) {
//		case 400:
//			return "Bad Request";
//		case 401:
//			return "Unauthorized";
//		case 403:
//			return "Forbidden";
//		case 404:
//			return "Not Found";
//		case 405:
//			return "Method Not Allowed";
//		case 500:
//			return "Internal Server Error";
//		default:
//			return "Unknown";
//		}
//	}
//
//	private static String BOUNDARY = "----------gc0p4Jq0M2Yt08jU534c0p";
// }
