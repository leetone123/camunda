package com.leetone.camunda;

import org.camunda.bpm.dmn.engine.DmnDecisionTableResult;
import org.camunda.bpm.engine.DecisionService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.EventListener;

/**
 * @description: 启用流程支持
 * @author: leetone
 * @date: 2021/9/6 17:20
 * @throws:
 */
@SpringBootApplication
@EnableProcessApplication
public class LoanApprovalApplication {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  public static void main(String[] args) {
    SpringApplication.run(LoanApprovalApplication.class, args);
  }

  @Autowired
  private RuntimeService runtimeService;

  @EventListener
  private void processPostDeploy(PostDeployEvent event) {
    //在bpmn部署完毕后调用
    runtimeService.startProcessInstanceByKey("loan-approval");
    //在dmn部署完毕后调用
    DecisionService decisionService = event.getProcessEngine().getDecisionService();
    // 输入参数
    VariableMap variables = Variables.createVariables().putValue("bmi", 20.0);
    // 调用决策
    DmnDecisionTableResult dmnDecisionTableResult = decisionService
        .evaluateDecisionTableByKey("bmi-classification", variables);
    String result = dmnDecisionTableResult.getSingleEntry();
    // 打印结果
    logger.info("\n\nBMI Classification result is : {0}\n\n", result);
  }
}
