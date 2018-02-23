package edu.carleton.comp4601.crawlerstuff;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

import org.bson.Document; 

import com.mongodb.MongoClient; 
import com.mongodb.client.MongoCollection; 
import com.mongodb.client.MongoDatabase; 


public class Controller {
    public static void control() throws Exception {
    	// "/data/crawl/root"
        String crawlStorageFolder = "/data/crawl/root";
        int numberOfCrawlers = 7;

        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorageFolder);
        config.setIncludeBinaryContentInCrawling(true);
        config.setMaxPagesToFetch(100);
        config.setPolitenessDelay(1000);
        //config.setMaxDepthOfCrawling(10);
        

        /*
         * Instantiate the controller for this crawl.
         */
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

        /*
         * For each crawl, you need to add some seed urls. These are the first
         * URLs that are fetched and then the crawler starts following links
         * which are found in these pages
         */
        
      
        //controller.addSeed("http://www.carleton.ca");
        //controller.addSeed("https://sikaman.dyndns.org/courses/4601/resources/");
        //controller.addSeed("https://sikaman.dyndns.org/courses/4601/handouts/");
    	//controller.addSeed("http://www.ics.uci.edu/");
        //controller.addSeed("https://sikaman.dyndns.org/courses/4601/lecture-9/N-0.html");
        controller.addSeed("http://sikaman.dyndns.org:8888/courses/4601/resources/N-0.html");
        //controller.addSeed("http://www.carleton.ca");
        //controller.addSeed("http://www.zdirect.com");
    	
        /*
         * Start the crawl. This is a blocking operation, meaning that your code
         * will reach the line after this only when crawling is finished.
         */
        controller.start(MyCrawler.class, numberOfCrawlers);
        
        
    	
         
        
        
    }
}
