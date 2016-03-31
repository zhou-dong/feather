package org.feather.common.util;

import java.io.StringWriter;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

import org.apache.commons.io.IOUtils;

public class StringUtil {

	public static boolean isEmpty(String str) {
		return (str == null || str.length() == 0);
	}

	public static String getLocalIP() {
		Enumeration<NetworkInterface> netInterfaces = null;
		String ip = "127.0.0.1";
		try {
			netInterfaces = NetworkInterface.getNetworkInterfaces();
			while (netInterfaces.hasMoreElements()) {
				NetworkInterface ni = netInterfaces.nextElement();
				String name = ni.getName().toLowerCase();
				if (name.startsWith("lo") || name.startsWith("vir") || name.startsWith("vmnet")
						|| name.startsWith("wlan")) {
					continue;
				}
				Enumeration<InetAddress> ips = ni.getInetAddresses();
				while (ips.hasMoreElements()) {
					InetAddress ia = ips.nextElement();
					if (ia instanceof Inet4Address) {
						if (ia.getHostAddress().toString().startsWith("127")) {
							continue;
						} else {
							return ia.getHostAddress();
						}
					}
				}
			}
		} catch (Exception e) {
		}
		return ip;
	}

	public static String trim(String text) {
		if (StringUtil.isEmpty(text)) {
			return "";
		}
		final StringBuilder buffer = new StringBuilder(text.length());
		for (final char ch : text.toCharArray()) {
			if (ch != (char) 160 && ch != '\t' && ch != '\n' && ch != '\r' && ch != ' ') {
				buffer.append(ch);
			}
		}
		return buffer.toString();
	}

	public static String getLocalName() {
		try {
			Runtime run = Runtime.getRuntime();
			Process proc = run.exec("hostname");
			StringWriter writer = new StringWriter();
			IOUtils.copy(proc.getInputStream(), writer, "utf-8");
			String name = StringUtil.trim(writer.toString());
			return name;
		} catch (Exception e) {
			return "unknow";
		}
	}

}
