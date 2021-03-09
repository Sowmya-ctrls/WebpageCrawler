# Website Scraper prototype

- This is a prototype web scraper, which used Jsoup library to scrape listing metadata from an arbitrary website. This scraper is built mainly for educational purposes and not intended to be used at scale or for commercial purposes.

## Prerequisites
- Download jsoup-1.13.1 jar and configure the build project(add the jar into classpath) to be able run the project

## Structure
- LipstickModel.java represents the real world metadata of lipstick listing on the selected website
- JsoupCrawler.java represents the crawling functionality with HTML DOM parsing logic for fetching metadata
- PrintToCSV.java is a miniature csv writer which writes LipstickModel object to a csv file
- lipstickdetails.csv has sample output scraped from this webscraper
