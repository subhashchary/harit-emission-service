package com.threecortex.harit.haritemissionservice.nlp.model.dto;

public class SentimentAnalysisDTO {

	private String sentimentCategoryName;
	private double postiveScore;
	private double neutralScore;
	private double negativeScore;
	private double bestScore;
	private String statement;
	
	private Long entityId;
	private Long evalSetId;
	private Long entityIngestionId;
	private Long templateId;
	private Long compositionId;
	private Long riskCategoryId;
	private Long subCategoryId;
	private Long selfPublic;
	private Long runId; 
	private int paramWeight;
	
	public String getSentimentCategoryName() {
		return sentimentCategoryName;
	}
	public void setSentimentCategoryName(String sentimentCategoryName) {
		this.sentimentCategoryName = sentimentCategoryName;
	}
	public double getPostiveScore() {
		return postiveScore;
	}
	public void setPostiveScore(double postiveScore) {
		this.postiveScore = postiveScore;
	}
	public double getNeutralScore() {
		return neutralScore;
	}
	public void setNeutralScore(double neutralScore) {
		this.neutralScore = neutralScore;
	}
	public double getNegativeScore() {
		return negativeScore;
	}
	public void setNegativeScore(double negativeScore) {
		this.negativeScore = negativeScore;
	}
	public String getStatement() {
		return statement;
	}
	public void setStatement(String statement) {
		this.statement = statement;
	}
	public double getBestScore() {
		return bestScore;
	}
	public void setBestScore(double bestScore) {
		this.bestScore = bestScore;
	}
	public Long getEntityId() {
		return entityId;
	}
	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}
	public Long getEvalSetId() {
		return evalSetId;
	}
	public void setEvalSetId(Long evalSetId) {
		this.evalSetId = evalSetId;
	}
	public Long getEntityIngestionId() {
		return entityIngestionId;
	}
	public void setEntityIngestionId(Long entityIngestionId) {
		this.entityIngestionId = entityIngestionId;
	}
	public Long getTemplateId() {
		return templateId;
	}
	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}
	public Long getCompositionId() {
		return compositionId;
	}
	public void setCompositionId(Long compositionId) {
		this.compositionId = compositionId;
	}
	public Long getRiskCategoryId() {
		return riskCategoryId;
	}
	public void setRiskCategoryId(Long riskCategoryId) {
		this.riskCategoryId = riskCategoryId;
	}
	public Long getSubCategoryId() {
		return subCategoryId;
	}
	public void setSubCategoryId(Long subCategoryId) {
		this.subCategoryId = subCategoryId;
	}
	public Long getSelfPublic() {
		return selfPublic;
	}
	public void setSelfPublic(Long selfPublic) {
		this.selfPublic = selfPublic;
	}
	public Long getRunId() {
		return runId;
	}
	public void setRunId(Long runId) {
		this.runId = runId;
	}
	public int getParamWeight() {
		return paramWeight;
	}
	public void setParamWeight(int paramWeight) {
		this.paramWeight = paramWeight;
	}
	
	
}
