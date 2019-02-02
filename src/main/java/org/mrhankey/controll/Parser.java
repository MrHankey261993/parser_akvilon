package org.mrhankey.controll;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Connection.Response;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.mrhankey.model.Spares;

public class Parser {
	private Logger log = Logger.getLogger(Parser.class);
	static String URL = "https://akvilonavto.by";
	private List<Spares> listSpares = new ArrayList<>();

	public List<Spares> parser(List<String> links){

		long start = System.currentTimeMillis();
		links.parallelStream().forEach(s -> {

			// Запрос для обработки статуса сервера
			Response response;
			try {
				response = Jsoup.connect(URL + s).followRedirects(false).execute();
				if (response.statusCode() == 502 || response.statusCode() == 503) {
					Thread.sleep(10000);
					log.warn("Статус код = " + response.statusCode() + s);
				}
			} catch (IOException e) {
				log.error(e.getMessage());
			} catch (InterruptedException e) {
				log.error(e.getMessage());
				
			}
			Spares spares = new Spares();
			Document doc;
			try {
				doc = Jsoup.connect(URL + s).timeout(30000 * 10).userAgent("Chrome/70").followRedirects(true).get();
				Element manufacturer = doc.getElementById("propBRAND");
				if (manufacturer == null) {
					spares.setProduction("Не указано");
				}
				Element articul = doc.getElementById("propCML2_ARTICLE");
				Element weight = doc.getElementById("propVES_CHISLITEL");
				Elements prices = doc.select(".c-prices__value-old");
				Elements ul = doc.select("ul.rsbreadcrumb > li");
				Element applicability;
				Element subgroup;
				if (ul.size() == 15) {
					applicability = doc.getElementById("bx_breadcrumb_6");
					subgroup = doc.getElementById("bx_breadcrumb_4");
				} else {
					applicability = doc.getElementById("bx_breadcrumb_5");
					subgroup = doc.getElementById("bx_breadcrumb_4");
				}
				if (manufacturer == null) {
					spares.setProduction("Не указан");
				} else {
					spares.setProduction(manufacturer.text());
				}
				if (articul == null) {
					spares.setArticul("Не указан");
				} else {
					spares.setArticul(articul.text());
				}
				spares.setName(doc.title());
				spares.setPrice(prices.get(0).text());
				spares.setWeight(weight.text());
				spares.setApplicability(applicability.text());
				spares.setSubgroup(subgroup.text());
			} catch (IOException e) {
				log.error(e.getMessage());
			}

			listSpares.add(spares);
			System.out.println(spares + Thread.currentThread().getName());
			System.out.println(listSpares.size());

		});
		
		try (FileOutputStream fos = new FileOutputStream("text.txt");
		ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(listSpares);
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		long finsh = System.currentTimeMillis();
		long itog = start - finsh;
		System.out.println(itog);
		return listSpares;

	}

	public List<Spares> getListSpares() {
		return listSpares;
	}

	public void setListSpares(List<Spares> listSpares) {
		this.listSpares = listSpares;
	}

}
