package com.app.jokeappv2.data.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Created by Muthu Narayanan on 19/11/2021.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
data class Jokes(

    @JsonProperty("category")
    var category: String? = null,

    @JsonProperty("type")
    var type: String? = null,

    @JsonProperty("setup")
    var setup: String? = null,

    @JsonProperty("delivery")
    var delivery: String? = null,

    @JsonProperty("flags")
    var flags: Flags? = null,

    @JsonProperty("id")
    var id: Int?,

    @JsonProperty("safe")
    var safe: Boolean?,

    @JsonProperty("lang")
    var lang: String? = null,
)
