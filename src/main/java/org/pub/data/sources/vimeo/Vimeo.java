package org.pub.data.sources.vimeo;

import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.pub.pt.data.sources.domain.AbstractDataSource;
import org.pub.global.utils.DomUtils;
import org.pub.data.sources.vimeo.domain.VimeoVideo;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * This class will retrieve information about a specific video in vimeo
 * @author balhau
 *
 */
public class Vimeo extends AbstractDataSource{

	class PageVimeoScrapper implements Callable<List<String>>{

		private String url;

		public PageVimeoScrapper(String urlFragment){
			this.url=String.format(BASE_URL_PATTERN,urlFragment);
		}

		@Override
		public List<String> call() throws Exception {
			Connection con = DomUtils.get(this.url);
			Document doc = con.get();
			String description=doc.getElementsByTag("meta").get(7).attr("content");
			return Arrays.asList(description);
		}
	}

	class PlayerVimeoScrapper implements Callable<List<String>>{
		private String url;

		public PlayerVimeoScrapper(String urlFragment){
			this.url=String.format(PLAYER_URL_PATTERN,urlFragment);
		}

		@Override
		public List<String> call() throws Exception {
			Connection con = DomUtils.get(this.url);
			Document doc = con.get();

			//Scrapped from htmlpage
			String urlMovie=doc.body().toString().split("\"url\":")[2].split(",")[0].replaceAll("\"","");
			String titleMovie=doc.body().toString().split("\"title\":")[1].split(",\"url\":")[0].replaceAll("\"","");
			String duration = doc.body().toString().split("\"duration\":")[1].split(",\"id\":")[0];
			return Arrays.asList(urlMovie,titleMovie,duration);
		}
	}

	public static String BASE_URL="http://vimeo.com/";
	private static String BASE_URL_PATTERN="http://vimeo.com/%s";
	private static String PLAYER_URL_PATTERN="https://player.vimeo.com/video/%s";
	private static int THREAD_POOL=2;

	private ExecutorService executor;
	
	public Vimeo(){
		executor= Executors.newFixedThreadPool(THREAD_POOL);
	}

	/**
	 * This will fetch the video metadata from a specific url
	 * @param url String url of the video
	 * @return VimeoVideo metadata
	 * @throws Exception
     */
	public VimeoVideo getVideo(String url) throws Exception{
		String fragment=extractFragment(url);
		Future<List<String>> playerFuture=executor.submit(new PlayerVimeoScrapper(fragment));
		Future<List<String>> pageFuture=executor.submit(new PageVimeoScrapper(fragment));

		List<String> playerList=playerFuture.get();
		List<String> pageList = pageFuture.get();

		return new VimeoVideo(
				playerList.get(1),
				pageList.get(0),
				playerList.get(0),
				Integer.valueOf(playerList.get(2))
		);
	}

	private String extractFragment(String url){
		return url.split(BASE_URL)[1];
	}

	public static void main(String[] args) throws Exception {
		Vimeo v=new Vimeo();
		//https://player.vimeo.com/video/117241365
		System.out.println(v.getVideo("https://vimeo.com/117241365"));
	}
}
