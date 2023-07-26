/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.infra.binder.segment.projection;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.shardingsphere.infra.binder.segment.projection.impl.ColumnProjectionSegmentBinder;
import org.apache.shardingsphere.infra.metadata.ShardingSphereMetaData;
import org.apache.shardingsphere.sql.parser.sql.common.segment.dml.item.ColumnProjectionSegment;
import org.apache.shardingsphere.sql.parser.sql.common.segment.dml.item.ProjectionSegment;
import org.apache.shardingsphere.sql.parser.sql.common.segment.dml.item.ProjectionsSegment;

/**
 * Projections segment binder.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ProjectionsSegmentBinder {
    
    /**
     * Bind projections segment with metadata.
     *
     * @param segment table segment
     * @param metaData meta data
     * @param defaultDatabaseName default database name
     * @return bounded projections segment
     */
    public static ProjectionsSegment bind(final ProjectionsSegment segment, final ShardingSphereMetaData metaData, final String defaultDatabaseName) {
        ProjectionsSegment result = new ProjectionsSegment(segment.getStartIndex(), segment.getStopIndex());
        result.setDistinctRow(segment.isDistinctRow());
        segment.getProjections().forEach(each -> result.getProjections().add(bind(each, metaData, defaultDatabaseName)));
        return result;
    }
    
    private static ProjectionSegment bind(final ProjectionSegment projectionSegment, final ShardingSphereMetaData metaData, final String defaultDatabaseName) {
        if (projectionSegment instanceof ColumnProjectionSegment) {
            return ColumnProjectionSegmentBinder.bind((ColumnProjectionSegment) projectionSegment, metaData, defaultDatabaseName);
        }
        // TODO support more ProjectionSegment bind
        return projectionSegment;
    }
}
