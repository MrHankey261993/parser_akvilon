package org.mrhankey.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class UtilParser {
	private List<String> linksProducts;
	private final static String URL = "https://akvilonavto.by";
	private static final String START_PAGE = "https://akvilonavto.by/catalog/zapasnye_chasti/zapchasti_mtz/";
	static final String TAGLI_CLASS_NAME = "section col-xs-6 col-md-6 col-md-4 col-lg-5rs";
	private Logger log = Logger.getLogger(UtilParser.class);

//Метод собирает сылки на модели
	public List<String> linksOnModel() {

		List<String> links = new ArrayList<String>();
		Document doc = null;
		try {
			doc = Jsoup.connect(START_PAGE).get();
			Elements tagLi = doc.getElementsByClass(TAGLI_CLASS_NAME);
			for (Element element : tagLi) {
				String href = element.select("a.parent").attr("href");
				links.add(href);
			}
		} catch (IOException e) {
			log.error(e.getMessage());
		}

		return links;

	}

//Метод собирает ссылки на группы(Мотор, сцепление и т.д.) всех моделей
	public List<String> linksOnGruop() {
		List<String> links = new ArrayList<String>();
		linksOnModel().parallelStream().forEach(s -> {

			try {
				Document doc = Jsoup.connect(URL + s).get();
				Elements tagLi = doc.getElementsByClass(TAGLI_CLASS_NAME);
				for (Element element : tagLi) {
					String href = element.select("a.parent").attr("href");
					links.add(href);
				}
			} catch (IOException e) {
				log.error(e.getMessage());
			}

		});

		return links;
	}

//метод собирает сылки на все продукты
	public List<String> linksProduct() {
		linksProducts = new ArrayList<>();
		long start = System.currentTimeMillis();
		linksOnGruop().parallelStream().forEach(s -> {
			Response response;
			try {
				response = Jsoup.connect(URL + s).execute();
				if (response.statusCode() == 503 || response.statusCode() == 502) {
					Thread.sleep(10000);
				}
			} catch (IOException e) {
				log.error(e.getMessage());
			} catch (InterruptedException e) {
				log.error(e.getMessage());
			}
			Document doc;
			try {
				doc = Jsoup.connect(URL + s).timeout(30000 * 10).userAgent("Chrome/70").followRedirects(true).get();
				Elements tagA = doc.select("div.list-showcase__name > a");
				// Проверка есть ли сылки на странице
				if (tagA.isEmpty()) {
					Elements tagLi = doc.getElementsByClass(TAGLI_CLASS_NAME);
					for (Element element : tagLi) {
						String href = element.select("a.parent").attr("href");
						doc = Jsoup.connect(URL + href).timeout(30000 * 10).userAgent("Chrome/70").followRedirects(true)
								.get();
						Elements arrowRight;
						do {
							tagA = doc.select("div.list-showcase__name > a");
							for (Element a : tagA) {
								href = a.attr("href");
								linksProducts.add(href);
								System.out.println(href);
							}
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								log.error(e.getMessage());
							}
							arrowRight = doc.select("a.right");
							// Проверяем есть ли ещё товар в этой группе
							if (!arrowRight.isEmpty()) {
								href = arrowRight.attr("href");
								try {
									response = Jsoup.connect(URL + href).execute();
									if (response.statusCode() == 503 || response.statusCode() == 502) {
										Thread.sleep(30000);
										log.warn("Статус код = " + response.statusCode() + s);
									}
								} catch (IOException e) {
									log.error(e.getMessage());
								} catch (InterruptedException e) {
									log.error(e.getMessage());
								}
								doc = Jsoup.connect(URL + href).timeout(30000 * 10).userAgent("Chrome/70")
										.followRedirects(true).ignoreHttpErrors(true).ignoreContentType(true).get();
							}
						} while (!arrowRight.isEmpty());
					}
				} else {
					Elements arrowRight;
					do {
						tagA = doc.select("div.list-showcase__name > a");
						for (Element a : tagA) {
							String href = a.attr("href");
							linksProducts.add(href);
							System.out.println(a.text() + Thread.currentThread().getName());
						}
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							log.error(e.getMessage());
						}
						arrowRight = doc.select("a.right");
						// Проверяем есть ли ещё товар в этой группе
						if (!arrowRight.isEmpty()) {
							String href = arrowRight.attr("href");
							try {
								response = Jsoup.connect(URL + href).execute();

								if (response.statusCode() == 503 || response.statusCode() == 502) {
									Thread.sleep(30000);
									log.warn("Статус код = " + response.statusCode() + s);
								}
								doc = Jsoup.connect(URL + href).timeout(30000 * 10).userAgent("Chrome/70")
										.followRedirects(true).ignoreHttpErrors(true).ignoreContentType(true).get();

								System.out.println(response.statusCode() + " " + href);
							} catch (InterruptedException e) {
								log.error(e.getMessage());
							}

						}
					} while (!arrowRight.isEmpty());

				}
			} catch (IOException e) {
				log.error(e.getMessage());
			}

		});

		long finsh = System.currentTimeMillis();
		long itog = start - finsh;
		System.out.println(linksProducts.size() + " " + itog);
		try (FileOutputStream fos = new FileOutputStream("links.txt");
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(linksProducts);
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		System.out.println("Файл записан");
		return linksProducts;

	}

	public List<String> getLinksProducts() {
		return linksProducts;
	}

	public void setLinksProducts(List<String> linksProducts) {
		this.linksProducts = linksProducts;
	}
}
