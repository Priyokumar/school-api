package com.school.api.hr.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.api.common.dto.ActionResponseDTO;
import com.school.api.common.dto.ApiUtilDTO;
import com.school.api.common.services.CommonService;
import com.school.api.common.utils.ScUtil;
import com.school.api.common.vo.PayrollStatus;
import com.school.api.error.exception.NotFoundException;
import com.school.api.hr.dto.EmployeePayrollDeductionDTO;
import com.school.api.hr.dto.EmployeePayrollEarningDTO;
import com.school.api.hr.dto.EmployeePayrollDTO;
import com.school.api.hr.dto.EmployeePayrollRequestDTO;
import com.school.api.hr.dto.EmployeePayrollResponseDTO;
import com.school.api.hr.dto.EmployeePayrollsResponseDTO;
import com.school.api.hr.entity.EmployeePayrollDeduction;
import com.school.api.hr.entity.EmployeePayrollEarning;
import com.school.api.hr.entity.EmployeePayroll;
import com.school.api.hr.repository.IEmployeeRepository;
import com.school.api.hr.repository.IEmployeePayrollRepository;

@Service
public class EmployeePayrollService {

	@Autowired
	private IEmployeePayrollRepository payrollRepository;

	@Autowired
	private IEmployeeRepository employeeRepository;

	@Autowired
	private CommonService commonService;

	@Autowired
	private EmployeeService employeeService;

	public EmployeePayrollsResponseDTO findPayrolls(String empId, String status) {

		EmployeePayrollsResponseDTO res = new EmployeePayrollsResponseDTO();

		List<EmployeePayroll> payrolls = null;

		if (ScUtil.isAllPresent(empId) && !ScUtil.isAllPresent(status)) {

			payrolls = payrollRepository.findPayrollByEmployee(empId);

		} else if (!ScUtil.isAllPresent(empId) && ScUtil.isAllPresent(status)) {

			payrolls = payrollRepository.findPayrollByStatus(status);

		} else if (ScUtil.isAllPresent(empId) && ScUtil.isAllPresent(status)) {

			payrolls = payrollRepository.findPayrollByEmployeeAndStatus(status, empId);

		} else {
			payrolls = (List<EmployeePayroll>) payrollRepository.findAll();
		}

		if (!ScUtil.isAllPresent(payrolls))
			throw new NotFoundException("No payroll can be found !");

		List<EmployeePayrollDTO> payrollsDTO = new ArrayList<EmployeePayrollDTO>();

		payrolls.forEach(payroll -> {
			payrollsDTO.add(setPayrollToDTO(payroll));
		});

		res.setApiMessage(ApiUtilDTO.okMessage("Success"));
		res.setData(payrollsDTO);

		return res;
	}

	private EmployeePayrollDTO setPayrollToDTO(EmployeePayroll payroll) {

		EmployeePayrollDTO payrollDTO = new EmployeePayrollDTO();
		payrollDTO.setId(payroll.getId());

		payrollDTO.setGrossSalary(payroll.getGrossSalary());
		payrollDTO.setMonth(payroll.getMonth());
		payrollDTO.setNetSalary(payroll.getNetSalary());
		payrollDTO.setStatus(payroll.getStatus());
		payrollDTO.setTotalDeduction(payroll.getTotalDeduction());
		payrollDTO.setTotalEarning(payroll.getTotalEarning());
		payrollDTO.setYear(payroll.getYear());
		payrollDTO.setEmployee(employeeService.setEmployeeToDto(payroll.getEmployee()));
		payrollDTO.setEarnings(new ArrayList<EmployeePayrollEarningDTO>());
		payrollDTO.setDeductions(new ArrayList<EmployeePayrollDeductionDTO>());

		List<EmployeePayrollEarning> earnings = payroll.getEarnings();

		if (ScUtil.isAllPresent(earnings)) {

			for (EmployeePayrollEarning earning : earnings) {
				EmployeePayrollEarningDTO earningDTO = new EmployeePayrollEarningDTO();
				earningDTO.setType(earning.getType());
				earningDTO.setValue(earning.getValue());
				payrollDTO.getEarnings().add(earningDTO);
			}
		}

		List<EmployeePayrollDeduction> deductions = payroll.getDeductions();

		if (ScUtil.isAllPresent(deductions)) {
			for (EmployeePayrollDeduction deduction : deductions) {
				EmployeePayrollDeductionDTO deductionDTO = new EmployeePayrollDeductionDTO();
				deductionDTO.setType(deduction.getType());
				deductionDTO.setValue(deduction.getValue());
				payrollDTO.getDeductions().add(deductionDTO);
			}
		}

		return payrollDTO;
	}

