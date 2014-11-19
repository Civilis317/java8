package nl.playground.java8;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class StreamsMain {
	
	private static final String DATAFILE = "/data/dev/java/experiments/java8/data/dosbox.conf";
	
	/**
	 * Pre java 8 streams filereader:
	 * 
	 * @throws IOException
	 */
	private static void readLinesUsingFileReader() throws IOException {
		File file = new File(DATAFILE);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String line;
		while ((line = br.readLine()) != null) {
			if (line.contains("mapperfile")) {
				System.out.println(line);
			}
		}
		br.close();
	    fr.close();
	}
	
	private static void readLinesUsingStream() throws IOException {
		Stream<String> lines= Files.lines(Paths.get(DATAFILE));
		Predicate<String> predicate = s -> s.startsWith("#");
		Optional<String> isActive = lines.filter(predicate).findFirst();
		if (isActive.isPresent()) {
			System.out.println(isActive.get());
		}
		//Close the stream and it's underlying file as well
		lines.close();
	}
	
	private static void readStreamOfLinesUsingTryBlock() throws IOException {
		Path path = Paths.get(DATAFILE);
		//The stream hence file will also be closed here
		try (Stream<String> lines = Files.lines(path)) {
			Optional <String> selectedLine = lines.filter(s -> (s.contains("emulate"))).findFirst();
			if (selectedLine.isPresent()) {
				System.out.println(selectedLine.get());
			}
		}
	}
	
	private static void readStreamOfLinesUsingTryBlock2() throws IOException {
		Path path = Paths.get(DATAFILE);
		//When filteredLines is closed, it closes underlying stream as well as underlying file.
		try (Stream<String> filteredLines = Files.lines(path)
				//test if file is closed or not
				.onClose(() -> System.out.println("File closed"))
				.filter(s -> !s.startsWith("#") & s.length() > 0)) {
			Optional<String> selectedLine = filteredLines.findFirst();
			if (selectedLine.isPresent()) {
				System.out.println(selectedLine.get());
			}
		}
	}
	
	private static void filterMultipleLines() throws IOException {
		Path path = Paths.get(DATAFILE);
		// Stream Files.lines is colelcted in a List
		//List<String> lines = Files.lines(path).filter(s -> !s.startsWith("#") & s.length() > 0).collect(Collectors.toList());
//		for (String element : lines) {
//			System.out.println(element);
//		}
		// compacter is:
		//lines.forEach((string) -> {System.out.println(string);});
		
		// but most compact is:
		Files.lines(path).filter(s -> !s.startsWith("#") & s.length() > 0).forEach((string) -> {System.out.println(string);});
	}
	
	
	public static void main (String[] args) throws IOException {
		readLinesUsingFileReader();
		readLinesUsingStream();
		readStreamOfLinesUsingTryBlock();
		readStreamOfLinesUsingTryBlock2();
		filterMultipleLines();
	}

}
