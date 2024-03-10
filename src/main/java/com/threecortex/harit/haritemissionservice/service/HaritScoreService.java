package com.threecortex.harit.haritemissionservice.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.threecortex.harit.haritemissionservice.dto.HaritEvalParam;
import com.threecortex.harit.haritemissionservice.dto.HaritScoreResponseDTO;
import com.threecortex.harit.haritemissionservice.dto.ModelInputParamDTO;
import com.threecortex.harit.haritemissionservice.dto.RiskEvaluationParam;
import com.threecortex.harit.haritemissionservice.nlp.model.dto.SentenseCategorizerDTO;
import com.threecortex.harit.haritemissionservice.nlp.model.dto.SentimentAnalysisDTO;

@Service
public interface HaritScoreService {

	public void dataPreprocessing(HaritEvalParam haritEvalDTO)  throws Exception;
	
    public  HashMap<String, List<SentenseCategorizerDTO>> sentenseCategorizer(HaritEvalParam haritEvalParam)  throws Exception;
	
	public List<SentimentAnalysisDTO> sentimentAnalysisSubCategoryWise(RiskEvaluationParam riskEvaluationParam)  throws Exception;
	
	public HaritScoreResponseDTO executeHaritModel(ModelInputParamDTO executeModel)  throws Exception;
	
	
}
