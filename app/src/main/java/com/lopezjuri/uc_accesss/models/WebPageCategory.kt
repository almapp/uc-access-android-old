package com.lopezjuri.uc_accesss.models

/**
 * Created by patriciolopez on 19-01-16.
 */
class WebPageCategory(val name: String, val detail: String?, val webpages: List<WebPage> = emptyList()) {
    val activeWebpages: List<WebPage>
        get() = webpages.filter { it.selected }
}
