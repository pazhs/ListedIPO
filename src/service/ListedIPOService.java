package service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import constant.StrLtrConst;

public class ListedIPOService {
	
	// start page of moneycontrol's IPO
	private int ipoPageNumber = 1;
	
	
	public boolean execute(){
		
		for(int index = 0; index <= 25; index++) {
			fetchIPOData();
		}
		
		return true;
	}
	
	private boolean fetchIPOData() {
		
		Document doc = null;
		Document companyDoc = null;
		Elements contents = null;
		
		StringBuffer sbIPOPageURL = null;
		StringBuffer sbCompanyURL = null;
		
		String mmYearIPO = "";
		String companyName;
		String strCompanyURL;
		String issuePrice ;
		String marketCap = " ";
		String bse = " ";
		String nse = " ";
		String isin = " ";
		String info = " ";
		
		String date = null;
		Elements capValue = null;
		Elements bseValue = null;
		
		try{
			sbIPOPageURL = new StringBuffer(StrLtrConst.LI_IPO_URL).append(ipoPageNumber++);
			
			System.out.println(sbIPOPageURL);
			
			doc = Jsoup.connect(sbIPOPageURL.toString()).get();
			contents = doc
					.select("#ipoissueDiv_3 table tr td a[target]");
			
			mmYearIPO = "";
			
			try{
				
				for (Element content : contents) {
					
					sbCompanyURL = new StringBuffer(StrLtrConst.LI_companyUrl);
					
					if(mmYearIPO.isEmpty()) {
						// month & year of IPO Issue
						mmYearIPO = content.parent().parent().parent()
							.previousElementSibling().text().trim();
					}
					companyName = content.text().trim();
					strCompanyURL = (sbCompanyURL.append(content.attr("href"))).toString().trim();// Deriving IPO Link
					issuePrice = content.parent().parent().parent()
							.select("td:nth-of-type(2)").text();// Deriving IPO
																// Issue Price
					
					companyDoc = Jsoup.connect(strCompanyURL).get();// Connecting to
																// IPO Link
					capValue = null;
					capValue = companyDoc.select("#mktdet_2 .gD_12");// Market
																				// Cap
					bseValue = companyDoc.select(".PB10 .gry10");
					info = bseValue.text();// BSE NSE ISIN INFO
					bse = info.split("\\|")[0].split(":")[1].trim();
					nse = info.split("\\|")[1].split(":")[1].trim();
					isin = info.split("\\|")[2].split(":")[1].trim();
					
					if (bse.equals("") || bse.equals(" "))
						bse = "0";
					if (nse.equals("") || nse.equals(" "))
						nse = "0";
					if (isin.equals("") || isin.equals(" "))
						isin = "0";
					marketCap = capValue.first().text();
					marketCap = marketCap.replaceAll("[,]", "");
					
					/*if (mmYearIPO.matches("(.*)(\\-+)(\\d*)")) {
						mmYearIPO = mmYearIPO.replaceAll("\\s", "");
						date = mmYearIPO;
					}*/
					
					/*
					if (companyName != null) {
						System.out.println(date + " , " + companyName + " , "
								+ companyUrl + " , " + issuePrice + " , "
								+ marketCap + " , " + bse + " , " + nse + " , "
								+ isin + " , " + currDate);
						log.info("Info of " + companyName
								+ " added in csv file...");
						String bytes = date + " , " + companyName + " , "
								+ companyUrl + " , " + issuePrice + " , "
								+ marketCap + " , " + bse + " , " + nse + " , "
								+ isin + " , " + currDate + "\n";
						fos.write(bytes.getBytes());
					}
					
					*/
				}
			} catch(Exception ex) {
				
			}
			
		}catch(Exception ex) {
			System.out.println("stop");
		}
		
		return true;
	}

}