	public ActionResponseDTO createOrUpdatePayroll(EmployeePayrollRequestDTO payrollRequestDTO, String id) {

		ActionResponseDTO res = new ActionResponseDTO();

		EmployeePayroll payroll = new EmployeePayroll();
		if (ScUtil.isAllPresent(id)) {

			Optional<EmployeePayroll> payrollOpt = payrollRepository.findById(id);
			if (!payrollOpt.isPresent()) {
				throw new NotFoundException("Payroll not found.");
			}
			payroll = payrollOpt.get();

			payroll.setGrossSalary(payrollRequestDTO.getGrossSalary());
			payroll.setMonth(payrollRequestDTO.getMonth());
			payroll.setNetSalary(payrollRequestDTO.getNetSalary());

			if (ScUtil.isAllPresent(id)) {
				payroll.setStatus(payrollRequestDTO.getStatus());
			} else {
				payroll.setStatus(PayrollStatus.GENERATED);
			}
			payroll.setStatus(payrollRequestDTO.getStatus());
			payroll.setTotalDeduction(payrollRequestDTO.getTotalDeduction());
			payroll.setTotalEarning(payrollRequestDTO.getTotalEarning());
			payroll.setYear(payrollRequestDTO.getYear());
			payroll.setEmployee(employeeRepository.findById(payrollRequestDTO.getEmpId()).get());
			payroll.setEarnings(new ArrayList<EmployeePayrollEarning>());
			payroll.setDeductions(new ArrayList<EmployeePayrollDeduction>());
			List<EmployeePayrollEarningDTO> earningsDTO = payrollRequestDTO.getEarnings();

			if (ScUtil.isAllPresent(earningsDTO)) {

				for (EmployeePayrollEarningDTO earningDTO : earningsDTO) {
					EmployeePayrollEarning earning = new EmployeePayrollEarning();
					earning.setPayroll(payroll);
					earning.setType(earningDTO.getType());
					earning.setValue(earningDTO.getValue());
					payroll.getEarnings().add(earning);
				}
			}

			List<EmployeePayrollDeductionDTO> deductionsDTO = payrollRequestDTO.getDeductions();

			if (ScUtil.isAllPresent(deductionsDTO)) {
				for (EmployeePayrollDeductionDTO deductionDTO : deductionsDTO) {
					EmployeePayrollDeduction deduction = new EmployeePayrollDeduction();
					deduction.setPayroll(payroll);
					deduction.setType(deductionDTO.getType());
					deduction.setValue(deductionDTO.getValue());
					payroll.getDeductions().add(deduction);
				}
			}
		}

		commonService.save(payroll);

		String message = "";
		if (ScUtil.isAllPresent(id)) {
			message = "Successfully updated the Designation data";
			res.setApiMessage(ApiUtilDTO.okMessage(message));
		} else {
			message = "Successfully created a Designation";
			res.setApiMessage(ApiUtilDTO.createdMessage(message));
			res.setActionMessage(message);
		}
		return res;
	}

	public EmployeePayrollResponseDTO findPayroll(String id) {

		EmployeePayrollResponseDTO res = new EmployeePayrollResponseDTO();

		Optional<EmployeePayroll> payrollOpt = payrollRepository.findById(id);

		if (!payrollOpt.isPresent())
			throw new NotFoundException("No payroll can be found !");

		EmployeePayrollDTO designationDTO = setPayrollToDTO(payrollOpt.get());

		res.setApiMessage(ApiUtilDTO.okMessage("Success"));
		res.setData(designationDTO);

		return res;
	}

	public ActionResponseDTO deletePayroll(String id) {

		ActionResponseDTO res = new ActionResponseDTO();

		Optional<EmployeePayroll> payrollOpt = payrollRepository.findById(id);

		if (!payrollOpt.isPresent())
			throw new NotFoundException("No payroll can be found !");

		payrollRepository.delete(payrollOpt.get());

		res.setApiMessage(ApiUtilDTO.okMessage("Successfully deleted"));
		return res;
	}
}
