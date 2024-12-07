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

package org.apache.shardingsphere.infra.metadata.caseinsensitive;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.shardingsphere.infra.metadata.identifier.ShardingSphereIdentifier;

/**
 * Case insensitive qualified table.
 */
@Getter
@EqualsAndHashCode
// TODO should merge with QualifiedTable
public final class CaseInsensitiveQualifiedTable {
    
    private final ShardingSphereIdentifier schemaName;
    
    private final ShardingSphereIdentifier tableName;
    
    public CaseInsensitiveQualifiedTable(final String schemaName, final String tableName) {
        this.schemaName = new ShardingSphereIdentifier(schemaName);
        this.tableName = new ShardingSphereIdentifier(tableName);
    }
    
    @Override
    public String toString() {
        return null == schemaName.getValue() ? tableName.getValue() : String.join(".", schemaName.getValue(), tableName.getValue());
    }
}
