package com.threecortex.harit.haritemissionservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.threecortex.harit.haritemissionservice.dto.HaritEvalParam;
import com.threecortex.harit.haritemissionservice.dto.HaritScoreResponseDTO;
import com.threecortex.harit.haritemissionservice.dto.ModelInputParamDTO;
import com.threecortex.harit.haritemissionservice.dto.RiskEvalCategoryResponseDTO;
import com.threecortex.harit.haritemissionservice.dto.RiskEvaluationParam;
import com.threecortex.harit.haritemissionservice.nlp.model.dto.SentimentAnalysisDTO;
import com.threecortex.harit.haritemissionservice.service.impl.HaritScoreServiceImpl;

@RestController
@RequestMapping(path = "/harit-eval", produces = MediaType.APPLICATION_JSON_VALUE)
public class HaritScoreController {
	
	@Autowired
	HaritScoreServiceImpl haritScoreServiceImpl;
	



	@PostMapping(path="/execute-model")
	public ResponseEntity<HaritScoreResponseDTO> executeModel(@RequestBody ModelInputParamDTO executeModel) throws Exception {
		
		HaritScoreResponseDTO haritScoreResponseDTO=	haritScoreServiceImpl.executeHaritModel(executeModel);
		
		return  new ResponseEntity<HaritScoreResponseDTO>(haritScoreResponseDTO, HttpStatus.OK);
	}
	
	
	@GetMapping(path="/risk-eval-result")
	public ResponseEntity<List<RiskEvalCategoryResponseDTO>> fetchRiskEvalDetail(@RequestParam Long entityId,@RequestParam Long evalSetId,@RequestParam Long entityIngestionId,@RequestParam Long templateId) throws Exception {
		
		List<RiskEvalCategoryResponseDTO> result=	haritScoreServiceImpl.fetchRiskEvalDetail(entityId,evalSetId,entityIngestionId,templateId);
		
	return  new ResponseEntity<List<RiskEvalCategoryResponseDTO>>(result, HttpStatus.OK);
	}
	
	
	@PostMapping(path="/sentenseCategorize")
	public ResponseEntity<String> sentenseCategorize(@RequestBody HaritEvalParam haritEvalDTO) {
		haritScoreServiceImpl.computeHaritScoreRiskCategory(haritEvalDTO);
		
		return new ResponseEntity<String>("", HttpStatus.OK);
	}
	
	@PostMapping(path="/sentimentAnalysis")
	public ResponseEntity<List<SentimentAnalysisDTO>> sentimentAnalysis(@RequestBody RiskEvaluationParam riskEvaluationParam) {
		List<SentimentAnalysisDTO> list=haritScoreServiceImpl.sentimentAnalysisSubCategoryWise(riskEvaluationParam);
		
		return new ResponseEntity<List<SentimentAnalysisDTO>>(list, HttpStatus.OK);
	}
	

}
