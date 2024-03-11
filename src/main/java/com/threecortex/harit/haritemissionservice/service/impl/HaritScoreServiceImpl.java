package com.threecortex.harit.haritemissionservice.service.impl;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.threecortex.harit.haritemissionservice.dto.HaritEvalParam;
import com.threecortex.harit.haritemissionservice.dto.HaritScoreResponseDTO;
import com.threecortex.harit.haritemissionservice.dto.ModelInputParamDTO;
import com.threecortex.harit.haritemissionservice.dto.RiskEvalCategoryResponseDTO;
import com.threecortex.harit.haritemissionservice.dto.RiskEvalDTO;
import com.threecortex.harit.haritemissionservice.dto.RiskEvalResponseDTO;
import com.threecortex.harit.haritemissionservice.dto.RiskEvalSubCategoryResponseDTO;
import com.threecortex.harit.haritemissionservice.dto.RiskEvaluationParam;
import com.threecortex.harit.haritemissionservice.model.DataSubmission;
import com.threecortex.harit.haritemissionservice.model.RiskEval;
import com.threecortex.harit.haritemissionservice.model.RiskEvalExecution;
import com.threecortex.harit.haritemissionservice.model.RiskEvalId;
import com.threecortex.harit.haritemissionservice.model.RiskEvalPreprocess;
import com.threecortex.harit.haritemissionservice.model.RiskEvaluationDetail;
import com.threecortex.harit.haritemissionservice.model.RiskParamMaster;
import com.threecortex.harit.haritemissionservice.model.RiskTemplateComposition;
import com.threecortex.harit.haritemissionservice.model.SentenseRiskCategorization;
import com.threecortex.harit.haritemissionservice.model.SubRiskParamMaster;
import com.threecortex.harit.haritemissionservice.nlp.model.SentenseBoundaryDetectionModel;
import com.threecortex.harit.haritemissionservice.nlp.model.SentenseCategorizeModel;
import com.threecortex.harit.haritemissionservice.nlp.model.SentimentAnalysisModel;
import com.threecortex.harit.haritemissionservice.nlp.model.dto.SentenseCategorizerDTO;
import com.threecortex.harit.haritemissionservice.nlp.model.dto.SentimentAnalysisDTO;
import com.threecortex.harit.haritemissionservice.repository.DataSubmissionRepository;
import com.threecortex.harit.haritemissionservice.repository.RiskEvalDetailRepository;
import com.threecortex.harit.haritemissionservice.repository.RiskEvalExecutionRepository;
import com.threecortex.harit.haritemissionservice.repository.RiskEvalPreprocessRepository;
import com.threecortex.harit.haritemissionservice.repository.RiskEvalRepository;
import com.threecortex.harit.haritemissionservice.repository.RiskParamMasterRepo;
import com.threecortex.harit.haritemissionservice.repository.RiskTemplateCompositionRepo;
import com.threecortex.harit.haritemissionservice.repository.RiskTemplateMasterRepo;
import com.threecortex.harit.haritemissionservice.repository.SentenseRiskCategorizationRepository;
import com.threecortex.harit.haritemissionservice.repository.SubRiskParamMasterRepo;
import com.threecortex.harit.haritemissionservice.service.HaritScoreService;

@Service
public class HaritScoreServiceImpl implements HaritScoreService {
	private final static float FONT_SIZE = 12;
	private final static float LEADING = 1.5f * 12;

	@Autowired
	RiskTemplateCompositionRepo compositionRepo;

	@Autowired
	RiskTemplateMasterRepo riskTemplateMasterRepo;

	@Autowired
	SentenseCategorizeModel sentenseCategorizeModel;

	@Autowired
	SentimentAnalysisModel sentimentAnalysisModel;

	@Autowired
	SentenseBoundaryDetectionModel sentenseBoundaryDetectionModel;

	@Value("${sentense.subcategroize.outpath}")
	private String subcategoryFilePath;

