import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
// Scrapes data from www.tomford.com using Jsoup library
public class JsoupCrawler {

	public static void main(String args[]) throws IOException {

		Document d = Jsoup.connect("https://www.tomford.com/beauty/lips/").timeout(6000).get();
		Elements e = d.select("li.grid-tile"); // parent class for each item on the page

		List<LipstickModel> lipsticks = new ArrayList<>();

		for (Element element : e) {

			LipstickModel currentLipModel = new LipstickModel();
			// Individual Product url's
			String prodUrl = element.getElementsByClass("product-image").first().getElementsByTag("a").attr("href");
			Document temp = Jsoup.connect(prodUrl).timeout(6000).get();

			// get product name,product id, price, image url, description using itemprop
			// attribute
			currentLipModel.setProdName(getContent(element, "itemprop", "name", "content"));
			currentLipModel.setProdId(getContent(element, "itemprop", "productId", "content"));
			if (getContent(element, "itemprop", "price", "content").isEmpty()) { // if the price is empty get the range
																					// of prices(lowprice and highprice)
				currentLipModel.setProdPrice(getContent(element, "itemprop", "lowPrice", "content") + " to "
						+ getContent(element, "itemprop", "highPrice", "content"));
			} else {
				currentLipModel.setProdPrice(getContent(element, "itemprop", "price", "content"));
			}
			currentLipModel.setProdImageURL(getContent(element, "itemprop", "image", "src"));
			// getting individual product description by going into product page
			currentLipModel.setProdDescription(temp.select("div.primary-content").first()
					.getElementsByAttributeValue("itemprop", "description").text());

			lipsticks.add(currentLipModel); // adding into list of LipstickType objects
		}

		PrintToCSV.printToCSVFile(lipsticks);// printing list of LipstickModel objects to csv file

	}

	public static String getContent(Element element, String key, String attr, String value) {

		return element.getElementsByAttributeValue(key, attr).attr(value);
	}

}
