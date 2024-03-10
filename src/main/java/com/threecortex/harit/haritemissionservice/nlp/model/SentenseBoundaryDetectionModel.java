package com.threecortex.harit.haritemissionservice.nlp.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.springframework.stereotype.Component;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

@Component
public class SentenseBoundaryDetectionModel {

	public String[] sentenseBOundaryDetection(String documentText) {
		
		InputStream inputStream;
		SentenceModel sentenceModel;
		SentenceDetectorME sentenceDetector;
		String sentences[] = null;
		try {
			inputStream=new FileInputStream(new File("opennlp-en-ud-ewt-sentence-1.0-1.9.3.bin"));
			
			sentenceModel = new SentenceModel(inputStream);
			sentenceDetector = new SentenceDetectorME(sentenceModel);
			sentences = sentenceDetector.sentDetect(documentText);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return sentences;
	
	}
}
