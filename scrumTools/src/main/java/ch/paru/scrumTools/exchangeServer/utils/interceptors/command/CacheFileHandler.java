package ch.paru.scrumTools.exchangeServer.utils.interceptors.command;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

import ch.paru.scrumTools.exchangeServer.services.calendar.CalendarCategories;
import ch.paru.scrumTools.exchangeServer.services.calendar.ServerAppointment;
import ch.paru.scrumTools.exchangeServer.services.calendar.ServerDay;
import ch.paru.scrumTools.exchangeServer.services.calendar.ServerTime;
import ch.paru.scrumTools.exchangeServer.services.contact.ServerContact;
import ch.paru.scrumTools.exchangeServer.services.contact.ServerContactGroup;
import ch.paru.scrumTools.exchangeServer.utils.exceptions.ServerException;

import com.google.common.collect.Maps;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class CacheFileHandler {

	private String path;
	private String cacheName;

	public CacheFileHandler(String path, String cacheName) {
		this.path = path;
		this.cacheName = cacheName;
	}

	private XStream getXStream() {
		XStream xStream = new XStream(new DomDriver());
		xStream.setMode(XStream.NO_REFERENCES);
		xStream.alias("ServerAppointment", ServerAppointment.class);
		xStream.alias("CalendarCategories", CalendarCategories.class);
		xStream.alias("ServerDay", ServerDay.class);
		xStream.alias("ServerTime", ServerTime.class);
		xStream.alias("ServerContact", ServerContact.class);
		xStream.alias("ServerContactGroup", ServerContactGroup.class);
		return xStream;
	}

	public void store(Map<String, Object> data) {
		if (!fileExists()) {
			return;
		}

		try {
			XStream xStream = getXStream();
			File file = new File(getFilePath());
			FileWriter writer = new FileWriter(file, false);
			ObjectOutputStream out = xStream.createObjectOutputStream(writer);
			out.writeObject(data);
			out.close();
		}
		catch (Exception e) {
			throw new ServerException("store cache failed", e);
		}
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> load() {
		if (!fileExists()) {
			return Maps.newHashMap();
		}

		try {
			XStream xStream = getXStream();
			File file = new File(getFilePath());
			FileReader reader = new FileReader(file);
			ObjectInputStream in = xStream.createObjectInputStream(reader);
			Map<String, Object> data = (Map<String, Object>) in.readObject();
			reader.close();
			return data;
		}
		catch (Exception e) {
			throw new ServerException("load cache failed", e);
		}
	}

	private String getFilePath() {
		return path + "cache-" + cacheName + ".xml";
	}

	private boolean fileExists() {
		File file = new File(getFilePath());
		return file.exists();
	}
}
