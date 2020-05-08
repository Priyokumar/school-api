package com.school.common.services;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.common.dto.ActionResponseDTO;
import com.school.common.dto.ApiUtilDTO;
import com.school.common.dto.DocumentBodyDTO;
import com.school.common.dto.DocumentDTO;
import com.school.common.entity.Document;
import com.school.common.utils.ScUtil;
import com.school.common.vo.DocumentFor;
import com.school.error.exception.BadRequestException;
import com.school.error.exception.InternalServerException;
import com.school.error.exception.NotFoundException;
import com.school.hr.entity.Employee;
import com.school.hr.entity.EmployeePersonalInfo;

@Service
public class DocUploadService {

	@Autowired
	private CommonService commonService;

	private final Path rootLocation = Paths.get("upload");

	public Document store(MultipartFile file, DocumentBodyDTO documentBody, String id) throws Exception {

		String uploadPath = System.getProperty("user.dir") + File.separator + "upload";

		String dirPath = uploadPath;

		LocalDate today = LocalDate.now();
		int year = today.getYear();
		int monthValue = today.getMonthValue();
		int dayOfMonth = today.getDayOfMonth();

		String datePath = File.separator + year + File.separator + monthValue + File.separator + dayOfMonth;

		String docFor = documentBody.getDocFor();

		if (docFor.equals(DocumentFor.EMPLOYEE))
			dirPath += File.separator + DocumentFor.EMPLOYEE + File.separator + datePath + File.separator + id;

		String filePath = dirPath + File.separator + file.getOriginalFilename();

		File dirFile = new File(dirPath);
		File fileToWrite = new File(filePath);

		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}

		if (fileToWrite.exists()) {
			fileToWrite.delete();
		}

		file.transferTo(fileToWrite);

		Long docId = documentBody.getId();
		Document doc = null;
		if (ScUtil.isAllPresent(docId))
			doc = commonService.findById(docId, DocumentDTO.class);
		else
			doc = new Document();

		doc.setName(file.getOriginalFilename());
		doc.setPath(dirPath);

		doc = commonService.save(doc);

		return doc;

	}

	public Resource loadFile(Long docId) {
		try {

			Document doc = commonService.findById(docId, Document.class);

			if (!ScUtil.isAllPresent(doc))
				throw new NotFoundException("Document not found");

			Path filePath = Paths.get(doc.getPath() + File.separator + doc.getName());
			Resource resource = new UrlResource(filePath.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new InternalServerException("FAIL!");
			}
		} catch (MalformedURLException e) {
			throw new InternalServerException(e.getMessage());
		}
	}

	public ActionResponseDTO updateDocument(String id, MultipartFile file, String documentBodyStr) {

		ActionResponseDTO res = new ActionResponseDTO();

		try {

			ObjectMapper objMapper = new ObjectMapper();
			DocumentBodyDTO documentBody = objMapper.readValue(documentBodyStr, DocumentBodyDTO.class);

			String docFor = documentBody.getDocFor();
			String type = documentBody.getType();
			Document doc = store(file, documentBody, id);

			if (!ScUtil.isAllPresent(docFor, type))
				throw new BadRequestException("One or more field are empty in Document Body");

			if (docFor.equals(DocumentFor.EMPLOYEE))
				updateEmployeeDocs(id, docFor, type, doc);

			/*
			 * if (docFor.equals(DocumentFor.STUDENT)) this.updateStudentDocs(id, docFor,
			 * type, doc);
			 */

			res.setActionMessage(doc.getId().toString());

		} catch (Exception e) {
			throw new InternalServerException(e.getMessage());
		}

		res.setApiMessage(ApiUtilDTO.okMessage("Success"));

		return res;
	}

	private void updateEmployeeDocs(String id, String docFor, String type, Document doc) {
		Employee employee = commonService.findById(id, Employee.class);

		if (!ScUtil.isAllPresent(docFor, type))
			throw new NotFoundException("Could not update document.");

		EmployeePersonalInfo personalInfo = employee.getPersonalInfo();
		if (!ScUtil.isAllPresent(personalInfo)) {
			throw new InternalServerException("Could not update document.");
		}
		commonService.save(employee);
	}

	public void deleteAll() {
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}

	public void init() {
		try {
			Files.createDirectory(rootLocation);
		} catch (IOException e) {
			throw new InternalServerException("Could not initialize storage!");
		}
	}
}
