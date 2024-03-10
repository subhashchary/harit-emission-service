package com.threecortex.harit.haritemissionservice.nlp.model;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.threecortex.harit.haritemissionservice.nlp.model.dto.SentenseCategorizerDTO;

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
public class SentenseCategorizeModel {

	@Value("${sentense.subcategorize.threshold}")
	private double sentenseThreshold;

	public SentenseCategorizerDTO sentenseCategorizer(String sentense) {
		InputStreamFactory dataIn;
		ObjectStream lineStream;
		ObjectStream sampleStream;
		SentenseCategorizerDTO sentenseCategorizer = null;
		try {
			sentenseCategorizer = new SentenseCategorizerDTO();
			dataIn = new MarkableFileInputStreamFactory(new File("en-sentense-category.train"));
			lineStream = new PlainTextByLineStream(dataIn, "UTF-8");
			sampleStream = new DocumentSampleStream(lineStream);

			// define the training parameters
			TrainingParameters params = new TrainingParameters();
			params.put(TrainingParameters.ITERATIONS_PARAM, 10 + "");
			params.put(TrainingParameters.CUTOFF_PARAM, 0 + "");

			// create a model from traning data
			DoccatModel model = DocumentCategorizerME.train("en", sampleStream, params, new DoccatFactory());
			System.out.println("Model trained");

			// save the model to local
			BufferedOutputStream modelOut = new BufferedOutputStream(
					new FileOutputStream("en-sentense-category.bin"));
			model.serialize(modelOut);

			// test the model file by subjecting it to prediction
			DocumentCategorizer sentenseCategorizerModel = new DocumentCategorizerME(model);
			// String[] docWords = "Carbon nuetral by".replaceAll("[^A-Za-z]", " ").split("
			// ");
			// String[] docWords = "The Bank has been reporting to the Carbon Disclosure
			// Project (CDP) on its climate change strategy".replaceAll("[^A-Za-z]", "
			// ").split(" ");;
			String[] docWords = sentense.replaceAll("[^A-Za-z]", " ").split(" ");
			double[] allProbabiltiy = sentenseCategorizerModel.categorize(docWords);

			//  probabilities of the categories
			for (int i = 0; i < sentenseCategorizerModel.getNumberOfCategories(); i++) {
				System.out.println(sentenseCategorizerModel.getCategory(i) + " : " + allProbabiltiy[i]);
				if (allProbabiltiy[i] > sentenseThreshold) {
					sentenseCategorizer.setCategoryProbability(allProbabiltiy[i]);
					sentenseCategorizer.setSentenseCategoryName(sentenseCategorizerModel.getCategory(i));
					sentenseCategorizer.setSentense(sentense);
				}
			}

		} catch (Exception e) {
				e.printStackTrace();
		}
		return sentenseCategorizer;

	}
}
