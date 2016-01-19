package com.lopezjuri.uc_accesss.models

/**
 * Created by patriciolopez on 19-01-16.
 */
class WebPageGroup(val name: String, val detail: String?, val categories: List<WebPageCategory> = emptyList()) {
    val activeCategories: List<WebPageCategory>
        get() = categories.filter { it.activeWebpages.count() > 0 }
}
