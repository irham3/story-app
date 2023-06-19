package org.irham3.storyapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class DetailStoryResponse(

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("story")
	val storyResponse: StoryResponse
)

data class StoryResponse(

	@field:SerializedName("photoUrl")
	val photoUrl: String,

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("lon")
	val lon: Number,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("lat")
	val lat: Number
)
