package com.app.jokeappv2.data.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Created by Muthu Narayanan on 19/11/2021.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
data class Flags(

    @JsonProperty("nsfw")
    var nsfw: Boolean?,

    @JsonProperty("religious")
    var religious: Boolean?,

    @JsonProperty("political")
    var political: Boolean?,

    @JsonProperty("racist")
    var racist: Boolean?,

    @JsonProperty("sexist")
    var sexist: Boolean?,

    @JsonProperty("explicit")
    var explicit: Boolean?,
)
