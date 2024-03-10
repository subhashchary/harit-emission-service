package com.threecortex.harit.haritemissionservice.nlp.model;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.threecortex.harit.haritemissionservice.nlp.model.dto.SentimentAnalysisDTO;

import opennlp.tools.doccat.DoccatFactory;
import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizer;
import opennlp.tools.doccat.DocumentCategorizerME;
import opennlp.tools.doccat.DocumentSampleStream;
import opennlp.tools.util.InputStreamFactory;
import opennlp.tools.util.MarkableFileInputStreamFactory;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.TrainingParameters;

@Component
public class SentimentAnalysisModel {


	@Value("${sentense.subcategorize.threshold}")
	private double sentenseThreshold;

	public SentimentAnalysisDTO sentimentAnalysis(String sentense) {
		InputStreamFactory dataIn;
		ObjectStream lineStream;
		ObjectStream sampleStream;
		SentimentAnalysisDTO sentimentAnalysis = null;
		double positveScore = 0;
		double neutralScore = 0;
		double negativeScore = 0;
		double bestScore = 0;
		try {
			sentimentAnalysis = new SentimentAnalysisDTO();
			dataIn = new MarkableFileInputStreamFactory(new File("en-harit-sentiment-analysis.train"));
			lineStream = new PlainTextByLineStream(dataIn, "UTF-8");
			sampleStream = new DocumentSampleStream(lineStream);

			// define the training parameters
			TrainingParameters params = new TrainingParameters();
			params.put(TrainingParameters.ITERATIONS_PARAM, 10 + "");
			params.put(TrainingParameters.CUTOFF_PARAM, 0 + "");

			// create a model from traning data
			DoccatModel model = DocumentCategorizerME.train("en", sampleStream, params, new DoccatFactory());
			

			// save the model to local
			BufferedOutputStream modelOut = new BufferedOutputStream(
					new FileOutputStream("en-harit-sentiment-analysis.bin"));
			model.serialize(modelOut);

			// test the model file by subjecting it to prediction
			DocumentCategorizer sentenseCategorizerModel = new DocumentCategorizerME(model);
			// String[] docWords = "Carbon nuetral by".replaceAll("[^A-Za-z]", " ").split("
			// ");
			// String[] docWords = "The Bank has been reporting to the Carbon Disclosure
			// Project (CDP) on its climate change strategy".replaceAll("[^A-Za-z]", "
			// ").split(" ");;
			String[] docWords = sentense.replaceAll("[^A-Za-z]", " ").split(" ");
			Map<String, Double> sentimentDataMap=sentenseCategorizerModel.scoreMap(docWords);
			double[] aProbs = sentenseCategorizerModel.categorize(docWords);
			String bestCategory=sentenseCategorizerModel.getBestCategory(aProbs);
			 for (Map.Entry<String, Double> entry : sentimentDataMap.entrySet()) {
			        System.out.println(entry.getKey() + ":" + entry.getValue());
			        if(entry.getKey().contains("positive")) {
			        	positveScore= entry.getValue();
			        	if(bestCategory.contains("positive")) {
			        		bestScore=positveScore;
			        	}
			        }else if(entry.getKey().contains("negative")) {
			        	negativeScore=entry.getValue();
			        	if(bestCategory.contains("negative")) {
			        		bestScore=negativeScore;
			        	}
			        }else if(entry.getKey().contains("neutral")) {
			        	neutralScore=entry.getValue();
			        	if(bestCategory.contains("neutral")) {
			        		bestScore=neutralScore;
			        	}
			        }
			        
			    }
			 sentimentAnalysis.setNegativeScore(negativeScore);
			 
			 sentimentAnalysis.setNeutralScore(neutralScore);
			 sentimentAnalysis.setPostiveScore(positveScore);
			 sentimentAnalysis.setBestScore(bestScore);
			 sentimentAnalysis.setStatement(sentense);
			 
		} catch (Exception e) {
				e.printStackTrace();
		}
		return sentimentAnalysis;

	}

}