	@Value("${sentense.file-sbd.path}")
	private String fileSBDPath;

	@Autowired
	private DataSubmissionRepository dataSubmissionRepository;

	@Value("${harit.source.filepath}")
	private String haritSourceFilePath;

	@Autowired
	SubRiskParamMasterRepo subRiskParamMasterRepo;

	@Autowired
	RiskEvalDetailRepository riskEvalDetailRepository;

	@Autowired
	RiskEvalExecutionRepository riskEvalExecutionRepository;

	@Autowired
	SentenseRiskCategorizationRepository sentenseRiskCategorizationRepository;

	@Autowired
	RiskEvalPreprocessRepository riskEvalPreprocessRepository;
	
	@Value("${harit.model.positive.weightage}")
	private Double positiveWieghtage;
	
	@Value("${harit.model.neutral.weightage}")
	private Double neutralWeightage;
	
	@Value("${harit.model.negative.weightage}")
	private Double negativeWeightage;
	
	@Value("${harit.model.weightage.factor}")
	private Double weightageFactor;
	
	
	@Autowired
	private RiskEvalRepository riskEvalRepository;
	
	@Autowired
	RiskParamMasterRepo riskParamMasterRepo;
	
	@Override
	public HaritScoreResponseDTO executeHaritModel(ModelInputParamDTO executeModel) throws Exception {

		HaritScoreResponseDTO haritScoreResponseDTO = null;

		HaritEvalParam haritEvalParam = null;
		try {
			haritScoreResponseDTO = new HaritScoreResponseDTO();
			haritScoreResponseDTO.setEntityId(executeModel.getEntityId());
			haritScoreResponseDTO.setEntityIngestionId(executeModel.getEntityIngestionId());
			haritScoreResponseDTO.setEvalSetId(executeModel.getEvalSetId());
			haritScoreResponseDTO.setTemplateId(executeModel.getTemplateId());
			
			haritEvalParam = createHaritEvalParam(executeModel);
			saveLatestRunId(haritEvalParam);
			
			dataPreprocessing(haritEvalParam);
			sentenseCategorizerModelExecution(haritEvalParam);
			sentimentAnalysisModelExecution(haritEvalParam);
			computeHaritScoreRiskCategory(haritEvalParam);
			
			haritScoreResponseDTO.setRunId(haritEvalParam.getRunId());
			haritScoreResponseDTO.setMessage("Model Successfully executed with runId "+haritEvalParam.getRunId());
		} catch (Exception e) {
			e.printStackTrace();
			haritScoreResponseDTO.setMessage("ModelExecution failed");
		}

		return haritScoreResponseDTO;
	}

