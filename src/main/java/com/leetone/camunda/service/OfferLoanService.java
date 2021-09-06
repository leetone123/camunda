package com.leetone.camunda.service;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description 发放贷款
 * @ClassName OfferLoan.java
 * @Author leetone
 * @Version 1.0.0
 * @CreateTime 2021年09月06日 16:05:00
 */
public class OfferLoanService implements JavaDelegate {
  private final static Logger LOGGER = LoggerFactory.getLogger("LOAN-REQUESTS");

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    LOGGER.info("offer " + execution.getVariable("lenderId") + " loans");
  }
}
