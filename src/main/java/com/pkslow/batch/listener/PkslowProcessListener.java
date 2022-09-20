package com.pkslow.batch.listener;

import com.pkslow.batch.entity.Employee;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.ItemProcessListener;

public class PkslowProcessListener implements ItemProcessListener<Employee,Employee> {
    private static final Log logger = LogFactory.getLog(PkslowProcessListener.class);

    @Override
    public void beforeProcess(Employee employee) {
        logger.info("beforeProcess：" + employee);
    }

    @Override
    public void afterProcess(Employee employee, Employee employee2) {
        logger.info("afterProcess o：" + employee);
        logger.info("afterProcess o2：" + employee2);
    }

    @Override
    public void onProcessError(Employee employee, Exception e) {
        logger.info("onProcessError：" + employee);
    }
}