	public void computeHaritScoreRiskCategory(HaritEvalParam haritEvalParam) {
		
		try {
			List<RiskEvaluationDetail> riskEvaList=riskEvalDetailRepository.findByEntityIdAndEvalSetIdAndEntityIngestionIdAndTemplateIdAndRunId(haritEvalParam.getEntityId(), haritEvalParam.getEvalSetId(), haritEvalParam.getEntityIngestionId(), haritEvalParam.getTemplateId(), 
					haritEvalParam.getRunId());
				Map<Long, Double> average=riskEvaList.stream().collect(Collectors.groupingBy(RiskEvaluationDetail::getSubCategoryId,Collectors.averagingDouble(RiskEvaluationDetail::getTotalWeightage)));
			
				Map <Long,List<RiskEvaluationDetail>> result=	riskEvaList.stream().collect(Collectors.groupingBy(RiskEvaluationDetail::getSubCategoryId,Collectors.toList()));
	
			for (Map.Entry<Long, List<RiskEvaluationDetail>> entrySet : result.entrySet()) {
			
				RiskEval riskEval=new RiskEval();
				RiskEvalId riskEvalId=new RiskEvalId();
				RiskEvaluationDetail details=entrySet.getValue().get(0);
				
				Double averageValue=	average.get(entrySet.getKey());
	
				riskEvalId.setEntityId(details.getEntityId());
				riskEvalId.setCompositionId(details.getCompositionId());
				riskEvalId.setEntityIngestionId(details.getEntityIngestionId());
				riskEvalId.setEvalSetId(details.getEvalSetId());
				riskEvalId.setRiskCategoryId(details.getRiskCategoryId());
				riskEvalId.setRunId(details.getRunId());
				riskEvalId.setSelfPublic(details.getSelfPublic());
				riskEvalId.setSubCategoryId(entrySet.getKey());
				riskEvalId.setTemplateId(details.getTemplateId());
				riskEval.setScore(averageValue);
				riskEval.setOcStatus("O");
				riskEval.setVerifiedBy(details.getEntityId().intValue());
				riskEval.setParamWeight(details.getParamWeight());
				riskEval.setRunDate(details.getRunDate());
				riskEval.setRiskEvalId(riskEvalId);
				riskEvalRepository.save(riskEval);
				
			
			}
		
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		
	}

	private void sentimentAnalysisModelExecution(HaritEvalParam haritEvalParam) throws Exception {
		SubRiskParamMaster subRiskParamMaster = null;
		try {

			List<RiskTemplateComposition> riskCompositionList = compositionRepo
					.findByRiskTemplateMasterTemplateIdOrderByCompositionId(haritEvalParam.getTemplateId());
			RiskEvalExecution riskEvalExecution = riskEvalExecutionRepository
					.findTopByEntityIdAndEvalSetIdAndEntityIngestionIdAndTemplateIdOrderByRunIdDesc(
							haritEvalParam.getEntityId(), haritEvalParam.getEvalSetId(),
							haritEvalParam.getEntityIngestionId(), haritEvalParam.getTemplateId());
			List<SentenseRiskCategorization> sentenseRiskCategoryList = sentenseRiskCategorizationRepository
					.findByRunId(riskEvalExecution.getRunId());

			for (SentenseRiskCategorization sentenseRiskCategorization : sentenseRiskCategoryList) {

				for (RiskTemplateComposition riskTemplateComposition : riskCompositionList) {

					subRiskParamMaster = subRiskParamMasterRepo.findById(riskTemplateComposition.getSubCategoryId())
							.get();
					String categoryName = subRiskParamMaster.getSubCategoryName().replace(" ", "");
					if (sentenseRiskCategorization.getSubCategoryName().equalsIgnoreCase(categoryName)) {
						RiskEvaluationParam riskEvaluationParam = new RiskEvaluationParam();

						riskEvaluationParam.setCompositionId(riskTemplateComposition.getCompositionId());
						riskEvaluationParam.setCategoryId(riskTemplateComposition.getRiskCategoryId());
						riskEvaluationParam.setEntityId(sentenseRiskCategorization.getEntityId());
						riskEvaluationParam.setEntityIngestionId(sentenseRiskCategorization.getEntityIngestionId());
						riskEvaluationParam.setEvalSetId(sentenseRiskCategorization.getEvalSetId());
						riskEvaluationParam.setFilePath(sentenseRiskCategorization.getSubCategoryOutPath());
						riskEvaluationParam.setParamWeight(riskTemplateComposition.getParamWeight());
						riskEvaluationParam.setSubCategoryFileName(sentenseRiskCategorization.getSubCategoryName());
						riskEvaluationParam.setRunId(sentenseRiskCategorization.getRunId());
						riskEvaluationParam.setRunDate(sentenseRiskCategorization.getRunDate());
						riskEvaluationParam
								.setSubCategoryFileOutPath(sentenseRiskCategorization.getSubCategoryOutPath());
						riskEvaluationParam.setTemplateId(haritEvalParam.getTemplateId());
						riskEvaluationParam.setSubCategoryId(riskTemplateComposition.getSubCategoryId());
						List<SentimentAnalysisDTO> sentimentAnalysisDTOList = sentimentAnalysisSubCategoryWise(
								riskEvaluationParam);

						for (SentimentAnalysisDTO sentimentAnalysisDTO : sentimentAnalysisDTOList) {

							RiskEvaluationDetail riskEvaluationDetail = new RiskEvaluationDetail();
							riskEvaluationDetail.setBestScore(sentimentAnalysisDTO.getBestScore());
							riskEvaluationDetail.setCompositionId(sentimentAnalysisDTO.getCompositionId());
							riskEvaluationDetail.setEntityId(sentimentAnalysisDTO.getEntityId());
							riskEvaluationDetail.setEntityIngestionId(sentimentAnalysisDTO.getEntityIngestionId());
							riskEvaluationDetail.setEvalSetId(sentimentAnalysisDTO.getEvalSetId());
							riskEvaluationDetail.setNegativeScore(sentimentAnalysisDTO.getNegativeScore());
							riskEvaluationDetail.setNeutralScore(sentimentAnalysisDTO.getNeutralScore());
							riskEvaluationDetail.setParamWeight(sentimentAnalysisDTO.getParamWeight());
							riskEvaluationDetail.setPostiveScore(sentimentAnalysisDTO.getPostiveScore());
							riskEvaluationDetail.setRiskCategoryId(sentimentAnalysisDTO.getRiskCategoryId());
							riskEvaluationDetail.setRunDate(new Date(System.currentTimeMillis()));
							riskEvaluationDetail.setTemplateId(sentimentAnalysisDTO.getTemplateId());
							riskEvaluationDetail.setRunId(sentimentAnalysisDTO.getRunId());
							riskEvaluationDetail.setSelfPublic(1L);
							riskEvaluationDetail.setOcStatus("O");
							riskEvaluationDetail.setVerifiedBy(sentimentAnalysisDTO.getEntityId().intValue());
							riskEvaluationDetail.setStatement(sentimentAnalysisDTO.getStatement());
							riskEvaluationDetail.setSubCategoryId(sentimentAnalysisDTO.getSubCategoryId());

							riskEvaluationDetail.setPositiveWeightage(riskEvaluationDetail.getPostiveScore()*positiveWieghtage);
							riskEvaluationDetail.setNegativeWeightage(riskEvaluationDetail.getNegativeScore()*negativeWeightage);
							riskEvaluationDetail.setNeutralWeightage(riskEvaluationDetail.getNeutralScore()*neutralWeightage);
							riskEvaluationDetail.setTotalWeightage((riskEvaluationDetail.getPositiveWeightage()+riskEvaluationDetail.getNegativeWeightage()+riskEvaluationDetail.getNeutralWeightage())*weightageFactor);
							riskEvalDetailRepository.save(riskEvaluationDetail);
						}

					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	private HaritEvalParam createHaritEvalParam(ModelInputParamDTO executeModel) {
		HaritEvalParam haritEvalParam = null;
		try {
			haritEvalParam = new HaritEvalParam();
			haritEvalParam.setEntityId(executeModel.getEntityId());
			haritEvalParam.setEvalSetId(executeModel.getEvalSetId());
			haritEvalParam.setEntityIngestionId(executeModel.getEntityIngestionId());
			haritEvalParam.setTemplateId(executeModel.getTemplateId());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return haritEvalParam;
	}

	public void sentenseCategorizerModelExecution(HaritEvalParam haritEvalParam) throws Exception {

		try {

			HashMap<String, List<SentenseCategorizerDTO>> sentenseCategoryMap = sentenseCategorizer(haritEvalParam);

			createSubCategoryPDF(sentenseCategoryMap, haritEvalParam);

		} catch (Exception e) {

		}
	}

	@Override
	public HashMap<String, List<SentenseCategorizerDTO>> sentenseCategorizer(HaritEvalParam haritEvalParam)
			throws Exception {

		File file;
		PDDocument pdDocument;
		PDFTextStripper pdfTextStripper;

		HashMap<String, List<SentenseCategorizerDTO>> subCategoryMap = null;
		try {

			file = new File(haritEvalParam.getFileOutPath());
			pdDocument = PDDocument.load(file);
			pdfTextStripper = new PDFTextStripper();

			String documentText = pdfTextStripper.getText(pdDocument);
			List<String> stringLines = new ArrayList<>();
			documentText.lines().forEach(s -> stringLines.add(s));
			subCategoryMap = new HashMap<>();
			for (String stringRow : stringLines) {
				SentenseCategorizerDTO sentenseCategorizer = sentenseCategorizeModel.sentenseCategorizer(stringRow);

				sentenseCategorizer.setEntityId(haritEvalParam.getEntityId());
				sentenseCategorizer.setEntityIngestionId(haritEvalParam.getEntityIngestionId());
				sentenseCategorizer.setEvalSetId(haritEvalParam.getEvalSetId());
				sentenseCategorizer.setRunId(haritEvalParam.getRunId());
				;
				if (subCategoryMap != null) {
					List<SentenseCategorizerDTO> sentenseList = subCategoryMap
							.get(sentenseCategorizer.getSentenseCategoryName());
					if (sentenseList == null) {
						List<SentenseCategorizerDTO> subCategorylevelList = new ArrayList<SentenseCategorizerDTO>();
						subCategorylevelList.add(sentenseCategorizer);
						subCategoryMap.put(sentenseCategorizer.getSentenseCategoryName(), subCategorylevelList);
					} else {
						sentenseList.add(sentenseCategorizer);
						subCategoryMap.put(sentenseCategorizer.getSentenseCategoryName(), sentenseList);
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;

		}

		return subCategoryMap;

	}

	private void createSubCategoryPDF(HashMap<String, List<SentenseCategorizerDTO>> subCategoryMap,
			HaritEvalParam haritEvalParam) throws Exception {

		try {

			for (Map.Entry<String, List<SentenseCategorizerDTO>> entrySet : subCategoryMap.entrySet()) {
				String subCategoryName = entrySet.getKey();
				List<SentenseCategorizerDTO> sentenseList = entrySet.getValue();
				List<String> sentenses = new ArrayList<String>();
				sentenseList.parallelStream().forEach(c -> c.getSentense());

				for (SentenseCategorizerDTO sentenseCategorizerDTO : sentenseList) {
					sentenses.add(sentenseCategorizerDTO.getSentense());
				}
				String[] categorySentenses = Arrays.copyOf(sentenses.toArray(), sentenses.size(), String[].class);
				String filePath = subcategoryFilePath + "\\" + subCategoryName + ".pdf";

				createMultiLinePagePdf(categorySentenses, filePath);

				SentenseRiskCategorization sentenseRiskCategorization = new SentenseRiskCategorization();

				sentenseRiskCategorization.setEntityId(haritEvalParam.getEntityId());
				sentenseRiskCategorization.setEntityIngestionId(haritEvalParam.getEntityIngestionId());
				sentenseRiskCategorization.setEvalSetId(haritEvalParam.getEvalSetId());
				sentenseRiskCategorization.setTemplateId(haritEvalParam.getTemplateId());
				sentenseRiskCategorization.setRunId(haritEvalParam.getRunId());
				sentenseRiskCategorization.setSelfPublic(1L);
				sentenseRiskCategorization.setRunDate(new Date(System.currentTimeMillis()));

				sentenseRiskCategorization.setSubCategoryOutPath(filePath);
				sentenseRiskCategorization.setSubCategoryName(subCategoryName);

				sentenseRiskCategorization.setOcStatus("O");
				sentenseRiskCategorization.setVerifiedBy(haritEvalParam.getEntityId().intValue());

				sentenseRiskCategorizationRepository.save(sentenseRiskCategorization);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	@Override
	public List<SentimentAnalysisDTO> sentimentAnalysisSubCategoryWise(RiskEvaluationParam riskEvaluationParam) {

		List<SentimentAnalysisDTO> sentimentAnalysisDTOList = null;
		File file;
		PDDocument pdDocument;
		PDFTextStripper pdfTextStripper;

		try {
			sentimentAnalysisDTOList = new ArrayList<>();

			file = new File(riskEvaluationParam.getSubCategoryFileOutPath());

			pdDocument = PDDocument.load(file);
			pdfTextStripper = new PDFTextStripper();

			String data = pdfTextStripper.getText(pdDocument);
			List<String> stringLines = new ArrayList<>();
			data.lines().forEach(s -> stringLines.add(s));

			for (String stringRow : stringLines) {
				SentimentAnalysisDTO sentimentAnalysisDTO = sentimentAnalysisModel.sentimentAnalysis(stringRow);
				sentimentAnalysisDTO.setSentimentCategoryName(riskEvaluationParam.getSubCategoryFileName());
				sentimentAnalysisDTO.setEntityId(riskEvaluationParam.getEntityId());
				sentimentAnalysisDTO.setEntityIngestionId(riskEvaluationParam.getEntityIngestionId());
				sentimentAnalysisDTO.setEvalSetId(riskEvaluationParam.getEvalSetId());
				sentimentAnalysisDTO.setRiskCategoryId(riskEvaluationParam.getCategoryId());
				sentimentAnalysisDTO.setSubCategoryId(riskEvaluationParam.getSubCategoryId());
				sentimentAnalysisDTO.setSelfPublic(1L);
				sentimentAnalysisDTO.setCompositionId(riskEvaluationParam.getCompositionId());
				sentimentAnalysisDTO.setParamWeight(riskEvaluationParam.getParamWeight());
				sentimentAnalysisDTO.setTemplateId(riskEvaluationParam.getTemplateId());
				sentimentAnalysisDTO.setRunId(riskEvaluationParam.getRunId());
				sentimentAnalysisDTOList.add(sentimentAnalysisDTO);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return sentimentAnalysisDTOList;
	}

	@Override
	public void dataPreprocessing(HaritEvalParam haritEvalParam) throws Exception {
		File file;
		PDDocument pdDocument;
		PDFTextStripper pdfTextStripper;
		String messages[];

		String filePath = null;
		try {

			// assuming one submission to a ingestionId
			List<DataSubmission> dataSubmissionList = dataSubmissionRepository
					.findByentityIngestionId(haritEvalParam.getEntityIngestionId());
			DataSubmission dataSubmission = dataSubmissionList.get(0);
			String fileName = dataSubmission.getFileName();
			String outfileName = "SBD" + dataSubmission.getFileName();
			filePath = haritSourceFilePath + fileName;
			haritEvalParam.setFilePath(filePath);
			haritEvalParam.setFileOutPath(haritSourceFilePath + outfileName);

			file = new File(haritEvalParam.getFilePath());

			pdDocument = PDDocument.load(file);
			pdfTextStripper = new PDFTextStripper();

			String data = pdfTextStripper.getText(pdDocument);
			messages = sentenseBoundaryDetectionModel.sentenseBOundaryDetection(data);
			createMultiLinePagePdf(messages, haritEvalParam.getFileOutPath());

			RiskEvalPreprocess riskEvalPreprocess = new RiskEvalPreprocess();

			riskEvalPreprocess.setEntityId(haritEvalParam.getEntityId());
			riskEvalPreprocess.setEntityIngestionId(haritEvalParam.getEntityIngestionId());
			riskEvalPreprocess.setEvalSetId(haritEvalParam.getEvalSetId());
			riskEvalPreprocess.setRunId(haritEvalParam.getRunId());
			riskEvalPreprocess.setSelfPublic(1L);
			riskEvalPreprocess.setTemplateId(haritEvalParam.getTemplateId());
			riskEvalPreprocess.setRunDate(new Date(System.currentTimeMillis()));
			riskEvalPreprocess.setOcStatus("O");
			riskEvalPreprocess.setVerifiedBy(haritEvalParam.getEntityId().intValue());
			riskEvalPreprocess.setFileOutPath(haritEvalParam.getFileOutPath());
			riskEvalPreprocessRepository.save(riskEvalPreprocess);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	private void createMultiLinePagePdf(String[] text, String resultPath) throws IOException {

		try {

			PDDocument doc = new PDDocument();

			PDPage page = new PDPage();
			doc.addPage(page);
			PDPageContentStream contentStream = new PDPageContentStream(doc, page);

			contentStream.setFont(getFont(), FONT_SIZE);
			contentStream.setLeading(LEADING);
			contentStream.beginText();
			contentStream.newLineAtOffset(25, 700);

			int countLines = 0;

			for (String textLine : text) {
				textLine = textLine.replace("\n", "").replace("\r", "");

				if (countLines == 35) {
					contentStream.endText();
					contentStream.close();
					countLines = 0;
					contentStream = returnNewPageContent(doc);
				}
				writeLine(doc, contentStream, textLine);
				countLines += 1;
			}
			contentStream.endText();
			contentStream.close();
			doc.save(resultPath);
			doc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void writeLine(PDDocument doc, PDPageContentStream contentStream, String textLine) throws IOException {
		contentStream.showText(textLine);
		contentStream.newLine();
	}

	private PDPageContentStream returnNewPageContent(PDDocument doc) throws IOException {
		PDPage page = new PDPage();
		doc.addPage(page);
		PDPageContentStream contentStream = new PDPageContentStream(doc, page);
		contentStream.setFont(getFont(), FONT_SIZE);
		contentStream.setLeading(LEADING);
		contentStream.beginText();
		contentStream.newLineAtOffset(25, 700);
		return contentStream;
	}

	private static PDFont getFont() throws IOException {
		return PDType1Font.TIMES_ROMAN;
	}

	public Integer latestRunId() {

		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public void saveLatestRunId(HaritEvalParam haritEvalDTO) throws Exception {

		try {
			RiskEvalExecution riskEvalExecution = new RiskEvalExecution();
			riskEvalExecution.setEntityId(haritEvalDTO.getEntityId());
			riskEvalExecution.setEntityIngestionId(haritEvalDTO.getEntityIngestionId());
			riskEvalExecution.setEvalSetId(haritEvalDTO.getEvalSetId());
			riskEvalExecution.setTemplateId(haritEvalDTO.getTemplateId());
			riskEvalExecution.setOcStatus("O");
			riskEvalExecution.setVerifiedBy(haritEvalDTO.getEntityId().intValue());
			riskEvalExecution.setRunDate(new Date(System.currentTimeMillis()));
			riskEvalExecution = riskEvalExecutionRepository.save(riskEvalExecution);
			haritEvalDTO.setRunId(riskEvalExecution.getRunId());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}
	
	public List<RiskEvalCategoryResponseDTO>  fetchRiskEvalDetail(Long entityId,Long evalSetId,Long entityIngestionId,Long templateId) {
		SubRiskParamMaster subRiskParamMaster = null;
		 List<RiskEvalCategoryResponseDTO> riskEvalList=null;
		 List<RiskEvalDTO> riskevalList=null;
		 List<RiskEvalSubCategoryResponseDTO> riskSubList=null;
		try {
			riskevalList=new ArrayList<>();
			
			RiskEvalExecution riskEvalExecution = riskEvalExecutionRepository
					.findTopByEntityIdAndEvalSetIdAndEntityIngestionIdAndTemplateIdOrderByRunIdDesc(
							entityId, evalSetId,
							entityIngestionId, templateId);
		
			 List<RiskEval> listRiskEval= riskEvalRepository.findByRiskEvalIdRunId(riskEvalExecution.getRunId());
			
			// List<RiskEvalId,> list= listRiskEval.stream().collect(Collectors.groupingBy(RiskEval::getRiskEvalId,Collectors.toList()));
			riskEvalList=new ArrayList();
		Map<Long, RiskEvalResponseDTO> map=new HashMap<>();
			for(RiskEval riskEval: listRiskEval) {
				RiskEvalDTO riskEvalDTO=new RiskEvalDTO();
				riskEvalDTO.setEntityId(riskEval.getRiskEvalId().getEntityId());
				riskEvalDTO.setEntityIngestionId(riskEval.getRiskEvalId().getEntityIngestionId());
				riskEvalDTO.setEvalSetId(riskEval.getRiskEvalId().getEvalSetId());
				riskEvalDTO.setParamWeight(riskEval.getParamWeight());
				riskEvalDTO.setRiskCategoryId(riskEval.getRiskEvalId().getRiskCategoryId());
				riskEvalDTO.setRunId(riskEval.getRiskEvalId().getRunId());
				riskEvalDTO.setScore(riskEval.getScore());
				riskEvalDTO.setSelfPublic(riskEval.getRiskEvalId().getSelfPublic());
				riskEvalDTO.setSubCategoryId(riskEval.getRiskEvalId().getSubCategoryId());
				riskEvalDTO.setTemplateId(riskEval.getRiskEvalId().getTemplateId());
				riskevalList.add(riskEvalDTO);
		
			 }
			
		Map<Long, List<RiskEvalDTO>> groupByList=	riskevalList.stream().collect(Collectors.groupingBy(RiskEvalDTO::getRiskCategoryId,Collectors.toList()));
		Map<Long, Double> sumGroupby=	riskevalList.stream().collect(Collectors.groupingBy(RiskEvalDTO::getRiskCategoryId,Collectors.summingDouble(RiskEvalDTO::getScore)));
	//	groupByList.entrySet()
		for(Map.Entry<Long, List<RiskEvalDTO>> entrySet:groupByList.entrySet()) {
			
			
			List<RiskEvalDTO> list=groupByList.get(entrySet.getKey());
			
			
		//	entrySet
			RiskParamMaster riskParamMaster=riskParamMasterRepo.findByRiskCategoryId(entrySet.getKey());
			
		
			
			 RiskEvalCategoryResponseDTO riskEvalCategoryResponseDTO=new RiskEvalCategoryResponseDTO();
			 riskEvalCategoryResponseDTO.setRiskCategoryName(riskParamMaster.getParamName());
			
			 riskEvalCategoryResponseDTO.setScorePublicData( sumGroupby.get(entrySet.getKey()));
			 riskEvalCategoryResponseDTO.setScoreSelfData(sumGroupby.get(entrySet.getKey()));
			 riskEvalCategoryResponseDTO.setWeight(entrySet.getValue().get(0).getParamWeight());
			
			 
			 
			
		for(RiskEvalDTO riskEvalDTO:list) {
			riskSubList=new ArrayList<>();
			subRiskParamMaster = subRiskParamMasterRepo.findById( riskEvalDTO.getSubCategoryId())
					.get();
			 RiskEvalSubCategoryResponseDTO subCatResposnse=new RiskEvalSubCategoryResponseDTO();
			 subCatResposnse.setRiskSubCategoryName(subRiskParamMaster.getSubCategoryName());
			 subCatResposnse.setScorePublicData(riskEvalDTO.getScore());
			 subCatResposnse.setScoreSelfData(riskEvalDTO.getScore());
			 subCatResposnse.setWeight(riskEvalDTO.getParamWeight());
			 riskSubList.add(subCatResposnse);
		}
		riskEvalCategoryResponseDTO.setSubCategoryDTO(riskSubList);
		
		 riskEvalList.add(riskEvalCategoryResponseDTO);
		 
		}
	
		}catch(Exception e) {
			e.printStackTrace();
		}
		return riskEvalList;
	}
}
