package com.github.imflog.gradle.schema

import io.confluent.kafka.schemaregistry.client.CachedSchemaRegistryClient
import io.confluent.kafka.schemaregistry.client.SchemaRegistryClient

/**
 * This is a singleton.
 * We can reuse the registryClient without instantiating new ones.
 */
open class RegistryClientWrapper private constructor() {
    private object Holder {
        val INSTANCE = RegistryClientWrapper()
    }

    companion object {
        val instance: RegistryClientWrapper by lazy { Holder.INSTANCE }
    }

    private var registryClient: SchemaRegistryClient? = null

    open fun client(url: String): SchemaRegistryClient? {
        if (registryClient == null) {
            registryClient = CachedSchemaRegistryClient(url, 100)
        }
        return registryClient
    }
}