package com.lopezjuri.uc_accesss.models

import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.beust.klaxon.array
import com.github.kittinunf.fuel.Fuel
import com.lopezjuri.uc_accesss.promise
import nl.komponents.kovenant.Promise
import nl.komponents.kovenant.then
import java.io.ByteArrayInputStream

/**
 * Created by patriciolopez on 19-01-16.
 */
class WebPageFetcher(val URL: String) {
    fun fetch(): Promise<List<WebPageGroup>, Exception> {
        return Fuel.get(this.URL).promise() then {
            val json = Parser().parse(ByteArrayInputStream(it.second)) as? JsonArray<JsonObject>
            json?.map {
                WebPageGroup(
                        name = it["name"] as String,
                        detail = it["detail"] as? String,
                        categories = it.array<JsonObject>("categories")?.map {
                            WebPageCategory(
                                    name = it["name"] as String,
                                    detail = it["detail"] as String?,
                                    webpages = it.array<JsonObject>("services")?.map {
                                        WebPage(
                                                it["id"] as String?,
                                                it["name"] as String,
                                                it["description"] as String?,
                                                it["url"] as String,
                                                it["image"] as String?
                                        )
                                    } ?: emptyList())
                        } ?: emptyList())
            } ?: emptyList()
        }
    }
}
