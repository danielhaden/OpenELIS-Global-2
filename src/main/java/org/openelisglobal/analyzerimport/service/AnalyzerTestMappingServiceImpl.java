package org.openelisglobal.analyzerimport.service;

import java.util.ArrayList;

import org.openelisglobal.analyzerimport.dao.AnalyzerTestMappingDAO;
import org.openelisglobal.analyzerimport.valueholder.AnalyzerTestMapping;
import org.openelisglobal.analyzerimport.valueholder.AnalyzerTestMappingPK;
import org.openelisglobal.common.service.BaseObjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnalyzerTestMappingServiceImpl extends BaseObjectServiceImpl<AnalyzerTestMapping, AnalyzerTestMappingPK>
        implements AnalyzerTestMappingService {
    @Autowired
    protected AnalyzerTestMappingDAO baseObjectDAO;

    AnalyzerTestMappingServiceImpl() {
        super(AnalyzerTestMapping.class);
        defaultSortOrder = new ArrayList<>();
    }

    @Override
    protected AnalyzerTestMappingDAO getBaseObjectDAO() {
        return baseObjectDAO;
    }

}
