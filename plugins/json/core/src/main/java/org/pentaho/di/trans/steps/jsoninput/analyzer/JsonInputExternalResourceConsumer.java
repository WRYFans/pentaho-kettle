/*! ******************************************************************************
 *
 * Pentaho Data Integration
 *
 * Copyright (C) 2002-2018 by Hitachi Vantara : http://www.pentaho.com
 *
 *******************************************************************************
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 ******************************************************************************/

package org.pentaho.di.trans.steps.jsoninput.analyzer;

import org.pentaho.di.core.row.RowMetaInterface;
import org.pentaho.di.trans.steps.jsoninput.JsonInput;
import org.pentaho.di.trans.steps.jsoninput.JsonInputMeta;
import org.pentaho.metaverse.api.IAnalysisContext;
import org.pentaho.metaverse.api.analyzer.kettle.KettleAnalyzerUtil;
import org.pentaho.metaverse.api.analyzer.kettle.step.BaseStepExternalResourceConsumer;
import org.pentaho.metaverse.api.model.IExternalResourceInfo;

import java.util.Collection;
import java.util.Collections;

public class JsonInputExternalResourceConsumer
  extends BaseStepExternalResourceConsumer<JsonInput, JsonInputMeta> {

  @Override
  public Class<JsonInputMeta> getMetaClass() {
    return JsonInputMeta.class;
  }

  @Override
  public boolean isDataDriven( final JsonInputMeta meta ) {
    return meta.isAcceptingFilenames();
  }

  @Override
  public Collection<IExternalResourceInfo> getResourcesFromMeta(
    final JsonInputMeta meta, final IAnalysisContext context ) {
    Collection<IExternalResourceInfo> resources = Collections.emptyList();

    // We only need to collect these resources if we're not data-driven and there are no used variables in the
    // metadata relating to external files.
    if ( !isDataDriven( meta ) ) {
      resources = KettleAnalyzerUtil.getResourcesFromMeta( meta, context );
    }
    return resources;
  }

  @Override
  public Collection<IExternalResourceInfo> getResourcesFromRow(
    final JsonInput step, final RowMetaInterface rowMeta, final Object[] row ) {

    return KettleAnalyzerUtil.getResourcesFromRow( step, rowMeta, row );
  }
}
