package com.mitchmele.grievbox.util;

import com.networknt.schema.JsonSchema;

public interface GenericJsonSchemaBuilder {

    JsonSchema build(String resourceName);
}
