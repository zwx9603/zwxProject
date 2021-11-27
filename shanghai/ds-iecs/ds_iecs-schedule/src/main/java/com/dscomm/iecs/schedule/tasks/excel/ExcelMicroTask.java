package com.dscomm.iecs.schedule.tasks.excel;

import com.dscomm.iecs.schedule.service.excel.MicroStationService;
import org.mx.spring.task.BaseTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("microExcelTask")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ExcelMicroTask extends BaseTask {

    private MicroStationService microStationService;

    @Autowired
    public ExcelMicroTask(MicroStationService microStationService) {
        this.microStationService = microStationService;
    }


    @Override
    public void invoke() {
        try {
//            microStationService.fromExcel();
//            microStationService.testUrl();
            microStationService.modify();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
