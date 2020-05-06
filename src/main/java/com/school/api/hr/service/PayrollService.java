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
import com.school.api.hr.dto.DeductionDTO;
import com.school.api.hr.dto.EarningDTO;
import com.school.api.hr.dto.PayrollDTO;
import com.school.api.hr.dto.PayrollRequestDTO;
import com.school.api.hr.dto.PayrollResponseDTO;
import com.school.api.hr.dto.PayrollsResponseDTO;
import com.school.api.hr.entity.Deduction;
import com.school.api.hr.entity.Earning;
import com.school.api.hr.entity.Payroll;
import com.school.api.hr.repository.IEmployeeRepository;
import com.school.api.hr.repository.IPayrollRepository;

@Service
public class PayrollService {

	@Autowired
	IPayrollRepository payrollRepository;

	@Autowired
	IEmployeeRepository employeeRepository;

	@Autowired
	CommonService commonService;
	
	@Autowired
	EmployeeService employeeService;

	public PayrollsResponseDTO findPayrolls(String empId, String status) {

		PayrollsResponseDTO res = new PayrollsResponseDTO();

		List<Payroll> payrolls = null;

		if (ScUtil.isAllPresent(empId) && !ScUtil.isAllPresent(status)) {

			payrolls = payrollRepository.findPayrollByEmployee(empId);

		} else if (!ScUtil.isAllPresent(empId) && ScUtil.isAllPresent(status)) {

			payrolls = payrollRepository.findPayrollByStatus(status);

		} else if (ScUtil.isAllPresent(empId) && ScUtil.isAllPresent(status)) {

			payrolls = payrollRepository.findPayrollByEmployeeAndStatus(status, empId);

		} else {
			payrolls = (List<Payroll>) payrollRepository.findAll();
		}

		if (!ScUtil.isAllPresent(payrolls))
			throw new NotFoundException("No payroll can be found !");

		List<PayrollDTO> payrollsDTO = new ArrayList<PayrollDTO>();

		payrolls.forEach(payroll -> {
			payrollsDTO.add(setPayrollToDTO(payroll));
		});

		res.setApiMessage(ApiUtilDTO.okMessage("Success"));
		res.setData(payrollsDTO);

		return res;
	}

	private PayrollDTO setPayrollToDTO(Payroll payroll) {

		PayrollDTO payrollDTO = new PayrollDTO();
		payrollDTO.setId(payroll.getId());

		payrollDTO.setGrossSalary(payroll.getGrossSalary());
		payrollDTO.setMonth(payroll.getMonth());
		payrollDTO.setNetSalary(payroll.getNetSalary());
		payrollDTO.setStatus(payroll.getStatus());
		payrollDTO.setTotalDeduction(payroll.getTotalDeduction());
		payrollDTO.setTotalEarning(payroll.getTotalEarning());
		payrollDTO.setYear(payroll.getYear());
		payrollDTO.setEmployee(employeeService.setEmployeeToDto(payroll.getEmployee()));
		payrollDTO.setEarnings(new ArrayList<EarningDTO>());
		payrollDTO.setDeductions(new ArrayList<DeductionDTO>());
		
		List<Earning> earnings = payroll.getEarnings();
		
		if (ScUtil.isAllPresent(earnings)) {

			for (Earning earning : earnings) {
				EarningDTO earningDTO = new EarningDTO();
				earningDTO.setType(earning.getType());
				earningDTO.setValue(earning.getValue());
				payrollDTO.getEarnings().add(earningDTO);
			}
		}

		List<Deduction> deductions = payroll.getDeductions();

		if (ScUtil.isAllPresent(deductions)) {
			for (Deduction deduction : deductions) {
				DeductionDTO deductionDTO = new DeductionDTO();
				deductionDTO.setType(deduction.getType());
				deductionDTO.setValue(deduction.getValue());
				payrollDTO.getDeductions().add(deductionDTO);
			}
		}

		return payrollDTO;
	}

	public ActionResponseDTO createOrUpdatePayroll(PayrollRequestDTO payrollRequestDTO, String id) {

		ActionResponseDTO res = new ActionResponseDTO();

		Payroll payroll = new Payroll();
		if (ScUtil.isAllPresent(id)) {

			Optional<Payroll> payrollOpt = payrollRepository.findById(id);
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
			payroll.setEarnings(new ArrayList<Earning>());
			payroll.setDeductions(new ArrayList<Deduction>());
			List<EarningDTO> earningsDTO = payrollRequestDTO.getEarnings();

			if (ScUtil.isAllPresent(earningsDTO)) {

				for (EarningDTO earningDTO : earningsDTO) {
					Earning earning = new Earning();
					earning.setPayroll(payroll);
					earning.setType(earningDTO.getType());
					earning.setValue(earningDTO.getValue());
					payroll.getEarnings().add(earning);
				}
			}

			List<DeductionDTO> deductionsDTO = payrollRequestDTO.getDeductions();

			if (ScUtil.isAllPresent(deductionsDTO)) {
				for (DeductionDTO deductionDTO : deductionsDTO) {
					Deduction deduction = new Deduction();
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

	public PayrollResponseDTO findPayroll(String id) {

		PayrollResponseDTO res = new PayrollResponseDTO();

		Optional<Payroll> payrollOpt = payrollRepository.findById(id);

		if (!payrollOpt.isPresent())
			throw new NotFoundException("No payroll can be found !");

		PayrollDTO designationDTO = setPayrollToDTO(payrollOpt.get());

		res.setApiMessage(ApiUtilDTO.okMessage("Success"));
		res.setData(designationDTO);

		return res;
	}

	public ActionResponseDTO deletePayroll(String id) {

		ActionResponseDTO res = new ActionResponseDTO();

		Optional<Payroll> payrollOpt = payrollRepository.findById(id);

		if (!payrollOpt.isPresent())
			throw new NotFoundException("No payroll can be found !");

		payrollRepository.delete(payrollOpt.get());

		res.setApiMessage(ApiUtilDTO.okMessage("Successfully deleted"));
		return res;
	}
}
