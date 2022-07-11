package org.modality_project.base.shared.services.datasourcemodel;

import dev.webfx.stack.framework.shared.orm.domainmodel.DataSourceModel;
import dev.webfx.stack.framework.shared.orm.dql.sqlcompiler.sql.dbms.PostgresSyntax;
import dev.webfx.stack.framework.shared.services.datasourcemodel.spi.DataSourceModelProvider;
import dev.webfx.stack.framework.shared.services.domainmodel.DomainModelService;
import dev.webfx.stack.async.Future;

/**
 * @author Bruno Salmon
 */
public final class ModalityDataSourceModelProvider implements DataSourceModelProvider {

    public final static Object MODALITY_DATA_SOURCE_ID = "MDS";

    private final static DataSourceModel MODALITY_DATA_SOURCE_MODEL = new DataSourceModel(
            MODALITY_DATA_SOURCE_ID,
            PostgresSyntax.get(),
            DomainModelService.loadDomainModel(MODALITY_DATA_SOURCE_ID).result()
    );

    @Override
    public Object getDefaultDataSourceId() {
        return MODALITY_DATA_SOURCE_ID;
    }

    @Override
    public DataSourceModel getDefaultDataSourceModel() {
        return MODALITY_DATA_SOURCE_MODEL;
    }

    @Override
    public DataSourceModel getDataSourceModel(Object dataSourceId) {
        return MODALITY_DATA_SOURCE_ID.equals(dataSourceId) ? MODALITY_DATA_SOURCE_MODEL : null;
    }

    @Override
    public Future<DataSourceModel> loadDataSourceModel(Object dataSourceId) {
        return MODALITY_DATA_SOURCE_ID.equals(dataSourceId) ? Future.succeededFuture(MODALITY_DATA_SOURCE_MODEL) : Future.failedFuture("Unknown data source model id=" + dataSourceId);
    }
}