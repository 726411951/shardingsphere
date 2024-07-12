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

package org.apache.shardingsphere.infra.binder.segment.column;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.shardingsphere.infra.binder.segment.SegmentType;
import org.apache.shardingsphere.infra.binder.segment.expression.impl.ColumnSegmentBinder;
import org.apache.shardingsphere.infra.binder.segment.from.TableSegmentBinderContext;
import org.apache.shardingsphere.infra.binder.statement.SQLStatementBinderContext;
import org.apache.shardingsphere.sql.parser.statement.core.segment.dml.column.ColumnSegment;
import org.apache.shardingsphere.sql.parser.statement.core.segment.dml.column.InsertColumnsSegment;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Insert columns segment binder.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class InsertColumnsSegmentBinder {
    
    /**
     * Bind insert columns segment.
     *
     * @param segment insert columns segment
     * @param binderContext SQL statement binder context
     * @param tableBinderContexts table binder contexts
     * @return bound insert columns segment
     */
    public static InsertColumnsSegment bind(final InsertColumnsSegment segment, final SQLStatementBinderContext binderContext, final Map<String, TableSegmentBinderContext> tableBinderContexts) {
        Collection<ColumnSegment> boundColumns = segment.getColumns().stream()
                .map(each -> ColumnSegmentBinder.bind(each, SegmentType.INSERT_COLUMNS, binderContext, tableBinderContexts, Collections.emptyMap())).collect(Collectors.toList());
        return new InsertColumnsSegment(segment.getStartIndex(), segment.getStopIndex(), boundColumns);
    }
}
